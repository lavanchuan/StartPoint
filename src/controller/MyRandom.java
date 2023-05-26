package controller;

import java.util.Random;

public class MyRandom {
    
    public static int getInt(int min, int max){
        return (new Random()).nextInt(max - min + 1) + min;
    }
}
