package com.elly.twowalls.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.levels.Level1;
import com.elly.twowalls.screens.UI.ClickableImage;
import com.elly.twowalls.screens.UI.LevelChooseImage;
import com.elly.twowalls.screens.UI.ScreenChooseImage;
import com.elly.twowalls.tools.Constants;

import java.beans.EventHandler;

public class LevelChooseScreen implements Screen {

    private GameClass game;
    private Stage stage;
    private Viewport viewport;
    ClickableImage level1;
    ClickableImage backToMainScreen;

    public LevelChooseScreen(GameClass game){
        this.game = game;
        viewport = new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        init();
    }

    private void init(){
        Image background = new Image(game.getManager().background.getTexture());
        background.setPosition(0,0);
        background.setSize(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        stage.addActor(background);
        initButtons();
    }

    private void initButtons(){
        level1 = new LevelChooseImage(game.getManager().square.getTexture(),game, Level1.class);
        level1.setPosition(Constants.CELL_SIZE, Constants.WORLD_HEIGHT  - Constants.CELL_SIZE * 2.5f);
        level1.setSize(Constants.CELL_SIZE, Constants.CELL_SIZE);
        stage.addActor(level1);

        backToMainScreen = new ScreenChooseImage(game.getManager().ui.arrowBack(), game.getMainScreen(), game);
        backToMainScreen.setSize(Constants.CELL_SIZE, Constants.CELL_SIZE);
        backToMainScreen.setPosition(0,0);
        stage.addActor(backToMainScreen);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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

    }
}
