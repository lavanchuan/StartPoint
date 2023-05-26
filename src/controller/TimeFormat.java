package controller;

public class TimeFormat {
    
    public static String getDateTime(int timeSeconds){
        int day, hour, minute, second;
        day = timeSeconds / (60 * 60 * 24);
        hour = timeSeconds % (60 * 60 * 24) / (60 * 60);
        minute = timeSeconds % (60 * 60 * 24) % (60 * 60) / 60;
        second = timeSeconds % (60 * 60 * 24) % (60 * 60) % 60;
        if(day > 0){
            return numberFormat(day) + "-" +
                    numberFormat(hour) + ":" +
                    numberFormat(minute) + ":" +
                    numberFormat(second);
        }
        return getHourTime(timeSeconds);
    }
    
    public static String getHourTime(int timeSeconds){
        int hour, minute, second;
        hour = timeSeconds / (60 * 60);
        minute = timeSeconds % (60 * 60) / 60;
        second = timeSeconds % (60 * 60) % 60;
        if(hour > 0){
            return numberFormat(hour) + ":" + 
                    numberFormat(minute) + ":" +
                    numberFormat(second);
        }
        return getMinuteTime(timeSeconds);
    }
    
    public static String getMinuteTime(int timeSeconds){
        int minute, second;
        minute = timeSeconds / 60;
        second = timeSeconds % 60; 
        if(minute > 0){
            return numberFormat(minute) + ":" + numberFormat(second);
        }
        return timeSeconds+"";
    }
    
    public static String numberFormat(int num){
        if(num < 10){
            return "0" + num;
        }
        return num + "";
    }
 
}
