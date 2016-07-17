package com.seesaw.qeditor.data;

import java.util.ArrayList;
import java.util.LinkedList;

public interface Data {

    final int TYPE_STEP = 0;
    final int TYPE_ANSW = 1;
    final int TYPE_TIMER = 2;
    final int TYPE_SCRIPT = 3;

    /** Заменяет метку автора. Метки ставятся в начале ID Шагов, Ответов, Таймеров и Скриптов.
     *  Метки существующих ID не будут заменяться. */
    void setAuthorID(String ID);

    /** Получить метку автора. Описание см. выше. */
    String getAuthorID();

    /** Задать индексы на случай сброса.
     *  type - Author.STEP / Author.ANSWER / Author.TIMER / Author.SCRIPT
     *  index - новое значение индекса. */
    void setLastIndex(int type, int index);

    /** Получить новый индекс для Шага, Ответа, Таймера или Скрипта.
     *  type - Author.STEP / Author.ANSWER / Author.TIMER / Author.SCRIPT. */
    String getNewIndex(int type);

    /** Сохранить автора в Preferences */
    void saveAuthorInfo();

    /** Загрузить информацию об авторе из Preferences */
    void loadAuthorInfo();


    /** Загружает БД из файлов */
    void loadDataBase();

    /** Сохраняет БД в файлы и данные автора в preferences */
    void saveDataBase();



    /** Создает новый Шаг и возвращает его ID */
    void createStep(String id);

    /** Удаляет Шаг с указанным ID */
    void deleteStep(String id);

    /** Возвращает список ID всех Шагов */
    String[] getSteps();


    /** Возвращает отображаемый текст Шага */
    String getStepText(String id);

    /** Заменяет текст Шага на новый */
    boolean setStepText(String id, String newText);

    /** Возвращает задержку перед выводом текста Шага.
     *  Если Шага не существует, возвращает null! */
    Clock getStepDelay(String id);

    /** Устанавливает новое значение для задержки Шага */
    boolean setStepDelay(String id, Clock newDelay);

    /** Устанавливает новое значение для задержки Шага */
    boolean setStepDelay(String id, int hours, int minutes, int seconds);

    /** Возвращает список связанных с Шагом Ответов */
    String[] getStepAnswers(String id);

    /** Присоединяет к Шагу указанный Ответ */
    boolean addStepAnswer(String stepID, String answerID);

    /** Отсоединяет Ответ от Шага, Ответ не удаляется из базы данных */
    boolean removeStepAnswer(String stepID, String answerID);


    /** Возвращает список связанных с Шагом Таймеров */
    String[] getStepTimers(String id);

    /** Присоединяет к Шагу указанный Таймер */
    boolean addStepTimer(String stepID, String timerID);

    /** Отсоединяет Таймер от Шага, Таймер не удаляется из базы данных */
    boolean removeStepTimer(String stepID, String timerID);



    /** Создает новый Ответ и возвращает его ID */
    void createAnswer(String id);

    /** Удаляет Ответ с указанным ID */
    void deleteAnswer(String id);

    /** Возвращает список ID всех Ответов */
    String[] getAnswers();


    /** Возвращает отображаемый текст Ответа */
    String getAnswerText(String id);

    /** Заменяет текст Шага на новый */
    boolean setAnswerText(String id, String newText);


    /** Возвращает id Шага, привязанного к Ответу или пустую строку "" */
    String getAnswerStep(String id);

    /** Устанавливает Шаг для безусловного перехода (ни один из скриптов не будет выполнен) */
    boolean setAnswerStep(String id, String newStep);


    /** Возвращает список связанных с Ответом Скриптов*/
    ArrayList<String> getAnswerScripts(String id);

    /** Присоединяет к Ответу указанный Скрипт */
    boolean addAnswerScript(String answerID, String scriptID);

    /** Отсоединяет Скрипт от Ответа, Скрипт не удаляется из базы данных */
    boolean removeAnswerScript(String answerID, String scriptID);



    /** Создает новый Таймер и возвращает его ID */
    void createTimer(String id);

    /** Удаляет Таймер с указанным ID */
    void deleteTimer(String id);

    /** Возвращает список ID всех Таймеров */
    String[] getTimers();


    /** Возвращает время, установленное в Таймере, тип Таймера не учитывается */
    Clock getTimerTime(String id);

    /** Заменяет время в Таймере на новое, тип Таймера не учитывается */
    boolean setTimerTime(String id, int hours, int minutes, int seconds);

    /** Аналогичный предыдущему */
    boolean setTimerTime(String id, Clock time);


    /** Возвращает тип Таймера, 1 - таймер прибавляется к текущему времени, -1 - отнимается, 0 - "будильник" */
    int getTimerType(String id);

    /** Задает тип Таймера, значения аналогичны предыдущему */
    boolean setTimerType(String id, int type);


    /** Если true, то Таймер отменяется при смене Шага */
    boolean getTimerAutoCancel(String id);

    /** Аналогично предыдущему */
    boolean setTimerAutoCancel(String id, boolean autoCancel);


    /** Возвращает присоединенный к Таймеру Шаг */
    String getTimerStep(String id);

    /** Устанавливает новый привязанный к Таймеру Шаг */
    boolean setTimerStep(String id, String newStep);


    /** Возвращает список связанных с Таймером Скриптов*/
    ArrayList<String> getTimerScripts(String id);

    /** Присоединяет к Таймером указанный Скрипт */
    boolean addTimerScript(String answerID, String scriptID);

    /** Отсоединяет Скрипт от Таймера, Скрипт не удаляется из базы данных */
    boolean removeTimerScript(String answerID, String scriptID);



    /** Создает новый Скрипт и возвращает его ID */
    void createScript(String id);

    /** Удаляет Скрипт с указанным ID */
    void deleteScript(String id);

    /** Возвращает список ID всех Скриптов */
    String[] getScripts();


    /** Возвращает условие выполнения Скрипта */
    String getScriptCondition(String id);

    /** Заменяет условие выполнения Скрипта на новое */
    boolean setScriptCondition(String id, String newCondition);


    /** Возвращает алгоритм Скрипта */
    String getScriptAlgorithm(String id);

    /** Заменяет алгоритм Скрипта на новый */
    boolean setAlgorithm(String id, String newAlgorithm);

    /** Возвращает список полей базы данных, содержащих данную подстроку.
     *  Поиск происходит по текстовым полям, в том числе id.
     *  Каждому типу поля соответствует свой массив.
     *  Для обращения к массиву с нужным типом используются константы, описанные в интерфейсе. */
    ArrayList<LinkedList<String>>
    find(String substring, boolean inSteps, boolean inAnswers, boolean inTimers, boolean inScripts);
}
