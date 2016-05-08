package com.seesaw.qeditor.data;

import java.util.HashMap;

/** Хранилище данных, поля обычно заполняются из json файла,
 *  вся работа с классом и его полями происходит в DataModule. */
public class Storage {

    HashMap<String, Step> steps;
    HashMap<String, Answer> answers;
    HashMap<String, Timer> timers;
    HashMap<String, Script> scripts;

    public Storage(){
        steps = new HashMap<String, Step>();
        answers = new HashMap<String, Answer>();
        timers = new HashMap<String, Timer>();
        scripts = new HashMap<String, Script>();
    }
}
