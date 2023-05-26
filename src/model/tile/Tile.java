package model.tile;

import java.awt.image.BufferedImage;

import controller.LoadImage;

public class Tile {
    public BufferedImage image;
    String type;
    String name;
    
    public boolean collision = false;
    
    public Tile(String type, String name){
        this.type = type;
        this.name = name;
        loadImage(this.type, this.name);
        loadCollision();
    }
    
    void loadCollision(){
        switch(type){
            case "water":
            case "wall":
            case "tree":
                this.collision = true;
        }
    }
    
    void loadImage(String type, String name){
        this.image = (new LoadImage()).getImage("/image/tile/"+type+"/"+name+".png");
    }
}
