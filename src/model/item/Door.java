package model.item;

public class Door extends Item{
    
    public static final String NAME = "Door";
    
    public Door(){
        super(NAME);
        this.collision = true;
        this.pick = false;
        update();
    }
    
    @Override
    public void update(){
        if(this.collision){
           this.image = getImage("door");
        } else {
            this.image = getImage("doorOpen");
        }
    }
}
