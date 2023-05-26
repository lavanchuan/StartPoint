package controller.render;

import java.awt.Graphics;
import java.awt.Color;

import java.util.ArrayList;
import model.define.Direction;

import view.GamePanel;

import model.entity.monster.Monster;

public class MonsterRender {

    // reference
    GamePanel gp;

    public MonsterRender(GamePanel gp) {
        this.gp = gp;
    }

    boolean checkRenderMonster(Monster monster) {
        int x = monster.worldX + this.gp.getWidth() / 2 - this.gp.character.worldX;
        int y = monster.worldY + this.gp.getHeight() / 2 - this.gp.character.worldY;
        if (x + monster.width > 0
                && x - monster.width < this.gp.getWidth()
                && y + monster.height > 0
                && y - monster.height < this.gp.getHeight()) {
            return true;
        }
        return false;
    }

    // AREA
    public boolean onArea = false;

    public void render(Graphics g) {
        ArrayList<Monster> monsters = this.gp.monsterSetup.monsters;
        for (Monster monster : monsters) {
            if (checkRenderMonster(monster)) {
//                System.err.printf("Monster[%s] (%d, %d)\n", monster.name, monster.worldX, monster.worldY);
//                System.err.printf("PLAYER (%d, %d)\n", monster.worldX, monster.worldY);

                int x = monster.worldX - this.gp.character.worldX + this.gp.getWidth() / 2 - this.gp.character.width / 2;
                int y = monster.worldY - this.gp.character.worldY + this.gp.getHeight() / 2 - this.gp.character.height / 2;

                int w = monster.width;
                int h = monster.height;

                if (monster.state == Monster.ATTACK_STATE) {
                    switch (monster.direct) {
                        case Direction.DOWN:
                        case Direction.UP:
                            h *= 2;
                            break;
                        case Direction.LEFT:
                        case Direction.RIGHT:
                            w *= 2;
                            break;
                    }
                }

                g.drawImage(monster.image, x, y, w, h, null);

                // area ...
                if (onArea) {
                    // area detected
                    g.setColor(new Color(255, 0, 0));
                    g.drawRect(x - monster.areaCollision.width / 2 + monster.width / 2,
                            y - monster.areaCollision.height / 2 + monster.height / 2,
                            monster.areaCollision.width,
                            monster.areaCollision.height);

                    // area damage
                    g.setColor(new Color(255, 0, 0, 100));
                    g.fillRect(x, y, monster.areaDamage.width, monster.areaDamage.height);
                }
            }
        }
    }

    public void update(GamePanel gp) {
        this.gp = gp;
    }
}
