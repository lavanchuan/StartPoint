package model.item;

public class Health extends Item{
    public static final String NAME = "HEALTH";
    public double restoresHealth = 5;
    public int timeM = 2;
    public Health(){
        super(NAME);
        this.pick = true;
        this.use = true;
        this.image = getImage("health");
        this.description = "Use to restores " + restoresHealth + " every second, in "
                + timeM + " minute";
    }
}
