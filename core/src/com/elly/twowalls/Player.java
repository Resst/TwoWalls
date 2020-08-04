package com.elly.twowalls;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.tools.Assets.PlayerAssets;
import com.elly.twowalls.tools.ColliderCreator;
import com.elly.twowalls.tools.Constants;
import com.elly.twowalls.tools.Drawable;

public class Player implements Drawable {
    private Level level;

    private Sprite playerSprite;
    private int layer = 1;
    private float size = Constants.CELL_SIZE;

    private Vector2 position;
    private Vector2 bodyPosition;
    private float minX, maxX;
    private Body body;
    private Fixture collider;

    private boolean setToDestroy;
    private boolean destroyed;

    public float speed, changeWallSpeed;
    private int changeWallSign = -1;

    public Player(Level level, float speed, float changeWallSpeed) {
        this.level = level;
        this.speed = speed;
        this.changeWallSpeed = changeWallSpeed;
        position = new Vector2(minX, Constants.CELL_SIZE * 1.5f);
        bodyPosition = new Vector2(position.x + size / 2, position.y + size / 2);
        //getting textures from MyAssetManager object

        minX = level.leftBorder + level.borderSize;
        maxX = level.rightBorder - size;

        body = ColliderCreator.createBody(level.getWorld(), new Vector2(minX * 2, size + size / 2), 0);
        collider = ColliderCreator.createBoxCollider(body, 0, 0, size / 2);
        collider.setUserData(this);

        playerSprite = new Sprite(level.getScreen().getGame().getManager().player.getTexture());
        playerSprite.setBounds(position.x, position.y, size, size);
        playerSprite.setOrigin(size / 2, size / 2);//Sprite should rotate around center

        level.getScreen().getDrawQueue().add(this, layer);

    }

    public void update(float dt) {
        if (!destroyed) {
            body.setAwake(true);
            move(dt);
            updateSprite();
        }
        if (setToDestroy)
            destroy();
    }

    public void draw(SpriteBatch batch) {
        playerSprite.draw(batch);
    }

    private void updateSprite() {
        playerSprite.setPosition(position.x, position.y);
        playerSprite.setRotation((position.x - minX) / (minX - maxX) * 360);
    }

    private void move(float dt) {
        position.x += changeWallSpeed * changeWallSign * dt;
        bodyPosition.x += changeWallSpeed * changeWallSign * dt;
        if (position.x < minX) {
            position.x = minX;
            bodyPosition.x = minX + size / 2;
        } else if (position.x > maxX) {
            position.x = maxX;
            bodyPosition.x = maxX + size / 2;
        }
        position.y += speed * dt;
        bodyPosition.y += speed * dt;
        body.setTransform(bodyPosition, (position.x - minX) / (minX - maxX) * 6.28f);

        //moving camera
        level.getScreen().getGamecam().translate(0, speed * dt);
        level.getScreen().getGamecam().update();
    }

    public void changeWall() {
        changeWallSign = -changeWallSign;
    }

    public void takeDamage() {
        setToDestroy = true;
    }

    public void reset(){
        position.set(minX, Constants.CELL_SIZE * 1.5f);
        bodyPosition.set(position.x + size / 2, position.y + size / 2);
        level.getScreen().getDrawQueue().get(layer).add(this);
        changeWallSign = -1;
        updateSprite();
        destroyed = false;
    }

    public void destroy() {
        level.getScreen().getDrawQueue().get(layer).removeValue(this, true);
        level.gameOver();
        setToDestroy = false;
        destroyed = true;
    }


    public Vector2 getPosition() {
        return position;
    }

    public void dispose(){
        body.destroyFixture(collider);
        level.getWorld().destroyBody(body);
        level = null;
        playerSprite = null;
        position = null;
        bodyPosition = null;
    }
}
