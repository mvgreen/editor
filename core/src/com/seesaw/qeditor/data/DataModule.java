package com.seesaw.qeditor.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.Date;

/** Класс является СУБД, данные хранятся в отдельном объекте в виде HashMap-ов.
 *  В целях безопасности методы класса не позволяют работать с полями базы напрямую. */
public class DataModule implements Data {

    private Storage storage;
    private Author author;

    private FileHandle databaseFile;

    public DataModule(){
        this(Gdx.files.local("data/defaultDB.json"));
    }

    public DataModule(FileHandle file){
        databaseFile = file;
        author = new Author();
        if (!databaseFile.exists()){
            storage = new Storage();
            saveDataBase();
        }
        loadDataBase();
    }

    @Override
    public void setAuthorID(String ID) {
        author.setID(ID);
    }

    @Override
    public String getAuthorID() {
        return author.getID();
    }

    @Override
    public void setLastIndex(int type, int index){
        author.setLastIndex(type, index);
    }

    @Override
    public String getNewIndex(int type) {
        return author.getNewIndex(type);
    }

    @Override
    public void saveAuthorInfo() {
        author.savePrefs();
    }

    @Override
    public void loadAuthorInfo() {
        author.loadPrefs();
    }

    @Override
    public void loadDataBase() {
        Json json = new Json();
        storage = json.fromJson(Storage.class, databaseFile);
        loadAuthorInfo();
    }

    @Override
    public void saveDataBase() {
        Json json = new Json();
        json.toJson(storage, databaseFile);
        saveAuthorInfo();
    }

    @Override
    public void createStep(String id) {
        Step newStep = new Step(id);
        storage.steps.put(newStep.id, newStep);
    }

    @Override
    public void deleteStep(String id) {
        storage.steps.remove(id);
    }

    @Override
    public String[] getSteps() {
        String[] array = new String[storage.steps.size()];
        int i = 0;
        for (Step step : storage.steps.values()){
            array[i] = step.id;
            i++;
        }
        return array;
    }

    @Override
    public String getStepText(String id) {
        Step s = storage.steps.get(id);
        return s != null ? s.text : "<Step is not exists>";
    }

    @Override
    public boolean setStepText(String id, String newText) {
        if (!storage.steps.containsKey(id))
            return false;
        storage.steps.get(id).text = newText;
        return true;
    }

    @Override
    public String[] getStepAnswers(String id) {
        if (!storage.steps.containsKey(id))
            return null;
        return (String[]) storage.steps.get(id).answers.toArray();
    }

    @Override
    public boolean addStepAnswer(String stepID, String answerID) {
        if (!storage.steps.containsKey(stepID) || !storage.answers.containsKey(answerID))
            return false;
        if (!storage.steps.get(stepID).answers.contains(answerID))
            storage.steps.get(stepID).answers.add(answerID);
        return true;
    }

    @Override
    public boolean removeStepAnswer(String stepID, String answerID) {
        if (!storage.steps.containsKey(stepID) || !storage.answers.containsKey(answerID))
            return false;
        storage.steps.get(stepID).answers.remove(answerID);
        return true;
    }

    @Override
    public String[] getStepTimers(String id) {
        if (!storage.steps.containsKey(id))
            return null;
        return (String[]) storage.steps.get(id).timers.toArray();
    }

    @Override
    public boolean addStepTimer(String stepID, String timerID) {
        if (!storage.steps.containsKey(stepID) || !storage.timers.containsKey(timerID))
            return false;
        if (!storage.steps.get(stepID).timers.contains(timerID))
            storage.steps.get(stepID).timers.add(timerID);
        return true;
    }

    @Override
    public boolean removeStepTimer(String stepID, String timerID) {
        if (!storage.steps.containsKey(stepID) || !storage.timers.containsKey(timerID))
            return false;
        storage.steps.get(stepID).timers.remove(timerID);
        return true;
    }

    @Override
    public void createAnswer(String id) {
        Answer newAnswer = new Answer(id);
        storage.answers.put(newAnswer.id, newAnswer);
    }

    @Override
    public void deleteAnswer(String id) {
        storage.answers.remove(id);
    }

    @Override
    public String[] getAnswers() {
        String[] array = new String[storage.answers.size()];
        int i = 0;
        for (Answer answer : storage.answers.values()){
            array[i] = answer.id;
            i++;
        }
        return array;
    }

    @Override
    public String getAnswerText(String id) {
        Answer a = storage.answers.get(id);
        return a != null ? a.text : "<Answer is not exists>";
    }

    @Override
    public boolean setAnswerText(String id, String newText) {
        if (!storage.answers.containsKey(id))
            return false;
        storage.answers.get(id).text = newText;
        return true;
    }

