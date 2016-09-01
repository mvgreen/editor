package com.seesaw.qeditor.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.LinkedList;

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
        author = new Author();
        databaseFile = file;
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
    public Step getStep(String id) {
        return storage.steps.containsKey(id) ? storage.steps.get(id) : null;
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
    public Clock getStepDelay(String id) {
        Step s = storage.steps.get(id);
        return s != null ? s.delay : null;
    }

    @Override
    public boolean setStepDelay(String id, Clock newDelay) {
        if (!storage.steps.containsKey(id) || newDelay == null)
            return false;
        storage.steps.get(id).delay = newDelay;
        return true;
    }

    @Override
    public boolean setStepDelay(String id, int hours, int minutes, int seconds) {
        if (!storage.steps.containsKey(id))
            return false;
        storage.steps.get(id).delay.setTime(hours, minutes, seconds);
        return true;
    }

    @Override
    public String[] getStepAnswers(String id) {
        if (!storage.steps.containsKey(id) || storage.steps.get(id).answers.size() == 0)
            return new String[0];
        String[] array = new String[storage.steps.get(id).answers.size()];
        for (int i = 0; i < array.length; i++){
            array[i] = (String) storage.steps.get(id).answers.toArray()[i];
        }
        return array;
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
        if (!storage.steps.containsKey(stepID))
            return false;
        storage.steps.get(stepID).answers.remove(answerID);
        return true;
    }

    @Override
    public String[] getStepTimers(String id) {
        if (!storage.steps.containsKey(id) || storage.steps.get(id).timers.size() == 0)
            return new String[0];
        String[] array = new String[storage.steps.get(id).timers.size()];
        for (int i = 0; i < array.length; i++){
            array[i] = (String) storage.steps.get(id).timers.toArray()[i];
        }
        return array;
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
        if (!storage.steps.containsKey(stepID))
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
    public Answer getAnswer(String id) {
        return storage.answers.containsKey(id) ? storage.answers.get(id) : null;
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
    public String getAnswerStep(String id) {
        return storage.answers.containsKey(id) ? storage.answers.get(id).step : "<Answer is not exists>";
    }

    @Override
    public boolean setAnswerStep(String id, String newStep) {
        if (!storage.answers.containsKey(id) || newStep == null)
            return false;
        storage.answers.get(id).step = newStep;
        return true;
    }

    @Override
    public boolean answerHasStep(String id) {
        return storage.answers.containsKey(id) && getAnswer(id).hasStep();
    }

    @Override
    public ArrayList<String> getAnswerScripts(String id) {
        return storage.answers.get(id).scripts;
    }

    @Override
    public boolean addAnswerScript(String answerID, String scriptID) {
        if (!storage.answers.containsKey(answerID) || !storage.scripts.containsKey(scriptID))
            return false;

        if (!storage.answers.get(answerID).scripts.contains(scriptID))
            storage.answers.get(answerID).scripts.add(scriptID);
        return true;
    }

    @Override
    public boolean removeAnswerScript(String answerID, String scriptID) {
        if (!storage.answers.containsKey(answerID))
            return false;
        storage.answers.get(answerID).scripts.remove(scriptID);
        return true;
    }

    @Override
    public boolean answerHasScripts(String id) {
        return getAnswer(id).hasScripts();
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
    public Timer getTimer(String id) {
        return storage.timers.containsKey(id) ? storage.timers.get(id) : null;
    }

    @Override
    public Clock getTimerTime(String id) {
        if (!storage.timers.containsKey(id))
            return null;
        return storage.timers.get(id).time;
    }

    @Override
    public boolean setTimerTime(String id, int hours, int minutes, int seconds) {
        if (!storage.timers.containsKey(id))
            return false;
        storage.timers.get(id).time.setTime(hours, minutes, seconds);
        return true;
    }

    @Override
    public boolean setTimerTime(String id, Clock time) {
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
    public String getTimerStep(String id) {
        return storage.timers.containsKey(id) ? storage.timers.get(id).step : "<Timer is not exists>";
    }

    @Override
     public boolean setTimerStep(String id, String newStep) {
        if (!storage.timers.containsKey(id) || newStep == null)
            return false;
        storage.timers.get(id).step = newStep;
        return true;
    }

    @Override
    public boolean timerHasStep(String id) {
        return getTimer(id).hasStep();
    }

    @Override
    public ArrayList<String> getTimerScripts(String id) {
        return storage.timers.get(id).scripts;
    }

    @Override
    public boolean addTimerScript(String timerID, String scriptID) {
        if (!storage.timers.containsKey(timerID) || !storage.scripts.containsKey(scriptID))
            return false;

        if (!storage.timers.get(timerID).scripts.contains(scriptID))
            storage.timers.get(timerID).scripts.add(scriptID);
        return true;
    }

    @Override
    public boolean removeTimerScript(String timerID, String scriptID) {
        if (!storage.timers.containsKey(timerID))
            return false;
        storage.timers.get(timerID).scripts.remove(scriptID);
        return true;
    }

    @Override
    public boolean timerHasScripts(String id) {
        return getTimer(id).hasScripts();
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

    @Override
    public ArrayList<LinkedList<String>>
    find(String substring, boolean inSteps, boolean inAnswers, boolean inTimers, boolean inScripts) {
        ArrayList<LinkedList<String>> array = new ArrayList<LinkedList<String>>(4);

        for (int i = 0; i < 4; i++)
            array.add(new LinkedList<String>());

        if (substring == null || substring.trim().equals(""))
            return array;

        if (inSteps)
            for (Step step : storage.steps.values()){
                if (step.id.contains(substring) || step.text.contains(substring))
                    array.get(TYPE_STEP).add(step.id);
            }

        if (inAnswers)
            for (Answer answer : storage.answers.values()){
                if (answer.id.contains(substring) || answer.text.contains(substring))
                    array.get(TYPE_ANSW).add(answer.id);
            }

        if (inTimers)
            for (Timer timer : storage.timers.values()){
                if (timer.id.contains(substring))
                    array.get(TYPE_TIMER).add(timer.id);
            }

        if (inScripts)
            for (Script script : storage.scripts.values()){
                if (script.id.contains(substring) || script.algorithm.contains(substring))
                    array.get(TYPE_SCRIPT).add(script.id);
            }

        return array;
    }
}
