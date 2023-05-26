package model.entity.monster;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import model.define.Direction;
import model.entity.Entity;

import setting.Setting;

import view.GamePanel;

public class Monster extends Entity {

    // define
    public static final int PATROL_STATE = 1;
    public static final int CHASE_STATE = 2;
    public static final int ATTACK_STATE = 3;

    public int state = PATROL_STATE;

    // info
    public int damageNormal = 20;
    public int damageSkill = 40;
    public int attackSpeedS = 1;
    public int maxHealth = 200;
    public int maxMana = 100;
    public int currentHealth = maxHealth;
    public int currentMana = 50;
    public int percentHealthRecovery = 0;
    public int percentManaRecovery = 0;

    // speed chase
    public int chaseSpeed = 1;

    // image
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // move
    protected BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,
            attackLeft1, attackLeft2, attackRight1, attackRight2; // attack

    // area detect
    protected int sizeDetected = 5;

    // attack
    public Rectangle areaDamage;

    public Monster(GamePanel gp) {
        super(gp);

        areaDamage = new Rectangle(0, 0, this.width, this.height);
    }

    public boolean checkDetectedCharacter() {
//        System.err.printf("character: (%d, %d)\n", this.gp.character.worldX, this.gp.character.worldY);
//        System.out.println(this.gp.character.areaCollision);
        Rectangle rect = this.areaCollision;
        rect.x = this.worldX - this.areaCollision.width / 2 + this.width / 2;
        rect.y = this.worldY - this.areaCollision.height / 2 + this.height / 2;
        if (rect.intersects(this.gp.character.areaCollision)) {
            return true;
        }
        return false;
    }

    public boolean checkAttack() {
        Rectangle rect = new Rectangle(this.worldX, this.worldY, this.width, this.height);
        if (rect.intersects(this.gp.character.areaCollision)) {
            return true;
        }
        return false;
    }

    public void updateAreaDamage() {
        switch (this.direct) {
            case Direction.UP:
            case Direction.DOWN:
                if (this.state == ATTACK_STATE) {
                    this.areaDamage.height = this.height * 2;
                } else {
                    this.areaDamage.height = this.height;
                    this.areaDamage.width = this.width;
                }
                break;
            case Direction.LEFT:
            case Direction.RIGHT:
                if (this.state == ATTACK_STATE) {
                    this.areaDamage.width = this.width * 2;
                } else {
                    this.areaDamage.height = this.height;
                    this.areaDamage.width = this.width;
                }
                break;
        }
    }

    // attacked
    int counterPriteAttack = 0;
    public void attacked() {
        if(counterPriteAttack == 0){
            this.gp.character.beginAttacked(this.damageNormal, this.name);
            this.gp.ui.addMessae("Health palyer :\t-" + this.damageNormal);
//            System.err.println("Health palyer :\t-" + this.damageNormal);
        }
        if(++counterPriteAttack >= this.attackSpeedS * Setting.FPS){
            counterPriteAttack = 0;
        }
    }
    
    // => being attacked
    public void beginAttacked(int damage){
        this.currentHealth -= damage;
        System.out.println("-" + damage);
//        if(isDie()){
//            this.gp.ui.causeDie = name.toUpperCase();
//        }
    }
    
    public boolean isDie(){
        if(currentHealth <= 0){
            return true;
        }
        return false;
    }

}
