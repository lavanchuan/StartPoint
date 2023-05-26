package model.entity;

import controller.Position;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.bag.Bag;
import model.define.CharacterState;
import model.define.Direction;
import model.define.GameState;
import model.entity.monster.Monster;
import model.item.BlueShoes;
import model.item.Chest;
import model.item.Door;
import model.item.Health;
import model.setup.MapSetup;
import setting.Setting;
import view.GamePanel;
import model.item.Item;
import model.item.KeyChestItem;
import model.item.KeyItem;
import model.item.Mana;
import model.item.Sword01;
import model.item.Teleport;

public class Character extends Entity {

    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Bag bag;
    public boolean onBag = false;

    private int speedDF = 4;

    public int state;

    public ArrayList<Item> itemUsing;
    public ArrayList<Integer> timeUsing;

    private double resHealth = 5; // restorage parameter health
    private double resMana = 1; // restorage parameter mana

    // info
    public int damageNormal = 50;
    public int damageSkill = 100;
    public int attackSpeedS = 1;
    public int maxHealth = 500;
    public int maxMana = 100;
    public int currentHealth = maxHealth / 3;
    public int currentMana = 50;
    public int percentHealthRecovery = 1;
    public int percentManaRecovery = 1;

    // areaDamage
    public Rectangle areaDamage;

    public Character(GamePanel gp) {
        super(gp);

        this.state = CharacterState.CHARACTER_NORMAL;

        this.speed = this.speedDF;
        this.speedUnit = 1;
        this.worldX = MapSetup.MARGIN.width * Setting.tileSize + Setting.tileSize * 35;
        this.worldY = MapSetup.MARGIN.height * Setting.tileSize + Setting.tileSize * 35;
        this.width = 48;
        this.height = 48;

        this.down1 = loadImage("character", "move", "down1");
        this.down2 = loadImage("character", "move", "down2");
        this.up1 = loadImage("character", "move", "up1");
        this.up2 = loadImage("character", "move", "up2");
        this.left1 = loadImage("character", "move", "left1");
        this.left2 = loadImage("character", "move", "left2");
        this.right1 = loadImage("character", "move", "right1");
        this.right2 = loadImage("character", "move", "right2");

        this.image = this.down1;

        bag = new Bag();

        itemUsing = new ArrayList<Item>();
        timeUsing = new ArrayList<Integer>();

        // ATTACK
        this.areaDamage = new Rectangle(0, 0, 0, 0);
    }

    public double getResHealth() {
        return this.resHealth;
    }

    public double getResMana() {
        return this.resMana;
    }

    public void setResHealth(double resHealth) {
        this.resHealth = resHealth;
    }

    public void setResMana(double resMana) {
        this.resMana = resMana;
    }
    
    public void setDamage(int damage){
        this.damageNormal = damage;
    }
    
    public int getDamage(){
        return this.damageNormal;
    }

    @Override
    public void updateDirect() {
        if (gp.ch.upPress || gp.ch.downPress || gp.ch.leftPress || gp.ch.rightPress) {
            if (gp.ch.downPress) {
                this.direct = Direction.DOWN;
            }
            if (gp.ch.upPress) {
                this.direct = Direction.UP;
            }
            if (gp.ch.leftPress) {
                this.direct = Direction.LEFT;
            }
            if (gp.ch.rightPress) {
                this.direct = Direction.RIGHT;
            }
        } else {
            this.direct = Direction.STAND;
        }
//        System.out.println("DIRECT: " + this.direct);
//        System.err.println("OLD DIRECT: " + oldDirect);
    }

    @Override
    public void updateImage() {
        if (this.direct != Direction.STAND) {
            if (this.direct == this.oldDirect) {
                updateImageAnimation();
            } else {
                this.counter = 0;
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
            }
            this.oldDirect = this.direct;
        } else {
//            this.image = down1;
        }
    }

