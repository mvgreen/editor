package com.seesaw.qeditor.data;

public class Clock {
    int hours;
    int minutes;
    int seconds;

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes){
        this.minutes = minutes%60;
        hours += minutes/60;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds%60;
        setMinutes(seconds/60);
    }

    public int getHours(boolean simpleClock){
        return simpleClock ? hours%24 : hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }
}
