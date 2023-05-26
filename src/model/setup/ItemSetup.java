package model.setup;

import controller.Position;
import setting.Setting;
import model.item.BlueShoes;
import model.item.Chest;
import model.item.Door;
import model.item.Health;
import model.item.Item;
import model.item.KeyChestItem;
import model.item.KeyItem;
import model.item.Mana;
import model.item.Sword01;
import model.item.Teleport;

public class ItemSetup {

    public static final int MAX_ITEM = 999;

    public Item[] items;
    public Position[] posits;

    public ItemSetup() {
        items = new Item[MAX_ITEM];
        posits = new Position[MAX_ITEM];

        int marginWidth = MapSetup.MARGIN.width * Setting.tileSize;
        int marginHeight = MapSetup.MARGIN.height * Setting.tileSize;

        int index = 0;

        // KEY OPEN DOOR
        KeyItem kItem = new KeyItem();
        items[index] = kItem;
        posits[index] = new Position(marginWidth + 15 * Setting.tileSize, 
                marginHeight + 23 * Setting.tileSize);
        index++;
        
        // KEY OPEN CHEST
        KeyChestItem kcItem = new KeyChestItem();
        items[index] = kcItem;
        posits[index] = new Position(marginWidth + 20 * Setting.tileSize,
                marginHeight + 10 * Setting.tileSize);
        index++;
        
        // DOOR CLOSE
        Door door = new Door();
        items[index] = door;
        posits[index] = new Position(marginWidth + 21 * Setting.tileSize,
                marginHeight + 41 * Setting.tileSize);
        index++;
        
        door = new Door();
        items[index] = door;
        posits[index] = new Position(marginWidth + 20 * Setting.tileSize,
                marginHeight + 41 * Setting.tileSize);
        index++;
        
        // CHEST LOCKED
        Chest chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 16 * Setting.tileSize, 
                marginHeight + 5 * Setting.tileSize);
        index++;
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 9 * Setting.tileSize, 
                marginHeight + 9 * Setting.tileSize);
        index++;
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 10 * Setting.tileSize, 
                marginHeight + 9 * Setting.tileSize);
        index++;
        
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 11 * Setting.tileSize, 
                marginHeight + 9 * Setting.tileSize);
        index++;
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 9 * Setting.tileSize, 
                marginHeight + 11 * Setting.tileSize);
        index++;
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 10 * Setting.tileSize, 
                marginHeight + 11 * Setting.tileSize);
        index++;
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 11 * Setting.tileSize, 
                marginHeight + 11 * Setting.tileSize);
        index++;
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 9 * Setting.tileSize, 
                marginHeight + 10 * Setting.tileSize);
        index++;
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 11 * Setting.tileSize, 
                marginHeight + 10 * Setting.tileSize);
        index++;
        
        
        chest = new Chest();
        items[index] = chest;
        posits[index] = new Position(marginWidth + 9 * Setting.tileSize, 
                marginHeight + 31 * Setting.tileSize);
        index++;

        // BLUE SHOES
        BlueShoes blueShoes = new BlueShoes();
        items[index] = blueShoes;
        posits[index] = new Position(marginWidth + 20 * Setting.tileSize, 
                marginHeight + 20 * Setting.tileSize);
        index++;
        
        items[index] = blueShoes;
        posits[index] = new Position(marginWidth + 25 * Setting.tileSize, 
                marginHeight + 5 * Setting.tileSize);
        index++;
        
        // HEALTH
        Health health = new Health();
        items[index] = health;
        posits[index] = new Position(marginWidth + 30 * Setting.tileSize,
                marginHeight + 20 * Setting.tileSize);
        index++;
        
        // MANA
        Mana mana = new Mana();
        items[index] = mana;
        posits[index] = new Position(marginWidth + 16 * Setting.tileSize,
                marginHeight + 30 * Setting.tileSize);
        index++;
        
        // SWORD01
        Sword01 sword01 = new Sword01();
        items[index] = sword01;
        posits[index] = new Position(marginWidth + 20 * Setting.tileSize,
                marginHeight + 15 * Setting.tileSize);
        index++; 
        
        items[index] = sword01;
        posits[index] = new Position(marginWidth + 18 * Setting.tileSize,
                marginHeight + 15 * Setting.tileSize);
        index++; 
        
        // TELEPORT
        Teleport teleport = new Teleport();
        teleport.currentPosition = new Position(marginWidth + 25 * Setting.tileSize, 
                marginHeight + 5 * Setting.tileSize);
        teleport.destinPosition = new Position(marginWidth + 60 * Setting.tileSize, 
                marginHeight + 60 * Setting.tileSize);
        items[index] = teleport;
        posits[index] = teleport.currentPosition;
        index++;
        
    }
    
    public void update(){
        // update time active
        for(Item item : this.items){
            if(item != null){
                item.updateTimeActive();
            }
        }
    }
}
