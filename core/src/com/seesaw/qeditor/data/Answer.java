package com.seesaw.qeditor.data;

import java.util.ArrayList;

/** Ответ - способ воздействия игрока на игровую ситуацию.
 *  В игре представляется в виде кнопки с текстом на ней.
 *  При выборе ответа перебирается каждый из списков скриптов,
 *  в каждом списке выполняется первый скрипт, чье условие выполнится.
 *  Нам требуется лишь два списка. */
public class Answer extends Field{

    String                          text;
    String                          step;
    ArrayList<String>               scripts;

    /** Конструктор без аргументов требуется для десериализации. */
    public Answer(){}

    public Answer(String id) {
        this.id = id;
        step = "";
        scripts = new ArrayList<String>();
    }

    public boolean hasStep(){
        return step != null;
    }

    public String getStepId(){
        return step;
    }

    public boolean hasScripts() {
        return !scripts.isEmpty();
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}
