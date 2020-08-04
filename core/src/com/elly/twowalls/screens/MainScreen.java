package com.elly.twowalls.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.screens.UI.ClickableImage;
import com.elly.twowalls.screens.UI.LevelChooseImage;
import com.elly.twowalls.screens.UI.ScreenChooseImage;
import com.elly.twowalls.tools.Constants;

import java.util.concurrent.ConcurrentSkipListMap;

public class MainScreen implements Screen {

    private GameClass game;
    private Stage stage;


    public MainScreen(GameClass game){
        this.game = game;
        init();
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

    private void init(){
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT, new OrthographicCamera()), game.getBatch());

        ScreenChooseImage goToTexturesScreen = new ScreenChooseImage(game.getManager().square.getTexture(), game.getTextureChooseScreen(), game);
        goToTexturesScreen.setPosition(200,200);
        goToTexturesScreen.setSize(Constants.CELL_SIZE, Constants.CELL_SIZE);
        stage.addActor(goToTexturesScreen);

    }
}
