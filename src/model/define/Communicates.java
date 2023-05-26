package model.define;

import java.util.ArrayList;

public class Communicates {
    
    private ArrayList<String> msgList;
    private int currentMsg = 0;
    
    public Communicates(ArrayList<String> msgList){
        this.msgList = msgList;
    }
    
    public String getMsg(){
        return this.msgList.get(currentMsg);
    }
    
    public void nextIndexMsg(){
        if(++this.currentMsg >= this.msgList.size()){
            this.currentMsg = 0;
        }
    }
}
