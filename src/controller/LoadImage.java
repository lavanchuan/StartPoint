package controller;

import java.io.IOException;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class LoadImage {
    
    public BufferedImage getImage(String src){
        BufferedImage img = null;
        try{
            img = ImageIO.read(getClass().getResourceAsStream(src));
        } catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
