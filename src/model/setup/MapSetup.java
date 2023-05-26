package model.setup;

import controller.LoadMap;
import java.awt.Dimension;

import setting.Setting;

public class MapSetup {
    public int[][] map;
    public static Dimension MARGIN = new Dimension(30, 20);
    
    public MapSetup(){
        String src = (new Setting()).getSrcWorldMap();
        map = (new LoadMap()).getMap(src);
        map = addLimitMap(MARGIN.width, MARGIN.height, map, 0);
    }
    
    public int[][] addLimitMap(int w, int h, int map[][], int tile){
        int[][] rs = new int[map.length + h * 2][map[0].length + w * 2];
        for(int row = 0; row < rs.length; row++){
            for(int col = 0; col < rs[0].length; col++){
                if(row < h || row > rs.length - h - 1){
                    rs[row][col] = tile;
                } else if (col < w || col > rs[0].length - w - 1){
                    rs[row][col] = tile;
                } else {
                    rs[row][col] = map[row - h][col - w];
                }
            }
        }
        return rs;
    }
    
    public void readMap(int[][] map){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static int worldXReal(int worldX){
        return worldX - MARGIN.width * Setting.tileSize;
    }
    
    public static int worldYReal(int worldY){
        return worldY - MARGIN.height * Setting.tileSize;
    }
    
    public static void main(String[] agrs){
        int[][] x = new int[5][5];
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x[0].length; j++){
                x[i][j] = j+1;
            }
        }
        
        MapSetup ms = new MapSetup();
        x = ms.addLimitMap(3, 2, x, 0);
        ms.readMap(x);
    }
}
