package model.entity.monster;

import controller.MyRandom;

import setting.Setting;

import model.define.Direction;
import view.GamePanel;

public class ORC extends Monster {

    public static final String NAME = "ORC";
    public static final int CHASE_SPEED = 2;
    public static final int NORMAL_SPEED = 1;

    public ORC(GamePanel gp, int worldX, int worldY) {
        super(gp);

        this.worldX = worldX;
        this.worldY = worldY;

        this.name = NAME;

        setup();
        loadImage();
        this.image = down1;
    }

    void setup() {
        this.speedUnit = 1;
        this.speed = NORMAL_SPEED;
        this.chaseSpeed = CHASE_SPEED;
        this.damageNormal += 5;
        this.damageSkill += 10;

        this.areaCollision.width = sizeDetected * Setting.tileSize;
        this.areaCollision.height = sizeDetected * Setting.tileSize;
    }

    void loadImage() {
        // MOVE
        up1 = this.loadImage("monster/orc", "move", "up1");
        up2 = this.loadImage("monster/orc", "move", "up2");
        down1 = this.loadImage("monster/orc", "move", "down1");
        down2 = this.loadImage("monster/orc", "move", "down2");
        left1 = this.loadImage("monster/orc", "move", "left1");
        left2 = this.loadImage("monster/orc", "move", "left2");
        right1 = this.loadImage("monster/orc", "move", "right1");
        right2 = this.loadImage("monster/orc", "move", "right2");

        // ATTACK
        attackUp1 = this.loadImage("monster/orc", "attack", "up1");
        attackUp2 = this.loadImage("monster/orc", "attack", "up2");
        attackDown1 = this.loadImage("monster/orc", "attack", "down1");
        attackDown2 = this.loadImage("monster/orc", "attack", "down2");
        attackLeft1 = this.loadImage("monster/orc", "attack", "left1");
        attackLeft2 = this.loadImage("monster/orc", "attack", "left2");
        attackRight1 = this.loadImage("monster/orc", "attack", "right1");
        attackRight2 = this.loadImage("monster/orc", "attack", "right2");
    }

    int counterDraw = 0;

    public void changeDirect() {
        if (++counterDraw >= setting.Setting.FPS * 3) {
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
        if (++counterAnimation >= setting.Setting.FPS / 3) {
            counterAnimation = 0;
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

    // detected player
    void updateSpeed() {
        if (this.state == Monster.PATROL_STATE) {
            speed = NORMAL_SPEED;
        } else {
            speed = CHASE_SPEED;
        }
    }

    // chase player
    void updateDirectChase() {
        int x = this.gp.character.worldX;
        int y = this.gp.character.worldY;
        int w = this.gp.character.width;
        int h = this.gp.character.height;
        if (this.worldX + w/2 < x) {
            this.direct = Direction.RIGHT;
        } else if (this.worldX - w/2 > x) {
            this.direct = Direction.LEFT;
        } else if (this.worldY + h/2 < y) {
            this.direct = Direction.DOWN;
        } else if (this.worldY - h/2 > y) {
            this.direct = Direction.UP;
        }
        
    }

    // updateImageAttack
    int counterPriteAttack = 0;

    void updateImageAttack() {
        if (++counterPriteAttack >= setting.Setting.FPS * attackSpeedS / 2) {
            counterPriteAttack = 0;
            switch (this.direct) {
                case Direction.UP:
                    if (this.image == attackUp1) {
                        this.image = attackUp2;
                    } else {
                        this.image = attackUp1;
                    }
                    break;
                case Direction.DOWN:
                    if (this.image == attackDown1) {
                        this.image = attackDown2;
                    } else {
                        this.image = attackDown1;
                    }
                    break;
                case Direction.LEFT:
                    if (this.image == attackLeft1) {
                        this.image = attackLeft2;
                    } else {
                        this.image = attackLeft1;
                    }
                    break;
                case Direction.RIGHT:
                    if (this.image == attackRight1) {
                        this.image = attackRight2;
                    } else {
                        this.image = attackRight1;
                    }
                    break;
            }
        }
    }

    // update => (load gp)
    public void update(GamePanel gp) {
        this.gp = gp;
        switch (this.state) {
            case Monster.PATROL_STATE:
                updateDirect();
                updateImage();
                move();
                break;
            case Monster.CHASE_STATE:
                updateDirectChase();
                updateAreaDamage();
                updateImage();
                move();
                if(checkAttack()){
                    this.state = ATTACK_STATE;
                }
                break;
            case Monster.ATTACK_STATE:
                updateDirectChase();
                updateAreaDamage();
                updateImageAttack();
                attacked();
                if(!checkAttack()){
                    this.state = CHASE_STATE;
                    this.counterPriteAttack = 0;
                }
                break;
        }

        if (checkDetectedCharacter()) {
            if (this.state != ATTACK_STATE) {
                this.state = CHASE_STATE;
            } else {

            }
        } else {
            this.state = Monster.PATROL_STATE;
        }

        updateSpeed();
//        System.out.printf("Position: (%d, %d)\n", this.worldX, this.worldY);
    }

}
