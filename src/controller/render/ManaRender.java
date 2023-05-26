package controller.render;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import model.entity.monster.Monster;
import view.GamePanel;

public class ManaRender {

    public void render(Graphics g, GamePanel gp) {
        int width = gp.character.width * 3;
        int height = (int) (gp.character.height * 0.1);
        int mana = (int) (((double) gp.character.currentMana / gp.character.maxMana) * width);

        int x = gp.character.screenX - width / 2;
        int y = gp.character.screenY - gp.character.width - height + 1;

        g.setColor(new Color(0, 100, 255));
        g.fillRect(x, y, mana, height);

        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y, width, height);

        // MONSETR
        ArrayList<Monster> monsters = gp.monsterSetup.monsters;
        for (Monster monster : monsters) {
            if ((new MonsterRender(gp).checkRenderMonster(monster))) {
                mana = (int) (((double) monster.currentMana / monster.maxMana) * width);

                x = monster.worldX - gp.character.worldX + gp.getWidth() / 2 - width / 2;
                y = monster.worldY - gp.character.worldY + gp.getHeight() / 2 - width/2 + 5 * height;

                g.setColor(new Color(0, 100, 255));
                g.fillRect(x, y, mana, height);

                g.setColor(new Color(0, 0, 0));
                g.drawRect(x, y, width, height);
            }
        }
    }
}
