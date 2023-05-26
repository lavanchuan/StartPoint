package run;

import view.GamePanel;
import javax.swing.JFrame;

import setting.Setting;

public class GameScreen {
    JFrame window;
    GamePanel gp;
    Setting setting;
    
    public GameScreen(){
        setting = new Setting();
        
        window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setTitle(setting.getGameName());
        // add panel
        gp = new GamePanel();
        window.add(gp);
        // pack
        window.pack();
        
        // set posision center
        window.setLocationRelativeTo(null);
        
        // show window
        window.setVisible(true);
        
        // start game
        gp.run();
    }
}
