package model.item;

import java.util.ArrayList;

public class Chest extends Item {

    public static final String NAME = "CHEST";

    private ArrayList<Item> items;
    private ArrayList<Integer> quantites;

    public Chest() {
        super(NAME);
        this.collision = true;
        this.pick = false;
        update();
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public ArrayList<Integer> getQuantites() {
        return this.quantites;
    }

    public void clear() {
        this.items = new ArrayList<Item>(0);
        this.quantites = new ArrayList<Integer>(0);
    }

    @Override
    public void update() {
        if (this.collision) {
            this.image = getImage("chest");
            this.description = "Chest, may conatain items";
            this.items = new ArrayList<Item>();
            this.quantites = new ArrayList<Integer>();
            items.add(new KeyItem());
            quantites.add(1);

//            items.add()
        } else {
            this.description = "Chest opended, no empty";
        }
    }
}
