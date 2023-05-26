package controller;

import view.GamePanel;
import setting.Setting;

public class FPSControl {
    
    public static void setFPS(int FPS, GamePanel gp){
        gp.setting.FPS = FPS;
    }
    
    public static void setTimeDrawOneFrame(GamePanel gp){
        gp.timeDrawOneFrame = Setting.ONE_BILION / gp.setting.FPS;
    }
}
