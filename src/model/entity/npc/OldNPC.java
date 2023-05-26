package model.entity.npc;

import controller.MyRandom;
import java.awt.image.BufferedImage;
import model.define.Communicates;
import model.define.Direction;
import setting.DataCommOldNPC;
import view.GamePanel;
import setting.Setting;

public class OldNPC extends NPC {

    public static final String NAME = "Village elder";

    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;

    public OldNPC(GamePanel gp, int worldX, int worldY) {
        super(gp);
        this.name = NAME;

        this.worldX = worldX;
        this.worldY = worldY;

        setup();
    }

    @Override
    public void setup() {

        this.comm = new Communicates((new DataCommOldNPC()).msgs);
        this.speed = 1;
        this.speedUnit = 1;

        loadImage();
        this.image = down1;
        
        // area detected player
        this.areaCollision.width = this.width * this.sizeInterac;
        this.areaCollision.height = this.height * this.sizeInterac;
    }

    public void loadImage() {
        this.up1 = this.loadImage("npc/oldNpc", "move", "up1");
        this.up2 = this.loadImage("npc/oldNpc", "move", "up2");
        this.down1 = this.loadImage("npc/oldNpc", "move", "down1");
        this.down2 = this.loadImage("npc/oldNpc", "move", "down2");
        this.left1 = this.loadImage("npc/oldNpc", "move", "left1");
        this.left2 = this.loadImage("npc/oldNpc", "move", "left2");
        this.right1 = this.loadImage("npc/oldNpc", "move", "right1");
        this.right2 = this.loadImage("npc/oldNpc", "move", "right2");
    }

    int counterDraw = 0;

    public void changeDirect() {
        if (++counterDraw >= Setting.FPS * 2) {
            counterDraw = 0;
            int min = 1000;
            int max = 9999;
            int rd = MyRandom.getInt(min, max);
            switch (rd % (Direction.MAX_DIRECT + 1)) {
                case Direction.LEFT:
                    this.direct = Direction.LEFT;
                    break;
                case Direction.RIGHT:
                    this.direct = Direction.RIGHT;
                    break;
                case Direction.UP:
                    this.direct = Direction.UP;
                    break;
                case Direction.DOWN:
                    this.direct = Direction.DOWN;
                    break;
                case Direction.STAND:
                    this.direct = Direction.STAND;
                    break;
            }
        }
    }

    @Override
    public void updateDirect() {
//        System.err.printf("[%s] %d\n", this.name, this.direct);
        changeDirect();
    }

    int counterAnimation = 0;

    @Override
    public void updateImageAnimation() {
        if (++counterAnimation >= Setting.FPS / 3) {
            counterAnimation = 0;
            switch (this.direct) {
                case Direction.UP:
                    if(this.image == up1){
                        this.image = up2;
                    } else {
                        this.image = up1;
                    }
                    break;
                case Direction.DOWN:
                    if(this.image == down1){
                        this.image = down2;
                    } else {
                        this.image = down1;
                    }
                    break;
                case Direction.LEFT:
                    if(this.image == left1){
                        this.image = left2;
                    } else {
                        this.image = left1;
                    }
                    break;
                case Direction.RIGHT:
                    if(this.image == right1){
                        this.image = right2;
                    } else {
                        this.image = right1;
                    }
                    break;
            }
        }
    }

    @Override
    public void updateImage() {
        
        if (oldDirect != direct) {
            counterAnimation = 0;
            switch (this.direct) {
                case Direction.UP:
                    this.image = up1;
                    break;
                case Direction.DOWN:
                    this.image = down1;
                    break;
                case Direction.LEFT:
                    this.image = left1;
                    break;
                case Direction.RIGHT:
                    this.image = right1;
                    break;
            }
            oldDirect = direct;
        } else {
            updateImageAnimation();
        }

    }

    @Override
    public void update() {
        if (!interacting) {
            updateDirect();
            updateImage();
            move(); 
        } else {
            
        }
        
        if(checkDetectedCharacter()){
                this.canInteract = true;
            } else {
                this.canInteract = false;
                this.onInterac = false;
                this.interacting = false;
            }
    }

}