    @Override
    public ArrayList<ArrayList<String>> getAnswerScripts(String id) {
        return storage.answers.get(id).scripts;
    }

    @Override
    public boolean addAnswerScript(String answerID, String scriptID, int list) {
        if (!storage.answers.containsKey(answerID) || !storage.scripts.containsKey(scriptID) || list > 1 || list < 0)
            return false;

        ArrayList<String> array = storage.answers.get(answerID).scripts.get(list);

        if (!array.contains(scriptID))
            array.add(scriptID);
        return true;
    }

    @Override
    public boolean removeAnswerScript(String answerID, String scriptID, int list) {
        if (!storage.answers.containsKey(answerID) || !storage.scripts.containsKey(scriptID) || list > 1 || list < 0)
            return false;
        storage.answers.get(answerID).scripts.get(list).remove(scriptID);
        return true;
    }

    @Override
    public void createTimer(String id) {
        Timer newTimer = new Timer(id);
        storage.timers.put(newTimer.id, newTimer);
    }

    @Override
    public void deleteTimer(String id) {
        storage.timers.remove(id);
    }

    @Override
    public String[] getTimers() {
        String[] array = new String[storage.timers.size()];
        int i = 0;
        for (Timer timer : storage.timers.values()){
            array[i] = timer.id;
            i++;
        }
        return array;
    }

    @Override
    public Date getTimerTime(String id) {
        if (!storage.timers.containsKey(id))
            return null;
        return storage.timers.get(id).time;
    }

    @Override
    public boolean setTimerTime(String id, int hours, int minutes, int seconds) {
        if (!storage.timers.containsKey(id))
            return false;
        Timer t = storage.timers.get(id);
        t.time.setHours(hours);
        t.time.setMinutes(minutes);
        t.time.setSeconds(seconds);
        return true;
    }

    @Override
    public boolean setTimerTime(String id, Date time) {
        if (!storage.timers.containsKey(id))
            return false;
        storage.timers.get(id).time = time;
        return true;
    }

    @Override
    public int getTimerType(String id) {
        Timer t = storage.timers.get(id);
        return t != null ? t.type : 0;
    }

    @Override
    public boolean setTimerType(String id, int type) {
        if (!storage.timers.containsKey(id) || type < -1 || type > 1)
            return false;
        storage.timers.get(id).type = type;
        return true;
    }

    @Override
    public boolean getTimerAutoCancel(String id) {
        Timer t = storage.timers.get(id);
        return t != null && t.autoCancel;
    }

    @Override
    public boolean setTimerAutoCancel(String id, boolean autoCancel) {
        if (!storage.timers.containsKey(id))
            return false;
        storage.timers.get(id).autoCancel = autoCancel;
        return true;
    }

    @Override
    public ArrayList<ArrayList<String>> getTimerScripts(String id) {
        return storage.timers.get(id).scripts;
    }

    @Override
    public boolean addTimerScript(String timerID, String scriptID, int list) {
        if (!storage.timers.containsKey(timerID) || !storage.scripts.containsKey(scriptID) || list > 1 || list < 0)
            return false;

        ArrayList<String> array = storage.timers.get(timerID).scripts.get(list);

        if (!array.contains(scriptID))
            array.add(scriptID);
        return true;
    }

    @Override
    public boolean removeTimerScript(String timerID, String scriptID, int list) {
        if (!storage.timers.containsKey(timerID) || !storage.scripts.containsKey(scriptID) || list > 1 || list < 0)
            return false;
        storage.timers.get(timerID).scripts.get(list).remove(scriptID);
        return true;
    }

    @Override
    public void createScript(String id) {
        Script newScript = new Script(id);
        storage.scripts.put(newScript.id, newScript);
    }

    @Override
    public void deleteScript(String id) {
        storage.scripts.remove(id);
    }

    @Override
    public String[] getScripts() {
        String[] array = new String[storage.scripts.size()];
        int i = 0;
        for (Script script : storage.scripts.values()){
            array[i] = script.id;
            i++;
        }
        return array;
    }

    @Override
    public String getScriptCondition(String id) {
        Script s = storage.scripts.get(id);
        return s != null ? s.condition : "";
    }

    @Override
    public boolean setScriptCondition(String id, String newCondition) {
        if (!storage.scripts.containsKey(id))
            return false;
        storage.scripts.get(id).condition = newCondition;
        return true;
    }

    @Override
    public String getScriptAlgorithm(String id) {
        Script s = storage.scripts.get(id);
        return s != null ? s.algorithm : "";
    }

    @Override
    public boolean setAlgorithm(String id, String newAlgorithm) {
        if (!storage.scripts.containsKey(id))
            return false;
        storage.scripts.get(id).algorithm = newAlgorithm;
        return true;
    }
}
