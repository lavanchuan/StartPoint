package model.entity;

import java.awt.image.BufferedImage;

import controller.LoadImage;
import controller.Position;

import java.awt.Rectangle;
import setting.Setting;
import model.define.Direction;
import model.item.Item;
import view.GamePanel;

public class Entity {

    public int worldX;
    public int worldY;

    public int screenX;
    public int screenY;

    public int width = Setting.tileSize;
    public int height = Setting.tileSize;

    public int speed;
    public int speedUnit;

    public BufferedImage image;

    public int direct = Direction.STAND;
    public int oldDirect = Direction.STAND;
    
    public Rectangle areaCollision;

    protected GamePanel gp;
    
    public String name;
    
    public boolean interacting = false;
    public boolean canInteract = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
        areaCollision = new Rectangle(0, 0, this.width, this.height);
    }

    public boolean checkTile() {
        int col1, col2;
        int row1, row2;
        switch (this.direct) {
            case Direction.UP:
                col1 = (this.worldX) / Setting.tileSize;
                col2 = col1 + 1;
                row1 = (this.worldY)/ Setting.tileSize;
                if (this.gp.tileSetup.tiles[this.gp.mapSetup.map[row1][col1]].collision
                        || this.gp.tileSetup.tiles[this.gp.mapSetup.map[row1][col2]].collision) {
                    return false;
                }
                break;
            case Direction.DOWN:
                col1 = (this.worldX) / Setting.tileSize;
                col2 = col1 + 1;
                row1 = (this.worldY) / Setting.tileSize + 1;
                if (this.gp.tileSetup.tiles[this.gp.mapSetup.map[row1][col1]].collision
                        || this.gp.tileSetup.tiles[this.gp.mapSetup.map[row1][col2]].collision) {
                    return false;
                }
                break;
            case Direction.LEFT:
                col1 = (this.worldX) / Setting.tileSize;
                row1 = (this.worldY) / Setting.tileSize;
                row2 = row1 + 1;
                if (this.gp.tileSetup.tiles[this.gp.mapSetup.map[row1][col1]].collision
                        || this.gp.tileSetup.tiles[this.gp.mapSetup.map[row2][col1]].collision) {
                    return false;
                }
                break;
            case Direction.RIGHT:
                col1 = (this.worldX) / Setting.tileSize + 1;
                row1 = (this.worldY) / Setting.tileSize;
                row2 = row1 + 1;
                if (this.gp.tileSetup.tiles[this.gp.mapSetup.map[row1][col1]].collision
                        || this.gp.tileSetup.tiles[this.gp.mapSetup.map[row2][col1]].collision) {
                    return false;
                }
                break;
        }
        return true;
    }
    
    public boolean checkItem(){
        Item[] items = this.gp.itemSetup.items;
        Position[] ps = this.gp.itemSetup.posits;
        Rectangle rect1 = new Rectangle(
                this.worldX + this.gp.character.width/2, 
                this.worldY + this.gp.character.height/2, 
                this.width, 
                this.height);
        Rectangle rect2;
        for(int i = 0; i < items.length; i++){
            if(items[i] != null && ps[i] != null && items[i].collision){
                rect2 = new Rectangle(
                        ps[i].x + this.gp.character.width/2,
                        ps[i].y + this.gp.character.height/2,
                        items[i].areaCollision.width,
                        items[i].areaCollision.height
                );
                if(rect1.intersects(rect2)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check() {
        if (!checkTile()) {
            return false;
        }
        
        if(!checkItem()){
            return false;
        }
        
        return true;
    }

    public void left() {
        int s = 0;
        while (s < this.speed) {
            s += speedUnit;
            this.worldX -= this.speedUnit;
            if (!check()) {
                this.worldX += speedUnit;
                break;
            }
        }
    }

    public void right() {
        int s = 0;
        while (s < this.speed) {
            s += speedUnit;
            this.worldX += this.speedUnit;
            if (!check()) {
                this.worldX -= speedUnit;
                break;
            }
        }
    }

    public void up() {
        int s = 0;
        while (s < this.speed) {
            s += speedUnit;
            this.worldY -= this.speedUnit;
            if (!check()) {
                this.worldY += speedUnit;
                break;
            }
        }
    }

    public void down() {
        int s = 0;
        while (s < this.speed) {
            s += speedUnit;
            this.worldY += this.speedUnit;
            if (!check()) {
                this.worldY -= speedUnit;
                break;
            }
        }
    }

    public void move() {
        switch (this.direct) {
            case Direction.UP:
                up();
                break;
            case Direction.DOWN:
                down();
                break;
            case Direction.LEFT:
                left();
                break;
            case Direction.RIGHT:
                right();
                break;
        }
    }

    public void updateDirect() {
    }

    public void updateImage() {
    }

    protected int counter = 0;
    protected int timeChange = 10;

    public void updateImageAnimation() {
    }
    
    public void update(GamePanel gp){
        this.gp = gp;
    }

    public void update() {
        updateDirect();
        updateImage();
        move();
//        System.out.printf("Position: (%d, %d)\n", this.worldX, this.worldY);
    }

    protected BufferedImage loadImage(String type, String action, String name) {
        return (new LoadImage()).getImage("/image/entity/" + type + "/" + action + "/" + name + ".png");
    }
}
