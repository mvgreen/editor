package com.seesaw.qeditor.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/** Класс сождержит данные о пользователе: его идентификатор (метку) и индексы.
 *  Индексы нужны для быстрой генерации нового уникального id для Шага, Ответа, Таймера или Скрипта.
 *  Метка автора используется во избежание конфликта имен,
 *  если над сюжетом работает несколько авторов с разных устройств. Она добавляется в начале id. */
public class Author {

    final static int STEP    = 0;
    final static int ANSWER  = 1;
    final static int TIMER   = 2;
    final static int SCRIPT  = 3;

    private String ID;
    boolean defined;
    int[] lastIndexes;

    public Author(){
        lastIndexes = new int[4];
        loadPrefs();
    }

    public void setID(String newID){
        defined = (newID != null) && !newID.equals("");
        ID = defined ? newID : "";
    }

    public String getID(){
        return ID;
    }

    public void setLastIndex(int type, int newIndex){
        if (type < 0 || type > 3)
            return;
        lastIndexes[type] = newIndex;
    }

    public String getNewIndex(int type){
        if (type > 3 || type < 0)
            return "";
        lastIndexes[type]++;
        savePrefs();
        return ID + lastIndexes[type];
    }

    public void savePrefs() {
        Preferences preferences = Gdx.app.getPreferences("author");
        preferences.putString("ID", ID);
        preferences.putInteger("Steps", lastIndexes[Author.STEP]);
        preferences.putInteger("Answers", lastIndexes[Author.ANSWER]);
        preferences.putInteger("Timers", lastIndexes[Author.TIMER]);
        preferences.putInteger("Scripts", lastIndexes[Author.SCRIPT]);
        preferences.flush();
    }

    public void loadPrefs(){
        Preferences preferences = Gdx.app.getPreferences("author");
        defined = !(ID = preferences.getString("ID", "")).equals("");
        lastIndexes[Author.STEP] = preferences.getInteger("Steps", 0);
        lastIndexes[Author.ANSWER] = preferences.getInteger("Answers", 0);
        lastIndexes[Author.TIMER] = preferences.getInteger("Timers", 0);
        lastIndexes[Author.SCRIPT] = preferences.getInteger("Scripts", 0);
    }

}
