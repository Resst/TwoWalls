package com.elly.twowalls.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.tools.Constants;

public class TextureChooseScreen implements Screen {

    private GameClass game;
    private Stage stage;

    public TextureChooseScreen(GameClass game){
        this.game = game;
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, new OrthographicCamera()), game.getBatch());
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("it's me");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
