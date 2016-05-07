package com.seesaw.qeditor.data;

import java.util.ArrayList;
import java.util.Date;

public interface Data {

    /** Заменяет метку автора. Метки ставятся в начале ID Шагов, Ответов, Таймеров и Скриптов.
     *  Метки существующих ID не будут заменяться. */
    public void setAuthorID(String ID);

    /** Получить метку автора. Описание см. выше. */
    public String getAuthorID();

    /** Задать индексы на случай сброса.
     *  type - Author.STEP / Author.ANSWER / Author.TIMER / Author.SCRIPT
     *  index - новое значение индекса. */
    public void setLastIndex(int type, int index);



    /** Загружает БД из файлов */
    public void loadDataBase();

    /** Сохраняет БД в файлы и данные автора в preferences */
    public void saveDataBase();



    /** Создает новый Шаг и возвращает его ID */
    public void createStep(String id);

    /** Удаляет Шаг с указанным ID */
    public void deleteStep(String id);

    /** Возвращает список ID всех Шагов */
    public String[] getSteps();


    /** Возвращает отображаемый текст Шага */
    public String getStepText(String id);

    /** Заменяет текст Шага на новый */
    public boolean setStepText(String id, String newText);


    /** Возвращает список связанных с Шагом Ответов */
    public String[] getStepAnswers(String id);

    /** Присоединяет к Шагу указанный Ответ */
    public boolean addStepAnswer(String stepID, String answerID);

    /** Отсоединяет Ответ от Шага, Ответ не удаляется из базы данных */
    public boolean removeStepAnswer(String stepID, String answerID);


    /** Возвращает список связанных с Шагом Таймеров */
    public String[] getStepTimers(String id);

    /** Присоединяет к Шагу указанный Таймер */
    public boolean addStepTimer(String stepID, String timerID);

    /** Отсоединяет Таймер от Шага, Таймер не удаляется из базы данных */
    public boolean removeStepTimer(String stepID, String timerID);



    /** Создает новый Ответ и возвращает его ID */
    public void createAnswer(String id);

    /** Удаляет Ответ с указанным ID */
    public void deleteAnswer(String id);

    /** Возвращает список ID всех Ответов */
    public String[] getAnswers();


    /** Возвращает отображаемый текст Ответа */
    public String getAnswerText(String id);

    /** Заменяет текст Шага на новый */
    public boolean setAnswerText(String id, String newText);


    /** Возвращает список связанных с Ответом Скриптов*/
    public ArrayList<ArrayList<String>> getAnswerScripts(String id);

    /** Присоединяет к Ответу указанный Скрипт */
    public boolean addAnswerScript(String answerID, String scriptID, int list);

    /** Отсоединяет Скрипт от Ответа, Скрипт не удаляется из базы данных */
    public boolean removeAnswerScript(String answerID, String scriptID, int list);



    /** Создает новый Таймер и возвращает его ID */
    public void createTimer(String id);

    /** Удаляет Таймер с указанным ID */
    public void deleteTimer(String id);

    /** Возвращает список ID всех Таймеров */
    public String[] getTimers();


    /** Возвращает время, установленное в Таймере, тип Таймера не учитывается */
    public Date getTimerTime(String id);

    /** Заменяет время в Таймере на новое, тип Таймера не учитывается */
    public boolean setTimerTime(String id, int hours, int minutes, int seconds);

    /** Аналогичный предыдущему */
    public boolean setTimerTime(String id, Date time);


    /** Возвращает тип Таймера, 1 - таймер прибавляется к текущему времени, -1 - отнимается, 0 - "будильник" */
    public int getTimerType(String id);

    /** Задает тип Таймера, значения аналогичны предыдущему */
    public boolean setTimerType(String id, int type);


    /** Если true, то Таймер отменяется при смене Шага */
    public boolean getTimerAutoCancel(String id);

    /** Аналогично предыдущему */
    public boolean setTimerAutoCancel(String id, boolean autoCancel);


    /** Возвращает список связанных с Таймером Скриптов*/
    public ArrayList<ArrayList<String>> getTimerScripts(String id);

    /** Присоединяет к Таймером указанный Скрипт */
    public boolean addTimerScript(String answerID, String scriptID, int list);

    /** Отсоединяет Скрипт от Таймера, Скрипт не удаляется из базы данных */
    public boolean removeTimerScript(String answerID, String scriptID, int list);



    /** Создает новый Скрипт и возвращает его ID */
    public void createScript(String id);

    /** Удаляет Скрипт с указанным ID */
    public void deleteScript(String id);

    /** Возвращает список ID всех Скриптов */
    public String[] getScripts();


    /** Возвращает условие выполнения Скрипта */
    public String getScriptCondition(String id);

    /** Заменяет условие выполнения Скрипта на новое */
    public boolean setScriptCondition(String id, String newCondition);


    /** Возвращает алгоритм Скрипта */
    public String getScriptAlgorithm(String id);

    /** Заменяет алгоритм Скрипта на новый */
    public boolean setAlgorithm(String id, String newAlgorithm);

}
