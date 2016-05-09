package com.seesaw.qeditor.data;

/** Скрипт помещается в список Ответа или Таймера.
 *  Скрипт выполняется только если его условие будет выполнено,
 *  и не выполнится ни один из более приоритетных скриптов в списке. */
public class Script {

    String  id;
    String  condition;
    String  algorithm;

    /** Конструктор без аргументов требуется для десериализации. */
    public Script(){}

    public Script(String id) {
        this.id = id;
    }
}
