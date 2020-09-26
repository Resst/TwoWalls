package com.elly.twowalls.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.levels.Level1;
import com.elly.twowalls.levels.Level2;
import com.elly.twowalls.levels.Level3;
import com.elly.twowalls.levels.Level4;
import com.elly.twowalls.levels.Level5;
import com.elly.twowalls.levels.Level6;

public class LevelChooseScreen implements Screen {

    private Stage stage;
    private TextButton[] buttons;
    private Table table;
    private GameClass game;
    private StretchViewport viewport;

    public LevelChooseScreen(final GameClass game) {
        int levelsSize = 6;
        this.game = game;
        viewport = new StretchViewport(800, 1000, new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        table = new Table();
        buttons = new TextButton[levelsSize];
        Skin skin = new Skin(Gdx.files.internal("Textures/default/skin/uiskin.json"));
        for (int i = 0; i < levelsSize; i++) {
            buttons[i] = new TextButton(String.valueOf(i + 1), skin);
            table.add(buttons[i]).width(200).height(200).pad(20);
            if (i % 2 == 1)
                table.row();
        }

        buttons[0].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getGameScreen().setLevel(new Level1(game.getGameScreen())));
            }
        });
        buttons[1].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getGameScreen().setLevel(new Level2(game.getGameScreen())));
            }
        });
        buttons[2].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getGameScreen().setLevel(new Level3(game.getGameScreen())));
            }
        });
        buttons[3].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getGameScreen().setLevel(new Level4(game.getGameScreen())));
            }
        });
        buttons[4].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getGameScreen().setLevel(new Level5(game.getGameScreen())));
            }
        });
        buttons[5].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getGameScreen().setLevel(new Level6(game.getGameScreen())));
            }
        });

        stage.addActor(table);
        table.debug();
        table.setPosition(200, levelsSize * 50 + 50);
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClearColor(9 / 256f, 102 / 256f, 28 / 256f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
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
        stage.dispose();
    }
}
