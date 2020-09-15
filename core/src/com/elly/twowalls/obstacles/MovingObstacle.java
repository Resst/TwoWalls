package com.elly.twowalls.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.tools.motions.Motion;
import com.elly.twowalls.obstacles.tools.ObstacleShape;
import com.elly.twowalls.tools.Constants;

public class MovingObstacle extends Obstacle {

    protected Array<Array<Motion>> motions = new Array<>();
    protected boolean stopped;

    protected float triggerRange = Constants.WORLD_HEIGHT * 7 / 10;
    protected float triggerZone;
    protected boolean triggered = false;


    public MovingObstacle(Level level, Vector2 position, Vector2 size, ObstacleShape type) {
        super(level, position, size, type);
        setTriggerRange(triggerRange);
    }

    @Override
    protected void appear() {
        super.appear();
    }


    @Override
    protected void act(float dt) {
        if (!triggered) {
            checkTriggers();
        } else if (!stopped) {
            if (!makeMoves(dt))
                stopped = true;
            updateObject();
        }
    }

    private boolean makeMoves(float dt) {
        boolean made = false;
        for (Array<Motion> arr : motions) {
            if (!arr.isEmpty() && !arr.get(0).isEnded()) {
                arr.get(0).make(this, dt);
                made = true;
            }else if (!arr.isEmpty() && arr.get(0).isEnded()){
                arr.removeIndex(0);
                made = true;
            }
        }
        return made;
    }

    private void checkTriggers() {
        triggered = level.getPlayer().getPosition().y > triggerZone;
    }

    @Override
    protected boolean shouldBeDestroyed() {
        return super.shouldBeDestroyed() && stopped;
    }

    public MovingObstacle addMotion(Motion motion, int layer) {
        while (motions.size <= layer)
            motions.add(new Array<Motion>());
        motions.get(layer).add(motion);
        return this;
    }

    public MovingObstacle addMotion(Motion motion) {
        while (motions.size == 0)
            motions.add(new Array<Motion>());
        motions.get(0).add(motion);
        return this;
    }

    public MovingObstacle setTriggerRange(float range) {
        triggerRange = range;
        triggerZone = position.y - range;
        return this;
    }

    @Override
    public MovingObstacle setAngle(float angle) {
        super.setAngle(angle);
        return this;
    }

    @Override
    public MovingObstacle setPosition(Vector2 position) {
        super.setPosition(position);
        return this;
    }

    @Override
    public Obstacle translate(float x, float y) {
        super.translate(x, y);
        return this;
    }

    @Override
    public MovingObstacle setSize(Vector2 size) {
        super.setSize(size);
        return this;
    }

    public MovingObstacle scaleSize(float scalar){
        super.scaleSize(scalar);
        return this;
    }

    @Override
    public MovingObstacle setOrigin(Vector2 origin) {
        super.setOrigin(origin);
        return this;
    }
    @Override
    public MovingObstacle setOrigin(float x, float y) {
        super.setOrigin(x, y);
        return this;
    }
    @Override
    public MovingObstacle setOriginPercents(Vector2 origin) {
        super.setOriginPercents(origin);
        return this;
    }
    @Override
    public MovingObstacle setOriginPercents(float x, float y) {
        super.setOriginPercents(x, y);
        return this;
    }

    @Override
    public MovingObstacle setShape(ObstacleShape type) {
        super.setShape(type);
        return this;
    }

    @Override
    public MovingObstacle setSprite(Texture texture){
        super.setSprite(texture);
        return this;
    }

    @Override
    public MovingObstacle setColor(float r, float g, float b, float a){
        super.setColor(r, g, b, a);
        return this;
    }

    @Override
    public MovingObstacle setColor(float r, float g, float b){
        super.setColor(r, g, b);
        return this;
    }

    @Override
    public MovingObstacle setColor(Color color) {
        super.setColor(color);
        return this;
    }
}
