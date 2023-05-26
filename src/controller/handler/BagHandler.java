package controller.handler;

import setting.Setting;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.bag.Bag;
import model.define.CharacterState;
import model.define.GameState;
import model.item.BlueShoes;
import model.item.Health;
import model.item.Mana;
import model.item.Sword01;
import view.GamePanel;

public class BagHandler implements KeyListener {

    GamePanel gp;

    public BagHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.gp.gameState == GameState.PLAY_STATE) {
            if (e.getKeyCode() == KeyEvent.VK_B) {
                this.gp.character.onBag = !this.gp.character.onBag;
            }

            if (this.gp.character.state == CharacterState.CHARACTER_OPEN_BAG) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (--this.gp.characterRender.rowSelect < 0) {
                        this.gp.characterRender.rowSelect = Bag.MAX_ROW - 1;
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (++this.gp.characterRender.rowSelect > Bag.MAX_ROW - 1) {
                        this.gp.characterRender.rowSelect = 0;
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (--this.gp.characterRender.colSelect < 0) {
                        this.gp.characterRender.colSelect = Bag.MAX_COL - 1;
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (++this.gp.characterRender.colSelect > Bag.MAX_COL - 1) {
                        this.gp.characterRender.colSelect = 0;
                    }
                }

                // select and active
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = this.gp.characterRender.rowSelect;
                    int col = this.gp.characterRender.colSelect;
                    int index = row * Bag.MAX_ROW + col;
                    if (this.gp.character.bag.items.size() > index) {
                        String nameItem = this.gp.character.bag.items.get(index).name;
                        if (this.gp.character.bag.items.get(index).use) {
                            if (nameItem.equals(BlueShoes.NAME)) {
                                if (!this.gp.character.checkUsedItem(
                                        this.gp.character.bag.items.get(index))) {
                                    this.gp.character.speed
                                            += ((BlueShoes) this.gp.character.bag.items.get(index)).getSpeedAdd();
                                    this.gp.character.itemUsing.add(this.gp.character.bag.items.get(index));
                                    this.gp.character.timeUsing.add(
                                            ((BlueShoes) this.gp.character.bag.items.get(index)).getTimeEffectM() * Setting.FPS);

                                    this.gp.ui.addMessae("you used one " + this.gp.character.bag.items.get(index).name);

                                    this.gp.character.bag.remove(this.gp.character.bag.items.get(index), 1);
                                } else {
                                    this.gp.ui.addMessae("Can not use item");
                                }
                            } else if (nameItem.equals(Health.NAME)) {
                                if (!this.gp.character.checkUsedItem(
                                        this.gp.character.bag.items.get(index))) {
                                    this.gp.character.setResHealth(
                                            this.gp.character.getResHealth()
                                            + ((Health) this.gp.character.bag.items.get(index)).restoresHealth
                                    );
                                    this.gp.character.itemUsing.add(this.gp.character.bag.items.get(index));
                                    this.gp.character.timeUsing.add(
                                            ((Health) this.gp.character.bag.items.get(index)).timeM * Setting.FPS);

                                    this.gp.ui.addMessae("you used one " + this.gp.character.bag.items.get(index).name);

                                    this.gp.character.bag.remove(this.gp.character.bag.items.get(index), 1);
                                } else {
                                    this.gp.ui.addMessae("Can not use item");
                                }
                            } else if (nameItem.equals(Mana.NAME)) {
                                if (!this.gp.character.checkUsedItem(
                                        this.gp.character.bag.items.get(index))) {
                                    this.gp.character.setResMana(
                                            this.gp.character.getResMana()
                                            + ((Mana) this.gp.character.bag.items.get(index)).restoresMana
                                    );
                                    this.gp.character.itemUsing.add(this.gp.character.bag.items.get(index));
                                    this.gp.character.timeUsing.add(
                                            ((Mana) this.gp.character.bag.items.get(index)).timeM * Setting.FPS);

                                    this.gp.ui.addMessae("you used one " + this.gp.character.bag.items.get(index).name);

                                    this.gp.character.bag.remove(this.gp.character.bag.items.get(index), 1);
                                } else {
                                    this.gp.ui.addMessae("Can not use item");
                                }
                            } else if (nameItem.equals(Sword01.NAME)) {
                                if (!this.gp.character.checkUsedItem(
                                        this.gp.character.bag.items.get(index))) {
                                    this.gp.character.setDamage(
                                            this.gp.character.getDamage()
                                            + ((Sword01) this.gp.character.bag.items.get(index)).damage
                                    );
                                    this.gp.character.itemUsing.add(this.gp.character.bag.items.get(index));
                                    this.gp.character.timeUsing.add(
                                            ((Sword01) this.gp.character.bag.items.get(index)).timeM * Setting.FPS);

                                    this.gp.ui.addMessae("you used one " + this.gp.character.bag.items.get(index).name);

                                    this.gp.character.bag.remove(this.gp.character.bag.items.get(index), 1);
                                } else {
                                    this.gp.ui.addMessae("Can not use item");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
