package controller.render;

import java.awt.Graphics;

import setting.Setting;

import view.GamePanel;

import model.tile.Tile;

public class TileRender {
    
    int map[][];
    Tile tiles[];
    GamePanel gp;
    
    public TileRender(GamePanel gp){
        this.gp = gp;
        this.tiles = this.gp.tileSetup.tiles;
        this.map = this.gp.mapSetup.map;
    }
    
    public void render(Graphics g){
        
        int worldX;
        int worldY;
        int screenX;
        int screenY;
        int screenWidth = gp.getWidth();
        int screenHeight = gp.getHeight();
        
        int minX = this.gp.character.worldX - screenWidth/2;
        int minY = this.gp.character.worldY - screenHeight/2;
        int maxX = minX + screenWidth;
        int maxY = minY + screenHeight;
        
        int mapRow, mapCol; // map reference
        
        int marginLeft = Setting.tileSize * (minX/Setting.tileSize) - minX;
        int marginTop = Setting.tileSize * (minY/Setting.tileSize) - minY;
//        System.out.printf("[margin: %d, %d]\n", marginLeft, marginTop);
        
        int maxRow = screenHeight/Setting.tileSize + 2;
        int maxCol = screenWidth/Setting.tileSize + 2;
        
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                screenX = col * Setting.tileSize + marginLeft;
                screenY = row * Setting.tileSize + marginTop;
//                System.out.printf("[Screen: %d, %d]\n", screenX, screenY);
                
                worldX = this.gp.character.worldX - (this.gp.character.screenX - screenX);
                worldY = this.gp.character.worldY - (this.gp.character.screenY - screenY);
                
                mapRow = worldY / Setting.tileSize;
                mapCol = worldX / Setting.tileSize;
                
                g.drawImage(tiles[map[mapRow][mapCol]].image, 
                        screenX - Setting.tileSize/2,
                        screenY - Setting.tileSize/2,
                        Setting.tileSize,
                        Setting.tileSize,
                        null);
            }
        }
    }
    
    
}
