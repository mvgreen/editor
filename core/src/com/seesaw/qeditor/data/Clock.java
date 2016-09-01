package com.seesaw.qeditor.data;

import java.util.Calendar;
import java.util.TimeZone;

public class Clock {
    int hours;
    int minutes;
    float seconds;
    boolean timeIsOver;
    boolean reversed;

    public Clock(Timer timer) {
        hours = 0;
        minutes = 0;
        seconds = 0;
        timeIsOver = false;
        reversed = false;

        int cHour = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.HOUR_OF_DAY);
        int cMinute = Calendar.getInstance().get(Calendar.MINUTE);
        float cSecond = Calendar.getInstance().get(Calendar.SECOND);

        int tHour = timer.time.hours;
        int tMinute = timer.time.minutes;
        float tSecond = timer.time.seconds;

        switch (timer.type){
            default:
            case Timer.PLUS:
                hours = tHour;
                minutes = tMinute;
                seconds = tSecond;
                break;
            case Timer.ALARM_CLOCK:
                if (tSecond >= cSecond)
                    seconds += tSecond - cSecond;
                else {
                    seconds += 60 + tSecond - cSecond;
                    minutes--;
                }

                if (tMinute >= cMinute)
                    minutes += tMinute - cMinute;
                else {
                    minutes += 60 + tMinute - cMinute;
                    hours--;
                }

                if (tHour >= cHour)
                    hours += tHour - cHour;
                else
                    hours += 24 + tHour - cHour;
                break;
            case Timer.MINUS:
                hours = -tHour;
                minutes = -tMinute;
                seconds = -tSecond;
                reversed = true;
                break;
        }
    }

    public Clock(){
    }

    public int getHours(boolean simpleClock){
        return simpleClock ? hours%24 : hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public float getSeconds(){
        return seconds;
    }

    public void setTime(int hours, int minutes, int seconds) {
        seconds = seconds + minutes * 60 + hours * 60 * 60;

        this.seconds = seconds % 60;
        seconds /= 60;

        this.minutes = seconds % 60;
        seconds /= 60;

        this.hours = seconds;

        timeIsOver = false;
    }

    public void add(float deltaTime){
        if (deltaTime < 0){
            sub(-deltaTime);
            return;
        }
        seconds += deltaTime;

        if (!reversed) {
            if (seconds >= 60f) {
                seconds -= 60f;
                minutes++;
            }
            if (minutes >= 60) {
                minutes -= 60;
                hours++;
            }
            if (seconds > 0 || minutes > 0 || hours > 0)
                timeIsOver = false;
        }

        else {
            if ((seconds > 0f) && (minutes < 0 || hours < 0)){
                seconds -= 60f;
                minutes++;
                if (minutes > 0){
                    minutes -= 60;
                    hours++;
                }
            }
            else
                timeIsOver = true;
        }
    }

    public void sub(float deltaTime){
        if (deltaTime < 0){
            add(deltaTime);
            return;
        }
        seconds -= deltaTime;

        if (!reversed){
            if ((seconds < 0f) && (minutes > 0 || hours > 0)){
                seconds += 60f;
                minutes--;
                if (minutes < 0){
                    minutes += 60;
                    hours--;
                }
            }
            else if (seconds <= 0f && minutes <= 0 && hours <= 0)
                timeIsOver = true;
        }

        else{
            if (seconds <= 60f){
                seconds += 60f;
                minutes--;
            }
            if (minutes <= 60){
                minutes += 60;
                hours--;
            }
            if (seconds < 0 || minutes < 0 || hours < 0)
                timeIsOver = false;
        }
    }

    public boolean isOver(){
        return timeIsOver;
    }
}
