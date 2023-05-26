package model.item;

import java.awt.image.BufferedImage;

import controller.LoadImage;
import java.awt.Rectangle;
import model.define.ItemType;
import setting.Setting;

public class Item {

    public String name = "Item strage";
    public boolean collision = false;
    public boolean pick = true;
    public boolean use = false;
    public String description = "Item strange";
    public BufferedImage image;
    public int timeActive = Setting.FPS;
    public boolean actived = false;
    public int itemType = ItemType.NORMAL;

    // collision => active
    public Rectangle areaCollision;

    public Item(String name) {
        this.name = name;
        areaCollision = new Rectangle(Setting.tileSize / 4,
                Setting.tileSize / 4,
                Setting.tileSize / 2,
                Setting.tileSize / 2);
    }
    
    public void update(){}
    
    public void updateTimeActive(){
        if(actived){
            if(--timeActive <= 0){
                timeActive = Setting.FPS;
                actived = false;
            }
        }
    }

    public BufferedImage getImage(String name) {
        return (new LoadImage()).getImage("/image/item/" + name + ".png");
    }
}
