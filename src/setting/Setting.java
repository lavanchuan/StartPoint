package setting;

import java.awt.Dimension;

public class Setting {
    
    // INFO
    public static final String GAME_TITLE = "START POINT";
    private final String gameName = "Start Point";
    private final String author = "La Văn Chuân";
    
    // DEFINE
    public static final long ONE_BILION = 1000000000;
    
    // GAME SIZE
    public static final int tileSize = 48;
    private int width = 20 * tileSize;
    private int height = 15 * tileSize;
    private Dimension size;
    
    // GAME RUN
    public static int FPS = 60;
    
    // MAP DEFAULT
    String srcWorldMap = "/map/worldmap.txt";
    
    public Setting(){
        size = new Dimension(this.width, this.height); // 960 * 720
    }
    
    public Dimension getScreenSize(){
        return size;
    }
    
    public String getGameName(){
        return this.gameName;
    }
    
    public String getSrcWorldMap(){
        return this.srcWorldMap;
    }
    
    /*** ALERT DIE ***/
    public static String getAlertDie(String name){
        return "You were defeated by " + name;
    }
}
