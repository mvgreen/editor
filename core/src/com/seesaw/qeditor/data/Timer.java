package com.seesaw.qeditor.data;

import java.util.ArrayList;
import java.util.Date;

/** Таймер - запланированное во времени действие.
 *  Таймер может сработать или в конкретное время, или через промежуток времени.
 *  При срабатывании перебирается каждый из списков скриптов,
 *  в каждом списке выполняется первый скрипт, чье условие выполнится.
 *  Нам требуется лишь два списка.*/
public class Timer {

    static final int PLUS           = 1;
    static final int MINUS          = -1;
    static final int ALARM_CLOCK    = 0;

    String                          id;
    Clock                           time;
    int                             type;
    boolean                         autoCancel;
    String                          step;
    ArrayList<String>               scripts;

    /** Конструктор без аргументов требуется для десериализации. */
    public Timer(){}

    public Timer(String id) {
        this.id = id;
        scripts = new ArrayList<String>();
        time = new Clock();
    }
}
