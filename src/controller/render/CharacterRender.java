package controller.render;

import controller.TimeFormat;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.bag.Bag;

import view.GamePanel;
import model.entity.Character;
import setting.Setting;

public class CharacterRender {

    GamePanel gp;
    Character character;

    public CharacterRender(GamePanel gp) {
        this.gp = gp;
        this.character = this.gp.character;
    }

    // RENDER BAG
    int xSelect;
    int ySelect;
    public int colSelect = 0;
    public int rowSelect = 0;
    public String itemDescript = "";

    public void renderBag(Graphics g) {
        Bag bag = this.character.bag;
        int x = (int) ((double) this.gp.getWidth() * 0.2);
        int y = (int) (this.gp.getHeight() * 0.2);
        int width = (int) (this.gp.getWidth() * 0.6);
        int height = (int) (this.gp.getHeight() * 0.6);

        g.setColor(new Color(100, 120, 220, 240));
        g.fillRect(x, y, width, height);

        int heightHeader = (int) (height * 0.1);

        int heightBody = height - heightHeader;
        int marginX = (int) (width * 0.02);
        int marginY = (int) (heightBody * 0.02);
        int widthItem = (width - (marginX * (Bag.MAX_COL + 1))) / Bag.MAX_COL;
        int heightItem = (heightBody - (marginY * (Bag.MAX_ROW + 1))) / Bag.MAX_ROW;

        // select
        int xSelectDF = x + marginX;
        int ySelectDF = y + heightHeader + marginY;
        xSelect = xSelectDF + colSelect * (widthItem + marginX);
        ySelect = ySelectDF + rowSelect * (heightItem + marginY);
        g.setColor(Color.CYAN);
        g.drawRect(xSelect, ySelect, widthItem, heightItem);

        // get descriptiom
        int index = rowSelect * Bag.MAX_ROW + colSelect;
        if (index >= bag.items.size()) {
            itemDescript = "";
        } else {
            itemDescript = bag.items.get(index).description;
        }

        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.setColor(new Color(100, 240, 10));
        g.drawString(itemDescript, x + marginX, y + marginY + 20);

        int counter = 0;
        for (int row = 0; row < Bag.MAX_ROW; row++) {
            for (int col = 0; col < Bag.MAX_COL; col++) {
                if (counter < bag.items.size()) {
                    g.drawImage(bag.items.get(counter).image,
                            x + marginX + col * (widthItem + marginX),
                            y + heightHeader + marginY + row * (heightItem + marginY),
                            widthItem,
                            heightItem,
                            null);
                    // quantity
                    g.setColor(Color.CYAN);
                    g.setFont(new Font("Times New Roman", Font.BOLD, 20));
                    g.drawString(bag.quantites.get(counter) + "",
                            x + marginX + col * (widthItem + marginX),
                            y + heightHeader + marginY + row * (heightItem + marginY) + 20);
                }
                counter++;
            }
        }

        // select
//        int xSelectDF = x + marginX;
//        int ySelectDF = y + heightHeader + marginY;
//        xSelect = xSelectDF + colSelect * (widthItem + marginX);
//        ySelect = ySelectDF + rowSelect * (heightItem + marginY);
//        g.drawRect(xSelect, ySelect, widthItem, heightItem);
    }

    // RENDER SPEED
    public boolean onSpeed = false;

    public void renderSpeed(Graphics g) {
        int x = 10;
        int y = 10;
        g.setColor(new Color(0, 255, 255));
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        String text = "SPEED: " + this.character.speed;
        g.drawString(text,
                x,
                y + 30);
    }

    // RENDER ITEM USING
    public boolean onItemUsing = true;

