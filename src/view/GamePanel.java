package view;

import controller.Menu;
import controller.handler.BagHandler;
import javax.swing.JPanel;
import java.awt.Graphics;

import setting.Setting;
import controller.render.TileRender;
import model.entity.Character;
import controller.render.CharacterRender;
import model.setup.TileSetup;
import model.setup.MapSetup;

import controller.handler.CharacterHandler;
import controller.handler.InteractHandler;
import controller.handler.MenuHandler;

import controller.render.ItemRender;
import controller.render.MonsterRender;
import controller.render.NPCRender;
import model.define.GameScreen;

import model.define.GameState;
import model.setup.ItemSetup;
import model.setup.MonsterSetup;
import model.setup.NPCSetup;

public class GamePanel extends JPanel {

    // Game run
    boolean running;
    int fps;
    double delta;
    long currentTime;
    long lastTime;
    long timeOfFps;
    public double timeDrawOneFrame;

    public Setting setting;

    // ? Map
    public MapSetup mapSetup;

    // ? Tile
    public TileSetup tileSetup;
    public TileRender tileRender;

    // Item
    public ItemSetup itemSetup;
    public ItemRender itemRender;

    // ? Character
    public Character character;
    public CharacterRender characterRender;

    // User inteface
    public UserInterface ui;

    // Event
    public CharacterHandler ch;

    // Bag handler
    public BagHandler bh;

    // Game State
    public int gameState;

    // Game screen
    public int gameScreen;

    // Menu
    public Menu menu;
    MenuHandler mh;
    
    // NPC
    public NPCSetup npcSetup;
    NPCRender npcRender;
    InteractHandler interactHandler;
    
    // Monster
    public MonsterSetup monsterSetup;
    MonsterRender monsterRender;

    public GamePanel() {
        setting = new Setting();
        this.setPreferredSize(setting.getScreenSize());
        this.setFocusable(true);
        gameScreen = GameScreen.MENU_SCREEN;
        if (gameScreen == GameScreen.MENU_SCREEN) {
            setupMenu();
        } else if (gameScreen == GameScreen.PLAY_SCREEN) {
            setup();
        }
    }

    public void setupMenu() {
        menu = new Menu();
        mh = new MenuHandler(this);
        this.addKeyListener(mh);
    }

    public void setup() {

        // ? map setup
        mapSetup = new MapSetup();
        // ? tile setup
        tileSetup = new TileSetup();
        tileRender = new TileRender(this);
        // item setup
        itemSetup = new ItemSetup();
        itemRender = new ItemRender(this);
        
        // NPC
        this.npcSetup = new NPCSetup(this);
        this.npcRender = new NPCRender(this);
        this.interactHandler = new InteractHandler(this);
        this.addKeyListener(interactHandler);
        
        // MONSTER
        this.monsterSetup = new MonsterSetup(this);
        this.monsterRender = new MonsterRender(this);

        // ? character setup
        character = new Character(this);
        characterRender = new CharacterRender(this);

        // ui
        ui = new UserInterface(this);

        // event
        ch = new CharacterHandler(this);

        /* add event */
        // add character handler event
        this.addKeyListener(ch);

        /// setup game state
        gameState = GameState.PLAY_STATE;

        // Bag Handler
        bh = new BagHandler(this);
        this.addKeyListener(bh);
    }

    public void removeKeyListener() {
        this.removeKeyListener(bh);
        this.removeKeyListener(ch);
        this.removeKeyListener(interactHandler);
    }

    // UPDATE
    void update() {
        if (gameScreen == GameScreen.MENU_SCREEN) {
            gameState = GameState.NOT_STATE;
            menu.update();
        }

        if (gameState == GameState.PLAY_STATE) {
            // if set FPS -> setFPS + setTimeDrawOneFrame

            // update item
            itemSetup.update();
            
            // update npc
            this.npcRender.update(this);
            
            // MONSTER
            this.monsterSetup.update(this);
            this.monsterRender.update(this);
            

            // update character
            character.update(this);

            // ui
            ui.update();
        } else if (gameState == GameState.DIE_STATE){
            this.ui.onAlertDie = true;
        }
    }

    // RENDER
    @Override
    public void paintComponent(Graphics g) {
        if (gameScreen == GameScreen.PLAY_SCREEN) {
            // background
            g.setColor(new java.awt.Color(0, 255, 255));
            // tile
            tileRender.render(g);
            // item
            itemRender.render(g);

            // npc
            this.npcRender.render(g, this);
            
            // MONSTER
            this.monsterRender.render(g);
            
            // character (player)
            characterRender.render(g);
            
            // ui
            ui.render(g);
        } else if (gameScreen == GameScreen.MENU_SCREEN) {
            // menu
            menu.render(g, this);
        }

        g.dispose();
    }

    public void run() {
        running = true;
        timeDrawOneFrame = Setting.ONE_BILION / setting.FPS;
        lastTime = System.nanoTime();
        delta = 0;
        timeOfFps = 0;
        fps = setting.FPS;
        int counterFrame = 0;
        while (running) {
//            System.out.printf("Screen(%d - %d)\n", this.getWidth(), this.getHeight());
            // FPS control
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / timeDrawOneFrame;
            timeOfFps += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                delta--;
                update();
                repaint();
                counterFrame++;
//                System.err.println("Width: " + this.getWidth());
//                System.out.println("Height: " + this.getHeight());
            }

            // set fps
            if (timeOfFps >= Setting.ONE_BILION) {
                fps = counterFrame;
                counterFrame = 0;
                timeOfFps = 0;
            }
//            System.out.printf("FPS(%d)\n", fps);
        }
    }
}
