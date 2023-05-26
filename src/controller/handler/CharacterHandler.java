package controller.handler;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import model.define.GameScreen;

import view.GamePanel;
// import game state
import model.define.GameState;

public class CharacterHandler implements KeyListener {

    GamePanel gp;

    public CharacterHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean upPress = false;
    public boolean downPress = false;
    public boolean leftPress = false;
    public boolean rightPress = false;
    
    public boolean attackPress = false;

//    @Override
//    public void keyPressed(KeyEvent e){
//        int keyCode = e.getKeyCode();
//        if(gp.gameState == GameState.PLAY_STATE){
//            if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
//                this.gp.character.worldX -= 20;
//            }
//            if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
//                this.gp.character.worldX += 20;
//            }
//            if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
//                this.gp.character.worldY -= 20;
//            }
//            if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
//                this.gp.character.worldY += 20;
//            }
//        }
//    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (gp.gameState == GameState.PLAY_STATE) {
            int source = e.getKeyCode();
            if (source == KeyEvent.VK_UP || source == KeyEvent.VK_W) {
                this.upPress = true;
            }

            if (source == KeyEvent.VK_DOWN || source == KeyEvent.VK_S) {
                this.downPress = true;
            }

            if (source == KeyEvent.VK_LEFT || source == KeyEvent.VK_A) {
                this.leftPress = true;
            }

            if (source == KeyEvent.VK_RIGHT || source == KeyEvent.VK_D) {
                this.rightPress = true;
            }
            
            if(source == KeyEvent.VK_SPACE){
                this.attackPress = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.gp.gameState == GameState.PLAY_STATE) {
            int source = e.getKeyCode();
            if (source == KeyEvent.VK_UP || source == KeyEvent.VK_W) {
                this.upPress = false;
            }

            if (source == KeyEvent.VK_DOWN || source == KeyEvent.VK_S) {
                this.downPress = false;
            }

            if (source == KeyEvent.VK_LEFT || source == KeyEvent.VK_A) {
                this.leftPress = false;
            }

            if (source == KeyEvent.VK_RIGHT || source == KeyEvent.VK_D) {
                this.rightPress = false;
            }
            
            if (source == KeyEvent.VK_P) {
                this.gp.characterRender.onSpeed = !this.gp.characterRender.onSpeed;
            }

            if (source == KeyEvent.VK_O) {
                this.gp.characterRender.onItemUsing = !this.gp.characterRender.onItemUsing;
            }

            if (source == KeyEvent.VK_I) {
                this.gp.characterRender.onInfo = !this.gp.characterRender.onInfo;
            }
            
            if(source == KeyEvent.VK_SPACE){
                this.attackPress = false;
            }
        }
        
        if(this.gp.gameState == GameState.DIE_STATE){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                this.gp.gameScreen = GameScreen.MENU_SCREEN;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
