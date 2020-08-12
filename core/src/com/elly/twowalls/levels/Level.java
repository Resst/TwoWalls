package com.elly.twowalls.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.elly.twowalls.Player;
import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.obstacles.squares.Square;
import com.elly.twowalls.obstacles.tools.ObstacleCreator;
import com.elly.twowalls.obstacles.triangles.MovingTriangle;
import com.elly.twowalls.obstacles.triangles.RotatingTriangle;
import com.elly.twowalls.obstacles.triangles.StaticTriangle;
import com.elly.twowalls.obstacles.triangles.Triangle;
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
    private World world;

    protected float lastY = Constants.WORLD_HEIGHT * 2;
    protected ObstacleCreator obstacleCreator;

    private int score = 0;
    private Preferences prefs;

    private boolean gameOver = false;

    private OrthographicCamera camera;

    //presets of complex obstacles
    protected Presets presets;

    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;

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

        backgroundTexture = screen.getGame().getManager().background.getTexture();
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backgroundRegion = new TextureRegion(backgroundTexture);
        backgroundRegion.setRegion(0, (int) (camera.viewportHeight - camera.position.y) - 1,
                ((int) camera.viewportWidth) + 1, ((int) camera.viewportHeight) + 2);

        screen.getDrawQueue().add(this, 20);
        world = new World(Vector2.Zero, true);
        world.setContactListener(new BodyContactListener());
        obstacles = new Array<>();
        obstacleCreator = new ObstacleCreator(this);
        presets = new Presets(this);
        initPrefs();
        initPlayer();
        createObstacles();

    }

    public abstract void createObstacles();

    public void update(float dt) {

        //in case of death
        if (gameOver && Gdx.input.justTouched()) {
            reset();
            gameOver = false;
        }

        //Updating player
        player.update(dt);

        //Updating obstacles
        for (Obstacle o : obstacles) {
            o.update(dt);
        }

        //for moving background
        if (!player.isDestroyed())
            backgroundRegion.scroll(0, -player.speed / camera.viewportHeight * dt);

        //world step for checking collisions
        world.step(1, 5, 5);


    }


    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(backgroundRegion, 0, camera.position.y - camera.viewportHeight / 2);
    }


    protected void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
        space(obstacle.getSize().y);
    }

    protected void space(float spaceSize) {
        lastY += spaceSize;
    }

    protected void space() {
        lastY += Constants.CELL_SIZE;
    }

    protected Triangle addTriangle(float y, boolean onRightWall) {
        Triangle obstacle = obstacleCreator.addTriangle(y, onRightWall);
        addObstacle(obstacle);
        return obstacle;
    }

    protected Triangle addTriangle(boolean onRightWall) {
        return addTriangle(lastY, onRightWall);
    }

    protected Square addSquare(float y, boolean onRightWall) {
        Square obstacle = obstacleCreator.addSquare(y, onRightWall);
        addObstacle(obstacle);
        return obstacle;
    }

    protected Square addSquare(boolean onRightWall) {
        return addSquare(lastY, onRightWall);
    }


    protected StaticTriangle staticTriangle(float y, boolean onRightWall) {
        StaticTriangle obstacle = obstacleCreator.staticTriangle(y, onRightWall);
        addObstacle(obstacle);
        return obstacle;
    }

    protected StaticTriangle staticTriangle(boolean onRightWall) {
        return staticTriangle(lastY, onRightWall);
    }

    protected MovingTriangle movingTriangle(float y, boolean onRightWall) {
        MovingTriangle obstacle = obstacleCreator.movingTriangle(y, onRightWall);
        addObstacle(obstacle);
        return obstacle;
    }

    protected MovingTriangle movingTriangle(boolean onRightWall) {
        return movingTriangle(lastY, onRightWall);
    }

    protected RotatingTriangle rotatingTriangle(float y, boolean onRightWall) {
        RotatingTriangle obstacle = obstacleCreator.rotatingTriangle(y, onRightWall);
        addObstacle(obstacle);
        return obstacle;
    }

    protected RotatingTriangle rotatingTriangle(boolean onRightWall) {
        return rotatingTriangle(lastY, onRightWall);
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
        lastY = Constants.WORLD_HEIGHT * 2;
        camera.position.set(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, 0);
        createObstacles();
        score = 0;
    }

    public void gameOver() {
        gameOver = true;
    }

    public void dispose() {
        player.dispose();
        removeAllObstacles();
        world.dispose();
    }

    private void setBestScore(int newScore) {
        prefs.putInteger(Constants.BEST_SCORE_PREFERENCE, newScore);
        prefs.flush();
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


}
