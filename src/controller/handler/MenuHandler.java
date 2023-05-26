package controller.handler;

import controller.Menu;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.define.GameScreen;
import view.GamePanel;

public class MenuHandler implements KeyListener {

    GamePanel gp;

    public MenuHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.gp.gameScreen == GameScreen.MENU_SCREEN) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (--this.gp.menu.key < 0) {
                    this.gp.menu.key = this.gp.menu.menus.size() - 1;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (++this.gp.menu.key > this.gp.menu.menus.size() - 1) {
                    this.gp.menu.key = 0;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.gp.menu.control(this.gp);
            }
        } else if (this.gp.gameScreen == GameScreen.PLAY_SCREEN) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.gp.gameScreen = GameScreen.MENU_SCREEN;
                if (!this.gp.menu.checkExists(Menu.LOAD_GAME)) {
                    this.gp.menu.menus.add(1, Menu.LOAD_GAME);
                    this.gp.menu.values.add(1, Menu.LOAD_GAME_VALUE);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
