package com.seesaw.qeditor.data;

/** Скрипт помещается в список Ответа или Таймера. */
public class Script {

    String          id;
    String          algorithm;

    /** Конструктор без аргументов требуется для десериализации. */
    public Script(){}

    public Script(String id) {
        this.id = id;
    }
}
