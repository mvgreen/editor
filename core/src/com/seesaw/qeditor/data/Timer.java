package com.seesaw.qeditor.data;

import java.util.ArrayList;
import java.util.Date;

/** Таймер - запланированное во времени действие.
 *  Таймер может сработать или в конкретное время, или через промежуток времени.
 *  При срабатывании перебирается каждый из списков скриптов,
 *  в каждом списке выполняется первый скрипт, чье условие выполнится.*/
public class Timer {

    static final int PLUS           = 1;
    static final int MINUS          = -1;
    static final int ALARM_CLOCK    = 0;

    String                          id;
    Date                            time;
    int                             type;
    boolean                         autoCancel;
    ArrayList<ArrayList<String >>   scripts;

    public Timer(String id) {
        this.id = id;
        scripts = new ArrayList<ArrayList<String>>();
        time = new Date();
    }
}
