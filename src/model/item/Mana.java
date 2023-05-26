package model.item;

public class Mana extends Item{
    public static final String NAME = "MANA";
    public int restoresMana = 5;
    public int timeM = 2;
    public Mana(){
        super(NAME);
        this.pick = true;
        this.use = true;
        this.image = getImage("mana");
        this.description = "Use to restores " + restoresMana + " every second, in "
                + timeM + " minute";
    }
}
