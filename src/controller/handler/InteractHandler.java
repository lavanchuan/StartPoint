package controller.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.define.GameScreen;
import model.entity.npc.NPC;
import view.GamePanel;

public class InteractHandler implements KeyListener {

    GamePanel gp;

    public InteractHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gp.gameScreen == GameScreen.PLAY_SCREEN && this.gp.character.canInteract) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (this.gp.character.getIndexNpcCanInteract().size() == 1) {
                    int index = this.gp.character.getIndexNpcCanInteract().get(0);
                    NPC npc = this.gp.npcSetup.npcs.get(index);
                    if (!npc.onInterac) {
                        npc.onInterac = true;
                        npc.interacting = true;
                    } else {
                        npc.comm.nextIndexMsg();
                    }
                    this.gp.npcSetup.npcs.set(index, npc);
                }
            }
        }
    }
}
