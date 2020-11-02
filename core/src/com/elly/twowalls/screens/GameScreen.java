package com.elly.twowalls.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.levels.Level5;
import com.elly.twowalls.tools.Constants;
import com.elly.twowalls.tools.DrawQueue;


public class GameScreen implements Screen {

    private final GameClass game;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private Level level;
    private Level disposing;
    private Box2DDebugRenderer b2dr = new Box2DDebugRenderer();
    private DrawQueue drawQueue = new DrawQueue();
    private Stage stage;
    private Button buttonBack;
    private boolean paused = false;
    private boolean levelEnded = false;


    public GameScreen(GameClass game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, gamecam);
        gamecam.position.set(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, 0);
        createHud();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        gamecam.position.set(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, 0);
    }

    @Override
    public void render(float delta) {
        if (!paused) {
            update(delta);

            Gdx.gl.glClearColor(0,0,0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            game.getBatch().setProjectionMatrix(gamecam.combined);
            game.getBatch().begin();
            draw(game.getBatch());
            game.getBatch().end();

            b2dr.render(level.getWorld(), gamecam.combined);

            stage.act();
            stage.draw();


            if (levelEnded){
                levelEnded = false;
                game.setScreen(game.getLevelChooseScreen());
            }
        }
    }

    public void update(float dt) {
        handleInput();
        level.update(dt);
    }

    public void draw(SpriteBatch batch) {
        drawQueue.draw(batch);
    }

    private void handleInput() {
        if (Gdx.input.justTouched())
            level.getPlayer().changeWall();
    }

    public GameScreen setLevel(Level level) {
        this.level = level;
        return this;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height);
        level.resize();
        buttonBack.setPosition(0, stage.getViewport().getWorldHeight(), Align.topLeft);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void hide() {
        level.dispose();
    }

    @Override
    public void dispose() {
        level.dispose();
        drawQueue.clear();
    }

    public void end(){
        levelEnded = true;
    }

    public void reset() {
        level.reset();
    }

    private void createHud(){
        Viewport stageViewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(stageViewport, game.getBatch());
        Skin skin = game.getSkin();

        stage.act();
        stage.draw();

        buttonBack = new Button(skin, "back");
        buttonBack.setTransform(true);
        buttonBack.setSize(Constants.CELL_SIZE, Constants.CELL_SIZE / 2);
        buttonBack.setTransform(false);
        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getLevelChooseScreen());
            }
        });
        stage.addActor(buttonBack);
        stage.act();
        stage.draw();
    }


    public GameClass getGame() {
        return game;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public DrawQueue getDrawQueue() {
        return drawQueue;
    }

    public Level getLevel() {
        return level;
    }
}
