package model.item;

public class BlueShoes extends Item{
    
    public static final String NAME = "BLUE SHOES";
    private int speedAdd = 2;
    private int timeEffectM = 2;
    
    public BlueShoes(){
        super(NAME);
        
        this.use = true;
        
        this.description = "Use to speed add " + speedAdd + " in "
                + timeEffectM + " minute, use only 1 when using 1 item";
        this.image = getImage("shoesBlue");
    }
    
    public int getSpeedAdd(){
        return this.speedAdd;
    }
    
    public int getTimeEffectM(){
        return this.timeEffectM;
    }
    
}
