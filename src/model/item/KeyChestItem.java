package model.item;

public class KeyChestItem extends Item{
    
    public static final String NAME = "KEY CHEST";
    
    public KeyChestItem(){
        super(NAME);
        this.image = getImage("keyChest");
        this.description = "Use to open locked chests";
    }
}