    @Override
    public void updateImageAnimation() {
        if (this.counter++ >= this.timeChange) {
            this.counter = 0;
            switch (this.direct) {
                case Direction.UP:
                    if (this.image == up1) {
                        this.image = up2;
                    } else {
                        this.image = up1;
                    }
                    break;
                case Direction.DOWN:
                    if (this.image == down1) {
                        this.image = down2;
                    } else {
                        this.image = down1;
                    }
                    break;
                case Direction.LEFT:
                    if (this.image == left1) {
                        this.image = left2;
                    } else {
                        this.image = left1;
                    }
                    break;
                case Direction.RIGHT:
                    if (this.image == right1) {
                        this.image = right2;
                    } else {
                        this.image = right1;
                    }
                    break;
            }
        }
    }

    public void pickupItem() {
        Item[] items = this.gp.itemSetup.items;
        Position ps[] = this.gp.itemSetup.posits;
        this.areaCollision.x = this.worldX;
        this.areaCollision.y = this.worldY;
        Rectangle rectItem;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && ps[i] != null) {
                if (ps[i].x + Setting.tileSize > this.worldX - this.gp.getWidth() / 2
                        && ps[i].x - Setting.tileSize < this.worldX + this.gp.getWidth() / 2
                        && ps[i].y + Setting.tileSize > this.worldY - this.gp.getHeight() / 2
                        && ps[i].y - Setting.tileSize < this.worldY + this.gp.getHeight() / 2) {
                    rectItem = new Rectangle(items[i].areaCollision.x + ps[i].x,
                            items[i].areaCollision.y + ps[i].y,
                            items[i].areaCollision.width,
                            items[i].areaCollision.height);
                    if (rectItem.intersects(this.areaCollision)) {
                        if (items[i].collision) {
                            if (items[i].name.equals(Door.NAME)) {
                                if (!items[i].actived) {
                                    KeyItem ki = new KeyItem();
                                    if (this.bag.checkExists(ki)) {
                                        items[i].collision = false;
                                        items[i].update();
                                        this.bag.remove(ki, 1);
                                        this.gp.ui.addMessae("You used one key to open the door");
                                    } else {
                                        switch (this.direct) {
                                            case Direction.UP:
                                                this.worldY += speed;
                                                break;
                                            case Direction.DOWN:
                                                this.worldY -= speed;
                                                break;
                                            case Direction.LEFT:
                                                this.worldX += speed;
                                                break;
                                            case Direction.RIGHT:
                                                this.worldX -= speed;
                                                break;
                                        }
                                        this.gp.ui.addMessae("You need one key to open then door");
                                    }
                                    this.gp.itemSetup.items[i].actived = true;
                                } else {
                                    switch (this.direct) {
                                        case Direction.UP:
                                            this.worldY += speed;
                                            break;
                                        case Direction.DOWN:
                                            this.worldY -= speed;
                                            break;
                                        case Direction.LEFT:
                                            this.worldX += speed;
                                            break;
                                        case Direction.RIGHT:
                                            this.worldX -= speed;
                                            break;
                                    }
                                }
                            } else if (items[i].name.equals(Chest.NAME)) {
                                if (!items[i].actived) {
                                    KeyChestItem kci = new KeyChestItem();
                                    if (this.bag.checkExists(kci)) {
                                        items[i].collision = false;
                                        items[i].update();
                                        this.bag.remove(kci, 1);
                                        this.gp.ui.addMessae("You used one key to open the chest");
                                        // get and remove
                                        Chest c = (Chest) items[i];
                                        for (int j = 0; j < c.getItems().size(); j++) {
                                            this.bag.addItem(c.getItems().get(j), c.getQuantites().get(j));
                                            this.gp.ui.addMessae("You get from chest " + c.getQuantites().get(j)
                                                    + " " + c.getItems().get(j).name);
                                        }
                                        ((Chest) items[i]).clear();
                                    } else {
                                        switch (this.direct) {
                                            case Direction.UP:
                                                this.worldY += speed;
                                                break;
                                            case Direction.DOWN:
                                                this.worldY -= speed;
                                                break;
                                            case Direction.LEFT:
                                                this.worldX += speed;
                                                break;
                                            case Direction.RIGHT:
                                                this.worldX -= speed;
                                                break;
                                        }
                                        this.gp.ui.addMessae("You need one key to open then chest");
                                    }

                                    this.gp.itemSetup.items[i].actived = true;
                                } else {
                                    switch (this.direct) {
                                        case Direction.UP:
                                            this.worldY += speed;
                                            break;
                                        case Direction.DOWN:
                                            this.worldY -= speed;
                                            break;
                                        case Direction.LEFT:
                                            this.worldX += speed;
                                            break;
                                        case Direction.RIGHT:
                                            this.worldX -= speed;
                                            break;
                                    }
                                }
                            }
                        } else {
                            if (items[i].pick) {
                                // show mess
                                this.gp.ui.addMessae("You picked " + this.gp.itemSetup.items[i].name);
                                // Pick
                                this.bag.addItem(items[i], 1);
                                this.gp.itemSetup.items[i] = null;
                            } else {
                                if (!items[i].actived) {
                                    if (items[i].name.equals(Chest.NAME)) { // CHEST
                                        this.gp.ui.addMessae(items[i].description);
                                    } else if (items[i].name.equals(Teleport.NAME)) { // CHEST
                                        this.gp.ui.addMessae(items[i].description);
                                        this.worldX = ((Teleport) items[i]).destinPosition.x;
                                        this.worldY = ((Teleport) items[i]).destinPosition.y;
                                    }
                                    this.gp.itemSetup.items[i].actived = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // update item using
    int counterSprite = 0;

    public void updateTimeUsing() {
        if (++counterSprite >= Setting.FPS) {
            counterSprite = 0;
            for (int i = 0; i < this.timeUsing.size(); i++) {
                this.timeUsing.set(i, this.timeUsing.get(i) - 1);
            }
        }
    }

    public void updateItemUsing() {
        for (int i = 0; i < timeUsing.size(); i++) {
            if (timeUsing.get(i) <= 0) {
                if (itemUsing.get(i).name.equals(BlueShoes.NAME)) {
                    this.speed -= ((BlueShoes) itemUsing.get(i)).getSpeedAdd();
                } else if (itemUsing.get(i).name.equals(Health.NAME)) {
                    setResHealth(this.resHealth
                            - ((Health) itemUsing.get(i)).restoresHealth);
                } else if (itemUsing.get(i).name.equals(Mana.NAME)) {
                    setResMana(this.resMana
                            - ((Mana) itemUsing.get(i)).restoresMana);
                } else if (itemUsing.get(i).name.equals(Sword01.NAME)) {
                    setDamage(this.damageNormal
                            - ((Sword01) itemUsing.get(i)).damage);
                }
                this.gp.ui.addMessae("The effective time of " + itemUsing.get(i).name + " has expired");
                itemUsing.remove(i);
                timeUsing.remove(i);
            }
        }
    }

    public void updateUsing() {
        updateItemUsing();
        updateTimeUsing();
    }

    // update can interact
    void updateCanInteract(GamePanel gp) {
        boolean flag = false;
        for (int i = 0; i < gp.npcSetup.npcs.size(); i++) {
            if (gp.npcSetup.npcs.get(i) != null && gp.npcSetup.npcs.get(i).canInteract) {
                this.canInteract = true;
                flag = true;
                break;
            }
        }
        if (!flag) {
            this.canInteract = false;
        }

    }

    // get index list npcs can interactive
    public ArrayList<Integer> getIndexNpcCanInteract() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < this.gp.npcSetup.npcs.size(); i++) {
            if (this.gp.npcSetup.npcs.get(i).canInteract) {
                result.add(i);
            }
        }
        return result;
    }

    public void update(GamePanel gp) {
        this.gp = gp;
        if (onBag) {
            this.state = CharacterState.CHARACTER_OPEN_BAG;
        } else {
            this.state = CharacterState.CHARACTER_NORMAL;
        }

        if (this.gp.gameState == GameState.PLAY_STATE && this.state == CharacterState.CHARACTER_NORMAL) {
            update();
            pickupItem();
            //update using
            updateUsing();
            // update position when size screen change
            this.screenX = gp.getWidth() / 2;
            this.screenY = gp.getHeight() / 2;

            // update can interactive
            updateCanInteract(gp);
        } else if (this.gp.gameState == GameState.PLAY_STATE
                && this.state == CharacterState.CHARACTER_OPEN_BAG) {

        } else if (this.gp.gameState == GameState.PLAY_STATE
                && this.state == CharacterState.CHARACTER_INTERACTING) {

        }

        // RECOVERY
        recovery();

        // AREA ATTACK
        if (this.gp.ch.attackPress) {
            updateImageAttack();
            updateAreaDamageAttack();
            attack();
        } else {
            updateAreaDamage();
            counterPriteAttack = 0;
        }
    }

    // SET SPEED
    public void setSpeedDF() {
        this.speed = this.speedDF;
    }

    public boolean checkUsedItem(Item item) {
        for (Item i : itemUsing) {
            if (i.name.equals(item.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * * RECOVERY **
     */
    public void recoveryOnSecond() {
        // HEALTH
        if (this.currentHealth + this.resHealth >= this.maxHealth) {
            this.currentHealth = maxHealth;
        } else {
            this.currentHealth += resHealth;
        }

        // MANA
        if (this.currentMana + this.resMana >= this.maxMana) {
            this.currentMana = maxMana;
        } else {
            this.currentMana += resMana;
        }
    }

    int counterSpriteRes = 0;

    public void recovery() {
        if (++counterSpriteRes >= Setting.FPS) {
            counterSpriteRes = 0;
            recoveryOnSecond();
        }
    }

    // => being attacked
    public void beginAttacked(int damage, String name) {
        this.currentHealth -= damage;
        if (isDie()) {
            this.gp.ui.causeDie = name.toUpperCase();
            this.gp.gameState = GameState.DIE_STATE;
        }
    }

    public boolean isDie() {
        if (currentHealth <= 0) {
            return true;
        }
        return false;
    }

    // ATTACK
    public void updateAreaDamage() {
        this.areaDamage.x = 0;
        this.areaDamage.y = 0;
        this.areaDamage.width = 0;
        this.areaDamage.height = 0;
    }

    public void updateAreaDamageAttack() {
        switch (this.oldDirect) {
            case Direction.UP:
                this.areaDamage.x = this.worldX;
                this.areaDamage.y = this.worldY - width;

                this.areaDamage.width = width;
                this.areaDamage.height = height * 2;
                break;
            case Direction.DOWN:
                this.areaDamage.x = this.worldX;
                this.areaDamage.y = this.worldY;

                this.areaDamage.width = width;
                this.areaDamage.height = height * 2;
                break;
            case Direction.LEFT:
                this.areaDamage.x = this.worldX - width;
                this.areaDamage.y = this.worldY;

                this.areaDamage.width = width * 2;
                this.areaDamage.height = height;
                break;
            case Direction.RIGHT:
                this.areaDamage.x = this.worldX;
                this.areaDamage.y = this.worldY;

                this.areaDamage.width = width * 2;
                this.areaDamage.height = height;
                break;
        }
    }
    
    void updateImageAttack(){
        switch (this.oldDirect) {
            case Direction.UP:
                break;
            case Direction.DOWN:
                break;
            case Direction.LEFT:
                break;
            case Direction.RIGHT:
                break;
        }
    }

    int counterPriteAttack = 0;

    public void attack() {
        if (counterPriteAttack == 0) {
            for (int i = 0; i < this.gp.monsterSetup.monsters.size(); i++) {
                Monster monster = this.gp.monsterSetup.monsters.get(i);
                Rectangle rect1 = new Rectangle(monster.worldX, monster.worldY, monster.width, monster.height);
                if (rect1.intersects(this.areaDamage)) {
                    monster.beginAttacked(this.damageNormal);
                    if (monster.isDie()) {
                        this.gp.monsterSetup.monsters.remove(i);
                    } else {
                        this.gp.monsterSetup.monsters.set(i, monster);
                    }
                }
            }
        }

        if (++counterPriteAttack >= this.attackSpeedS * Setting.FPS) {
            counterPriteAttack = 0;
        }
    }

}
