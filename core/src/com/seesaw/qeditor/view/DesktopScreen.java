package com.seesaw.qeditor.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.seesaw.qeditor.QuestEditor;

public class DesktopScreen implements Screen {

    QuestEditor game;

    public DesktopScreen(QuestEditor g) {
        game = g;
    }

    @Override
    public void show() {

        /** Основная разметка */
        final Stack stack = (Stack) game.createActor(QuestEditor.STACK, "default");
        stack.setFillParent(true);
        final WidgetGroup desktop = (WidgetGroup) game.createActor(QuestEditor.DESKTOP, "default");
        final Table floatingButtons = (Table) game.createActor(QuestEditor.TABLE, "default");
        stack.add(desktop);
        stack.add(floatingButtons);

        /** Разметка меню */
        final Table navigationMenu = (Table) game.createActor(QuestEditor.TABLE, "default");
        final ScrollPane scrollPane = (ScrollPane) game.createActor(QuestEditor.SCROLL_PANE, "default");
        final Table list = (Table) game.createActor(QuestEditor.TABLE, "default");
        scrollPane.setWidget(list);
        final Button closeButton = (Button) game.createActor(QuestEditor.BUTTON, "default");
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stack.removeActor(navigationMenu);
            }
        });
        navigationMenu.add(closeButton).top().left().width(game.H/8).height(game.H/8).fill();
        navigationMenu.row();
        navigationMenu.add(scrollPane).top().expandX().fill();
        navigationMenu.row();
        navigationMenu.add().expand();

        /** Разметка элементов меню */
        TextButton createButton = (TextButton) game.createActor(QuestEditor.TEXT_BUTTON, "default");
        createButton.setText("Create...");
        TextButton stepsButton = (TextButton) game.createActor(QuestEditor.TEXT_BUTTON, "default");
        stepsButton.setText("Steps");
        TextButton answersButton = (TextButton) game.createActor(QuestEditor.TEXT_BUTTON, "default");
        answersButton.setText("Answers");
        TextButton timersButton = (TextButton) game.createActor(QuestEditor.TEXT_BUTTON, "default");
        timersButton.setText("Timers");
        TextButton scriptsButton = (TextButton) game.createActor(QuestEditor.TEXT_BUTTON, "default");
        scriptsButton.setText("Scripts");
        list.add(createButton).expandX().height(game.H/8).fill();
        list.row();
        list.add(stepsButton).expandX().height(game.H/8).fill();
        list.row();
        list.add(answersButton).expandX().height(game.H/8).fill();
        list.row();
        list.add(timersButton).expandX().height(game.H/8).fill();
        list.row();
        list.add(scriptsButton).expandX().height(game.H/8).fill();

        /** Разметка угловых кнопок */
        final Button removeElementButton = (Button) game.createActor(QuestEditor.BUTTON, "default");
        final Button navigationMenuButton = (Button) game.createActor(QuestEditor.BUTTON, "default");
        navigationMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stack.add(navigationMenu);
            }
        });
        floatingButtons.add(navigationMenuButton).top().left().width(game.H/8).height(game.H/8).fill();
        floatingButtons.add().expand();
        floatingButtons.row();
        floatingButtons.add();
        floatingButtons.add(removeElementButton).bottom().right().width(game.H/8).height(game.H/8).fill();

        game.stage.addActor(stack);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
