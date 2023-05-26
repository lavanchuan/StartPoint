package controller.render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import view.GamePanel;

import model.entity.npc.NPC;

public class NPCRender {

    // references
    GamePanel gp;

    public NPCRender(GamePanel gp) {
        this.gp = gp;
    }

    // update npcs
    public void update(GamePanel gp) {
        for (NPC npc : gp.npcSetup.npcs) {
            if (npc != null) {
                npc.update();
            }
        }
    }
    
    // area
    public boolean onArea = false;

    public void render(Graphics g, GamePanel gp) {
        int x, y;
        for (NPC npc : gp.npcSetup.npcs) {
            if (npc != null && checkRenderNPC(npc, gp)) {
                x = npc.worldX - (gp.character.worldX - gp.getWidth() / 2) - gp.character.width / 2;
                y = npc.worldY - (gp.character.worldY - gp.getHeight() / 2) - gp.character.height / 2;
//                System.err.printf("[%s]: (%d, %d)\n", npc.name, npc.worldX, npc.worldY);
//                System.out.printf("[Player]: (%d, %d)\n", gp.character.worldX, gp.character.worldY);
                g.drawImage(npc.image,
                        x,
                        y,
                        npc.width,
                        npc.height,
                        null);

                // draw area interac
                if (onArea) {
                    g.setColor(new Color(0, 255, 255));
                    g.drawRect(x + npc.width / 2 - npc.areaCollision.width / 2,
                            y + npc.height / 2 - npc.areaCollision.height / 2,
                            npc.areaCollision.width,
                            npc.areaCollision.height);
                }

                // render interactive
                renderInteractive(g, npc, gp);
            }
        }
    }

    public boolean checkRenderNPC(NPC npc, GamePanel gp) {
        int x, y;
        x = npc.worldX - (gp.character.worldX - gp.getWidth() / 2);
        y = npc.worldY - (gp.character.worldY - gp.getHeight() / 2);
        if (x + npc.width > 0
                && x - npc.width < gp.getWidth()
                && y + npc.height > 0
                && y - npc.height < gp.getHeight()) {
            return true;
        }
        return false;
    }

    // interactive render
    String msg = "";

    void renderInteractive(Graphics g, NPC npc, GamePanel gp) {
        if (npc.canInteract) {
            int x;
            int y;
            if (npc.onInterac) {
                int width = (int) (gp.getWidth() * 0.8);
                int height = (int) (gp.getHeight() * 0.4);
                x = (int) (gp.getWidth() * 0.1);
                y = (int) (gp.getHeight() * 0.02);
                renderDialog(g, x, y, width, height);
                // msg
                int margin = (int) (width * 0.1);
                int widthMsg = width - 2 * margin;
                int size = 40;
                g.setFont(new Font("Times New Roman", Font.PLAIN, size));
                g.setColor(new Color(255, 255, 255));
                msg = npc.comm.getMsg();
                RenderFormat.render(g, msg, widthMsg, x + margin, y + margin);
//                g.drawString(msg, x + margin, y + margin);
            } else {
                int size = 30;
                String text = "!!!";
                g.setColor(Color.WHITE);
                g.setFont(new Font("Times New Roman", Font.BOLD, size));
                x = npc.worldX - gp.character.worldX + gp.getWidth() / 2;
                y = npc.worldY - gp.character.worldY + gp.getHeight() / 2;
                RenderFormat.renderCenter(text, x, y - size, g);
            }
        }

    }

    void renderDialog(Graphics g, int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g.setColor(c);
        g.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(225, 225, 225);
        g.setColor(c);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

}
