package model.item;

import model.define.ItemType;

public class Sword01 extends Item {

    public static final String NAME = "SWORD01";
    public int damage = 20;
    public int timeM = 1;

    public Sword01(){
        super(NAME);
        this.use = true;
        this.itemType = ItemType.WEAPON;
        this.description = "Use to melee attack, " + damage + " damage for every hit, effective in " +
                timeM + " minute";
        this.image = getImage("sword01");
    }
}
