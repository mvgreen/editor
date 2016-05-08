package com.seesaw.qeditor.data;

import java.util.ArrayList;

/** Ответ - способ воздействия игрока на игровую ситуацию.
 *  В игре представляется в виде кнопки с текстом на ней.
 *  При выборе ответа перебирается каждый из списков скриптов,
 *  в каждом списке выполняется первый скрипт, чье условие выполнится.
 *  Нам требуется лишь два списка. */
public class Answer {

    String                          id;
    String                          text;
    ArrayList<ArrayList<String>>    scripts;

    public Answer(String id) {
        this.id = id;
        scripts = new ArrayList<ArrayList<String>>();
        scripts.add(new ArrayList<String>());
        scripts.add(new ArrayList<String>());
    }
}
