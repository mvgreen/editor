package com.seesaw.qeditor.data;

public class Clock {
    int hours;
    int minutes;
    int seconds;

    public int getHours(boolean simpleClock){
        return simpleClock ? hours%24 : hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }

    public void setTime(int hours, int minutes, int seconds) {
        seconds = seconds + minutes * 60 + hours * 60 * 60;

        this.seconds = seconds % 60;
        seconds /= 60;

        this.minutes = seconds % 60;
        seconds /= 60;

        this.hours = seconds;
    }
}
