package model.setup;

import java.util.ArrayList;

import model.entity.npc.NPC;
import model.entity.npc.OldNPC;
import setting.Setting;
import view.GamePanel;

public class NPCSetup {
    
    public ArrayList<NPC> npcs;
    
    // references
    GamePanel gp;
    
    public NPCSetup(GamePanel gp){
        this.gp = gp;
        this.npcs = new ArrayList<NPC>();
        setup();
    }
    
    public void setup(){
        int marginWidth = MapSetup.MARGIN.width * Setting.tileSize;
        int marginHeight = MapSetup.MARGIN.height * Setting.tileSize; 
        int row;
        int col;
        
        // OLD NPC
        row = 10;
        col = 17;
        OldNPC oldNPC = new OldNPC(this.gp, 
                marginWidth + col * Setting.tileSize,
                marginHeight + row * Setting.tileSize);
        this.npcs.add(oldNPC);
        
        row = 10;
        col = 10;
        oldNPC = new OldNPC(this.gp, 
                marginWidth + col * Setting.tileSize,
                marginHeight + row * Setting.tileSize);
        this.npcs.add(oldNPC);
        
        row = 60;
        col = 60;
        oldNPC = new OldNPC(this.gp, 
                marginWidth + col * Setting.tileSize,
                marginHeight + row * Setting.tileSize);
        this.npcs.add(oldNPC);
        
        
        row = 60;
        col = 10;
        oldNPC = new OldNPC(this.gp, 
                marginWidth + col * Setting.tileSize,
                marginHeight + row * Setting.tileSize);
        this.npcs.add(oldNPC);
        
        row = 10;
        col = 60;
        oldNPC = new OldNPC(this.gp, 
                marginWidth + col * Setting.tileSize,
                marginHeight + row * Setting.tileSize);
        this.npcs.add(oldNPC);
        
        row = 30;
        col = 30;
        oldNPC = new OldNPC(this.gp, 
                marginWidth + col * Setting.tileSize,
                marginHeight + row * Setting.tileSize);
        this.npcs.add(oldNPC);
    }
    
}
