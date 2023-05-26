package model.item;

public class KeyItem extends Item{
    public static final String NAME = "KEY";
    public KeyItem(){
        super(NAME);
        this.description = "Use to open locked doors";
        this.image = this.getImage("key");
    }

}
