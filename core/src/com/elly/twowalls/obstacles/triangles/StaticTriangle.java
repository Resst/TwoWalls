package com.elly.twowalls.obstacles.triangles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.obstacles.tools.ObstacleType;

public class StaticTriangle extends Obstacle {

    public StaticTriangle(Level level, float y, boolean onRightWall){
        super(level, new Vector2(onRightWall ? level.rightBorder : level.leftBorder + level.borderSize, y),
                new Vector2(onRightWall ? -standardSize : standardSize, standardSize), ObstacleType.TRIANGLE);
        setColor(1,1,0);
    }

    @Override
    protected StaticTriangle setSprite() {
        sprite = new Sprite(getManager().staticTriangle.getTexture());
        return this;
    }

    public StaticTriangle setSprite(Texture texture){
        sprite = new Sprite(texture);
        return this;
    }
}
