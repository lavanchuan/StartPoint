package model.bag;

import java.util.ArrayList;
import model.item.Item;
import model.item.KeyItem;

public class Bag {
    public static final int MAX_COL = 10;
    public static final int MAX_ROW = 10;
    
    public ArrayList<Item> items;
    public ArrayList<Integer> quantites;
    
    public Bag(){
        int max_length = MAX_COL * MAX_ROW;
        items = new ArrayList<Item>();
        quantites = new ArrayList<Integer>();
    }
    
    public void addItem(Item item, int quantity){
        boolean exists = false;
        int i;
        for(i = 0; i < items.size(); i++){
            if(item.name.equals(items.get(i).name)){
                exists = true;
                break;
            }
        }
        if(exists){
            quantites.set(i, quantites.get(i) + quantity);
        } else {
            items.add(item);
            quantites.add(quantity);
        }
    }
    
    public void remove(Item item){
        boolean exists = false;
        int i;
        for(i = 0; i < items.size(); i++){
            if(item.name.equals(items.get(i).name)){
                exists = true;
                break;
            }
        }
        if(exists){
            items.remove(i);
            quantites.remove(i);
        }
    }
    
    public void remove(Item item, int quantity){
        boolean exists = false;
        int i;
        for(i = 0; i < items.size(); i++){
            if(item.name.equals(items.get(i).name)){
                exists = true;
                break;
            }
        }
        if(exists){
            if(quantity == quantites.get(i)){
                items.remove(i);
                quantites.remove(i);
            } else {
                quantites.set(i, quantites.get(i) - quantity);
            }
        }
    }
    
    public boolean checkExists(Item item){
        for(Item i : items){
            if(i.name.equals(item.name)){
                return true;
            }
        }
        return false;
    }
    
}
