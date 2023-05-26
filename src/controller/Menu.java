package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.define.GameScreen;
import model.define.GameState;
import setting.Setting;
import view.GamePanel;

public class Menu {

    public static final String NEW_GAME = "NEW GAME";
    public static final int NEW_GAME_VALUE = 1;

    public static final String LOAD_GAME = "LOAD GAME";
    public static final int LOAD_GAME_VALUE = 2;

    public static final String SETTING = "SETTING";
    public static final int SETTING_VALUE = 3;

    public static final String EXIT = "EXIT";
    public static final int EXIT_VALUE = 0;

    public ArrayList<String> menus;
    public ArrayList<Integer> values;

    public BufferedImage image;

    public Menu() {

        this.menus = new ArrayList<String>();
        this.values = new ArrayList<Integer>();

        this.menus.add(NEW_GAME);
        this.values.add(NEW_GAME_VALUE);

        this.menus.add(SETTING);
        this.values.add(SETTING_VALUE);

        this.menus.add(EXIT);
        this.values.add(EXIT_VALUE);

        this.image = (new LoadImage()).getImage("/image/entity/character/move/down1.png");
    }

    public void control(GamePanel gp) {
        switch (this.values.get(this.key)) {
            case NEW_GAME_VALUE:
                newGame(gp);
                break;
            case LOAD_GAME_VALUE:
                loadGame(gp);
                break;
            case SETTING_VALUE:
                setting(gp);
                break;
            case EXIT_VALUE:
                exit();
                break;
        }
    }

    public void newGame(GamePanel gp) {
//        System.err.println("New game");
        gp.gameScreen = GameScreen.PLAY_SCREEN;
        gp.removeKeyListener();
        gp.setup();
    }

    public void loadGame(GamePanel gp) {
//        System.err.println("Load game");
        gp.gameScreen = GameScreen.PLAY_SCREEN;
        gp.gameState = GameState.PLAY_STATE;
    }

    public void exit() {
        System.exit(0);
    }

    public void setting(GamePanel gp) {
//        System.err.println("Setting");
    }

    public boolean checkExists(String item) {
        for (String s : menus) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    int counterSprite = 0;
    boolean flag = true;
    public void update() {
        if(++counterSprite >= Setting.FPS / 2){
            counterSprite = 0;
            if(flag){
               if(++k1 > 10){
                   flag = !flag;
               } 
            } else {
                if(--k1 < -10){
                   flag = !flag;
               }
            }
        }
    }

    public int key = 0;
    int k1 = 0;
    int k2 = 10;
    public void render(Graphics g, GamePanel gp) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gp.getWidth(), gp.getHeight());

        // HEADER
        String title = Setting.GAME_TITLE;
        g.setFont(new Font("Times New Romans", Font.BOLD, 100));
        int x = gp.getWidth() / 2 - (int) (g.getFontMetrics().getStringBounds(title, g).getWidth() / 2);
        int y = (int) (gp.getHeight() * 0.3);
        
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(title, x + k1, y + k2);
        
        g.setColor(Color.CYAN);
        g.drawString(title, x, y);
        
        // MENU
        x = (int) (gp.getWidth() * 0.3);
        y = (int) (gp.getHeight() * 0.5);
        int space = 70;

        int xSelect = x - 2 * space;
        g.drawImage(this.image,
                xSelect,
                key * space + y,
                space,
                space,
                null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.BOLD, space));
        for (int i = 0; i < this.menus.size(); i++) {
            g.drawString(this.menus.get(i), x, y + i * space + space);
        }
    }
}
