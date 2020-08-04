package com.elly.twowalls.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.tools.ObstacleType;
import com.elly.twowalls.tools.ColliderCreator;
import com.elly.twowalls.tools.Constants;
import com.elly.twowalls.tools.Drawable;
import com.elly.twowalls.tools.MyAssetManager;

public abstract class Obstacle implements Drawable {

    protected Vector2 position;
    protected Vector2 size;
    protected Vector2[] vertices;
    private ObstacleType type;
    public static final float standardSize = Constants.CELL_SIZE;
    private float maxSize = 2 * standardSize;


    private OrthographicCamera camera;
    public boolean wasOnScreen;
    private boolean destroyed = false;

    protected Level level;

    protected Sprite sprite;
    protected Color color;
    private int layer = 3;
    protected Vector2 origin = new Vector2(0, 0);
    protected float angle = 0;

    private Body body;
    private Fixture collider;
    protected Shape shape;
    private Vector2 bodyPosition;

    public Obstacle(Level level, Vector2 position, Vector2 size, ObstacleType type) {
        this.level = level;
        this.position = position;
        this.size = size;
        this.type = type;

        color = new Color(1, 1, 1, 1);

        setShape(type);
        level.getScreen().getDrawQueue().add(this, layer);
        camera = level.getScreen().getGamecam();

    }

    //for instance: sprite = new Sprite(bgAssets.getScreen().getGame().getManager().staticTriangle.getStaticTriangle())
    protected abstract Obstacle setSprite();


    protected void appear() {
        setSprite();
        sprite.setPosition(position.x, position.y);
        sprite.setSize(size.x, size.y);
        sprite.setOrigin(origin.x, origin.y);
        sprite.setRotation(angle);
        sprite.setColor(color);

        position.add(origin);
        body = ColliderCreator.createBody(level.getWorld(), position, 0);
        if (type != ObstacleType.NONE) {
            collider = ColliderCreator.createCollider(body, shape);
            collider.setUserData(this);
        }
        body.setTransform(position, (float) Math.toRadians(angle));
        position.sub(origin);

        level.incrementScore();
        wasOnScreen = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (wasOnScreen)
            sprite.draw(batch);
    }

    public void update(float dt) {
        if (!wasOnScreen && onScreen())
            appear();

        if (!destroyed)
            act(dt);

        if (shouldBeDestroyed())
            removeFromScreen();
    }

    protected void updateObject() {
        sprite.setPosition(position.x, position.y);
        sprite.setRotation(angle);
        position.add(origin);
        getBody().setTransform(position, (float) Math.toRadians(angle));
        position.sub(origin);
    }


    protected void act(float dt) {
    }

    public void updateSprite() {
        sprite.setPosition(position.x, position.y);
    }

    public void updateBody(float angle) {
        body.setTransform(position, angle);
    }

    public void removeFromScreen() {
        if (type != ObstacleType.NONE) {
            body.destroyFixture(collider);
            shape.dispose();
        }
        level.removeObstacle(this);
        destroyed = true;
    }

    public boolean onScreen() {
        return (position.y - size.len() < camera.position.y + camera.viewportHeight / 2);
    }

    protected boolean shouldBeDestroyed() {
        return !destroyed && position.y + maxSize < camera.position.y - camera.viewportHeight / 2;
    }

    public Obstacle setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public Obstacle setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        return this;
    }

    public Obstacle setSize(Vector2 size) {
        this.size = size;
        setShape(type);
        return this;
    }

    public Obstacle setSize(float x, float y) {
        return setSize(new Vector2(x, y));
    }

    public Obstacle setOrigin(Vector2 origin) {
        if (shape == null) {

        } else if (shape.getClass() == PolygonShape.class) {

            for (Vector2 v : vertices) {
                v.x += this.origin.x - origin.x;
                v.y += this.origin.y - origin.y;
            }
            ((PolygonShape) shape).set(vertices);

        } else if (shape.getClass() == CircleShape.class) {
            ((CircleShape) shape).setPosition(new Vector2(origin.x, origin.y));
        }

        maxSize = origin.len() + size.len();
        this.origin = origin;
        return this;
    }

    public Obstacle setOrigin(float x, float y) {
        return setOrigin(new Vector2(x, y));
    }

    public Obstacle setOriginPercents(Vector2 origin) {
        return setOrigin(new Vector2(origin.x * size.x, origin.y * size.y));
    }

    public Obstacle setOriginPercents(float x, float y) {
        return setOrigin(x * size.x, y * size.y);
    }

    public Obstacle setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    public Obstacle setShape(ObstacleType type) {
        switch (type) {
            case NONE: {
                shape = null;
                break;
            }
            case TRIANGLE: {
                vertices = new Vector2[3];
                vertices[0] = new Vector2(new Vector2(Vector2.Zero));
                vertices[1] = new Vector2(0, size.y);
                vertices[2] = new Vector2(size.x, size.y / 2);

                shape = new PolygonShape();
                ((PolygonShape) shape).set(vertices);
                break;
            }
            case SQUARE: {
                vertices = new Vector2[4];
                vertices[0] = new Vector2(new Vector2(Vector2.Zero));
                vertices[1] = new Vector2(0, size.y);
                vertices[2] = new Vector2(size.x, 0);
                vertices[3] = new Vector2(size.x, size.y);
                shape = new PolygonShape();
                ((PolygonShape) shape).set(vertices);
                break;
            }
            case CIRCLE: {
                shape = new CircleShape();
                shape.setRadius(size.x / 2);
                break;
            }
        }
        this.type = type;
        setOrigin(origin);
        return this;
    }

    public Obstacle setSprite(Texture texture) {
        sprite = new Sprite(texture);
        return this;
    }

    public Obstacle setColor(float r, float g, float b, float a) {
        color.set(r, g, b, a);
        return this;
    }

    public Obstacle setColor(float r, float g, float b) {
        color.set(r, g, b, 1);
        return this;
    }


    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public MyAssetManager getManager() {
        return level.getScreen().getGame().getManager();
    }

    public Body getBody() {
        return body;
    }

    public int getLayer() {
        return layer;
    }

    public float getAngle() {
        return angle;
    }

    public Level getLevel() {
        return level;
    }

}
