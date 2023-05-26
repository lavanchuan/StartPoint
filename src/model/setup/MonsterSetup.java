package model.setup;

import java.util.ArrayList;

import view.GamePanel;

import setting.Setting;

import model.entity.monster.Monster;
import model.entity.monster.ORC;

public class MonsterSetup {
    // property
    public ArrayList<Monster> monsters;
    
    // reference
    GamePanel gp;
    
    public MonsterSetup(GamePanel gp){
        this.gp = gp;
        this.monsters = new ArrayList<Monster>();
        setup();
    }
    
    void setup(){
        int marginWidth = MapSetup.MARGIN.width * Setting.tileSize;
        int marginHeight = MapSetup.MARGIN.height * Setting.tileSize;
        int row;
        int col;
        // ORC
        row = 10;
        col = 10;
        ORC orc = new ORC(this.gp, marginWidth + col * Setting.tileSize, marginHeight + row * Setting.tileSize);
        monsters.add(orc);
        
        col = 12;
        orc = new ORC(this.gp, marginWidth + col * Setting.tileSize, marginHeight + row * Setting.tileSize);
        monsters.add(orc);
        
        col = 10;
        row = 12;
        orc = new ORC(this.gp, marginWidth + col * Setting.tileSize, marginHeight + row * Setting.tileSize);
        monsters.add(orc);
        
        
        /// 
        row = 32;
        col = 10;
        orc = new ORC(this.gp, marginWidth + col * Setting.tileSize, marginHeight + row * Setting.tileSize);
        monsters.add(orc);
    }
    
    public void update(GamePanel gp){
        this.gp = gp;
        for(int i = 0; i < this.monsters.size(); i++){
            if(this.monsters.get(i) != null){
                Monster monster = this.monsters.get(i);
                monster.update(gp);
                this.monsters.set(i, monster);
            }
        }
    }
}