    public void renderItemUsing(Graphics g) {
        int width = 300;
        int x = this.gp.getWidth() - width;
        int xTime = x + 150;
        int y = this.gp.getHeight()/2;
        int space = 20;
        g.setFont(new Font("Times New Roman", Font.BOLD, space));
        for (int i = 0; i < this.character.itemUsing.size(); i++) {
            g.setColor(new Color(100, 240, 10));
            g.drawString(this.character.itemUsing.get(i).name, x, y + i * space);
            if(this.character.timeUsing.get(i) < Setting.FPS){
                g.setColor(new Color(255, 0, 0));
            }
            g.drawString(TimeFormat.getDateTime(this.character.timeUsing.get(i)), xTime, y + i * space);
        }
    }
    
    /*** INFO ***/
    public boolean onInfo = false;
    void infoRender(Graphics g){
        int margin = Setting.tileSize;
        int space = 20;
        g.setFont(new Font("Times New Roman", Font.BOLD, space));
        g.setColor(new Color(255, 255, 255));
        int width = (int)(this.gp.getWidth() * 0.3);
        int x = this.gp.getWidth() - width + margin;
        int y = margin;
        int xValue = x + (int)(width * 0.6);
        // cureent health
        g.drawString("Health", x, y);
        g.drawString(""+this.character.currentHealth, xValue, y);
        y+= space;
        
        // cureent mana
        g.drawString("Mana", x, y);
        g.drawString(""+this.character.currentMana, xValue, y);
        y+= space;
        
        // health recovery
        g.drawString("Health recovery", x, y);
        g.drawString(""+this.character.getResHealth(), xValue, y);
        y+= space;
        
        // mana recovery
        g.drawString("Mana recovery", x, y);
        g.drawString(""+this.character.getResMana(), xValue, y);
        y+= space;
        
        // Damage
        g.drawString("Damage", x, y);
        g.drawString(""+this.character.damageNormal, xValue, y);
        y+= space;
        
        // speed
        g.drawString("Speed", x, y);
        g.drawString(""+this.character.speed, xValue, y);
        y+= space;

    }
    
    // AREA ATTACK
    public boolean onAreaAttack = false;
    void areaDamageRender(Graphics g){
        int width = this.character.areaDamage.width;
        int height = this.character.areaDamage.height;
        
        int x = this.character.areaDamage.x - this.character.worldX + 
                this.gp.getWidth()/2 - this.character.width/2; 
        int y = this.character.areaDamage.y - this.character.worldY + 
                this.gp.getHeight()/2 - this.character.height/2;
        
        g.setColor(new Color(255, 0, 0, 100));
        g.fillRect(x, y, width, height);
    }
    
    void areaDamage(Graphics g){
        int width = this.character.areaDamage.width;
        int height = this.character.areaDamage.height;
        
        int x = this.character.areaDamage.x - this.character.worldX + 
                this.gp.getWidth()/2 - this.character.width/2; 
        int y = this.character.areaDamage.y - this.character.worldY + 
                this.gp.getHeight()/2 - this.character.height/2;

        g.setColor(new Color(255, 255, 255));
        g.fillRect(x, y, width, height);
    }

    public void render(Graphics g) {
        
        if(this.gp.ch.attackPress){
            areaDamage(g);
        }
        
        g.drawImage(character.image,
                this.gp.character.screenX - this.gp.character.width / 2,
                this.gp.character.screenY - this.gp.character.height / 2,
                character.width,
                character.height,
                null);
        
        if(true){
            (new HealthRender()).render(g, gp);
            (new ManaRender()).render(g, gp);
        }
        
//        g.setColor(Color.red);
//        g.fillRect(this.character.screenX - this.character.width/2, 
//                this.character.screenY - this.character.height/2, 
//                this.character.areaCollision.width, 
//                this.character.areaCollision.height);

        // render bag
        if (this.character.onBag) {
            renderBag(g);
        }

        // render speed
        if (onSpeed) {
            renderSpeed(g);
        }

        // item using
        if (onItemUsing) {
            renderItemUsing(g);
        }
        
        // INFO
        if(onInfo){
            infoRender(g);
        }
        
        // AREA ATTACK
        if(onAreaAttack){
            areaDamageRender(g);
        }
    }
}
