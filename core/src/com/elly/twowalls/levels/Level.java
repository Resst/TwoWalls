package com.elly.twowalls.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.elly.twowalls.Player;
import com.elly.twowalls.obstacles.MovingObstacle;
import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.obstacles.Trigger;
import com.elly.twowalls.obstacles.tools.ObstacleCreator;
import com.elly.twowalls.obstacles.tools.Pallete;
import com.elly.twowalls.obstacles.tools.Presets;
import com.elly.twowalls.screens.GameScreen;
import com.elly.twowalls.tools.Assets.BackgroundAssets;
import com.elly.twowalls.tools.BodyContactListener;
import com.elly.twowalls.tools.Constants;
import com.elly.twowalls.tools.Drawable;

public abstract class Level implements Drawable {

    public final float borderSize = Constants.CELL_SIZE / 2;
    public float leftBorder, rightBorder, gameField;
    private boolean hasBorder = true;

    private GameScreen screen;
    private Player player;
    private Array<Obstacle> obstacles;
    private Array<Trigger> triggers;
    private World world;

    protected ObstacleCreator creator;

    private int score = 0;
    private Preferences prefs;

    private boolean gameOver = false;
    private boolean ended = false;

    private OrthographicCamera camera;

    //presets of complex obstacles
    protected Presets presets;
    protected Pallete pallete;

    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    public Level(GameScreen screen, boolean hasBorder) {
        //coordinates of borders
        this.hasBorder = hasBorder;
        if (hasBorder) {
            leftBorder = 0;
            rightBorder = Constants.WORLD_WIDTH - borderSize;
        } else {
            leftBorder = -borderSize;
            rightBorder = Constants.WORLD_WIDTH;
        }
        gameField = rightBorder - leftBorder - borderSize;

        this.screen = screen;
        camera = screen.getGamecam();

        //TODO Remake
        backgroundTexture = screen.getGame().getManager().background.getTexture(BackgroundAssets.FOREST_TEXTURE);

        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backgroundSprite = new Sprite(backgroundTexture);

        screen.getDrawQueue().add(this, 20);
        world = new World(Vector2.Zero, true);
        world.setContactListener(new BodyContactListener());
        obstacles = new Array<>();
        triggers = new Array<>();
        creator = new ObstacleCreator(this);
        presets = new Presets(this);
        initPallete();
        initPrefs();
        initPlayer();
        createObstaclesInThread();

    }

    public abstract void createObstacles();

    private void createObstaclesInThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                createObstacles();
            }
        }).start();
    }

    public void update(float dt) {

        //in case of death
        if (gameOver && Gdx.input.justTouched()) {
            reset();
            gameOver = false;
        }

        //if level completed and player is out of screen we leave
        if (ended && player.getPosition().y >= camera.position.y + camera.viewportHeight / 2){
            screen.end();
            return;
        }

        //Updating player
        player.update(dt);

        //Updating obstacles
        for (Obstacle o : obstacles) {
            o.update(dt);
        }
        for (Trigger t : triggers)
            t.update();

        //for moving background
        if (!player.isDestroyed() && !ended){
            backgroundSprite.scroll(0, -player.speed / Constants.WORLD_HEIGHT * dt);
            backgroundSprite.setY(camera.position.y - camera.viewportHeight / 2);
        }

        //world step for checking collisions
        world.step(1, 5, 5);
    }


    @Override
    public void draw(SpriteBatch batch) {
        backgroundSprite.draw(batch);
    }

    public void end(){
        ended = true;
    }

    public void addTrigger(Trigger trigger){
        triggers.add(trigger);
    }

    public void removeTrigger(Trigger trigger){
        triggers.removeValue(trigger, true);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void removeObstacle(Obstacle obstacle) {
        screen.getDrawQueue().get(obstacle.getLayer()).removeValue(obstacle, true);
        world.destroyBody(obstacle.getBody());
        obstacles.removeValue(obstacle, true);
    }

    public void incrementScore() {
        score++;
        if (score > getBestScore())
            setBestScore(score);
    }

    private void initPrefs() {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES_NAME);
        if (!prefs.contains(Constants.BEST_SCORE_PREFERENCE)) {
            prefs.putInteger(Constants.BEST_SCORE_PREFERENCE, 0);
            prefs.flush();
        }
    }

    private void initPlayer() {
        player = new Player(this, Constants.WORLD_HEIGHT, Constants.CELL_SIZE * 10);
    }

    protected void initPallete(){
        pallete = new Pallete();
        pallete.setStaticTriangleColor(Color.YELLOW);
        pallete.setMovingTriangleColor(Color.RED);
        pallete.setRotatingTriangleColor(Color.PURPLE);
    }

    private void removeAllObstacles() {
        while (!obstacles.isEmpty()) {
            if (obstacles.get(0).wasOnScreen) {
                obstacles.get(0).removeFromScreen();
            } else {
                Obstacle toRemove = obstacles.get(0);
                screen.getDrawQueue().get(toRemove.getLayer()).removeValue(toRemove, true);
                obstacles.removeIndex(0);
            }
        }
    }

    public void reset() {
        removeAllObstacles();
        player.reset();
        creator.reset();
        camera.position.set(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, 0);
        createObstaclesInThread();
        score = 0;
    }

    public void resize(){
        backgroundSprite.setSize(Constants.WORLD_WIDTH, camera.viewportHeight + 100);
        backgroundSprite.setX(leftBorder + borderSize);

    }

    public void gameOver() {
        gameOver = true;
    }

    public void dispose() {
        player.dispose();
        removeAllObstacles();
        world.dispose();
        screen.getDrawQueue().clear();
    }

    private void setBestScore(int newScore) {
        prefs.putInteger(Constants.BEST_SCORE_PREFERENCE, newScore);
        prefs.flush();
    }

    public boolean isEnded() {
        return ended;
    }

    public GameScreen getScreen() {
        return screen;
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public World getWorld() {
        return world;
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return prefs.getInteger(Constants.BEST_SCORE_PREFERENCE);
    }

    public ObstacleCreator getCreator() {
        return creator;
    }

    public Pallete getPallete() {
        return pallete;
    }

    public float getWidth() {
        return rightBorder - leftBorder - borderSize;
    }
}
