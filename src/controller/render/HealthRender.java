package controller.render;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import model.define.Direction;
import model.entity.monster.Monster;
import setting.Setting;

import view.GamePanel;

public class HealthRender {

    public void render(Graphics g, GamePanel gp) {
        int width = gp.character.width * 3;
        int height = (int) (gp.character.height * 0.4);
        int health = (int) (((double) gp.character.currentHealth / gp.character.maxHealth) * width);

        int x = gp.character.screenX - width / 2;
        int y = gp.character.screenY - gp.character.height - height;

        g.setColor(new Color(0, 255, 100));
        g.fillRect(x, y, health, height);

        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y, width, height);

        // MONSETR
        ArrayList<Monster> monsters = gp.monsterSetup.monsters;
        for (Monster monster : monsters) {
            if ((new MonsterRender(gp).checkRenderMonster(monster))) {
                health = (int) (((double) monster.currentHealth / monster.maxHealth) * width);

                x = monster.worldX - gp.character.worldX + gp.getWidth() / 2 - width / 2;
                y = monster.worldY - gp.character.worldY + gp.getHeight() / 2 - width / 2;

                g.setColor(new Color(255, 0, 100));
                g.fillRect(x, y, health, height);

                g.setColor(new Color(0, 0, 0));
                g.drawRect(x, y, width, height);
            }
        }
    }

}
