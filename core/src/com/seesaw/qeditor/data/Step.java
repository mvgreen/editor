package com.seesaw.qeditor.data;

import java.util.ArrayList;

/** Шаг - Одна "реплика", после нее могут отобразиться варианты дальнейших действий.
 *  При переходе от одного узла к другому отображается текст (если он есть),
 *  останавливаются Таймеры старого Шага и запускаются Таймеры нового, сменяются Ответы.*/
public class Step {

    String              id;
    String              text;
    ArrayList<String>   answers;
    ArrayList<String>   timers;

    public Step(String id) {
        this.id = id;
        answers = new ArrayList<String>();
        timers = new ArrayList<String>();
    }
}
