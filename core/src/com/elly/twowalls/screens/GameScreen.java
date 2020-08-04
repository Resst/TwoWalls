package com.elly.twowalls.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.levels.Level1;
import com.elly.twowalls.tools.Constants;
import com.elly.twowalls.tools.DrawQueue;

public class GameScreen implements Screen {

    private final GameClass game;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private Level level;
    private Box2DDebugRenderer b2dr = new Box2DDebugRenderer();
    private DrawQueue drawQueue = new DrawQueue();
    private Hud hud;
    private boolean paused = false;


    public GameScreen(GameClass game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        viewport = new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, gamecam);
        gamecam.position.set(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, 0);
        hud = new Hud(this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.getStage());
        gamecam.position.set(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, 0);
    }

    @Override
    public void render(float delta) {
        if (!paused) {
            update(delta);
            Gdx.gl.glClearColor(9 / 256f, 102 / 256f, 28 / 256f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.getBatch().setProjectionMatrix(gamecam.combined);
            game.getBatch().begin();
            draw(game.getBatch());
            game.getBatch().end();
            b2dr.render(level.getWorld(), gamecam.combined);

            hud.draw(game.getBatch());
        }
    }

    public void update(float dt) {
        handleInput();
        level.update(dt);
        hud.update(dt);
    }

    public void draw(SpriteBatch batch) {
        drawQueue.draw(batch);
    }

    private void handleInput() {
        if (Gdx.input.justTouched())
            level.getPlayer().changeWall();
    }

    public GameScreen setLevel(Level level){
        this.level = level;
        return this;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    }

    @Override
    public void dispose() {
        level.dispose();
        drawQueue.clear();
    }

    public void reset(){
        level.reset();
    }

    public GameClass getGame() {
        return game;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public DrawQueue getDrawQueue(){
        return drawQueue;
    }

    public Level getLevel(){
        return level;
    }
}
