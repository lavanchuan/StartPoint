package controller.render;

import controller.Position;
import java.awt.Color;
import java.awt.Graphics;

import view.GamePanel;

import model.item.Item;

import model.entity.Character;
import model.setup.MapSetup;

import setting.Setting;

public class ItemRender {
    
    GamePanel gp;
    
    
    public ItemRender(GamePanel gp){
        this.gp = gp;
    }
    
    public void render(Graphics g){
        Item[] items = this.gp.itemSetup.items;
        Position[] ps = this.gp.itemSetup.posits;
        Character character = this.gp.character;
        int x;
        int y;
        
        for(int i = 0; i < items.length; i++){
            if(items[i] != null && ps[i] != null){
//                System.out.printf("Character: %d %d\n", character.worldX, 
//                        character.worldY);
//                System.err.printf("Item: %d %d\n", ps[i].x, ps[i].y);
                if(ps[i].x + Setting.tileSize > character.worldX - this.gp.getWidth()/2
                        && ps[i].x - Setting.tileSize < character.worldX + this.gp.getWidth()/2
                        && ps[i].y + Setting.tileSize > character.worldY - this.gp.getHeight()/2
                        && ps[i].y - Setting.tileSize < character.worldY + this.gp.getHeight()/2){
                    x = ps[i].x - character.worldX + this.gp.getWidth()/2 - Setting.tileSize/2;
                    y = ps[i].y - character.worldY + this.gp.getHeight()/2 - Setting.tileSize/2;
                    g.drawImage(items[i].image, x, y, Setting.tileSize, Setting.tileSize, null);
                    
                    
                    // draw areaCollision
//                    g.setColor(Color.red);
//                    g.fillRect(x + items[i].areaCollision.x, 
//                            y + items[i].areaCollision.y, 
//                            items[i].areaCollision.width, 
//                            items[i].areaCollision.height);
                }
            }
        }

    }
}
