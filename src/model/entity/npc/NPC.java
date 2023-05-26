package model.entity.npc;

import java.awt.Rectangle;
import model.define.Communicates;
import model.entity.Entity;
import view.GamePanel;

public class NPC extends Entity{
    
    public Communicates comm;
    
    protected int sizeInterac = 5;
    
    public boolean onInterac = false;
    
    public NPC(GamePanel gp) {
        super(gp);
    }
    
    public void setup(){}
    
    public boolean checkDetectedCharacter(){
//        System.err.printf("character: (%d, %d)\n", this.gp.character.worldX, this.gp.character.worldY);
//        System.out.println(this.gp.character.areaCollision);
        Rectangle rect = this.areaCollision;
        rect.x = this.worldX - this.areaCollision.width/2 + this.width/2;
        rect.y = this.worldY - this.areaCollision.height/2 + this.height/2;
        if(rect.intersects(this.gp.character.areaCollision)){
            return true;
        }
        return false;
    }
    
}
