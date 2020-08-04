package com.elly.twowalls.obstacles.triangles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.MovingObstacle;
import com.elly.twowalls.obstacles.tools.ObstacleType;

public class Triangle extends MovingObstacle {

    public Triangle(Level level, float y, boolean onRightWall){
        super(level, new Vector2(onRightWall ? level.rightBorder : level.leftBorder + level.borderSize, y),
                new Vector2(onRightWall ? -standardSize : standardSize, standardSize), ObstacleType.TRIANGLE);
    }

    @Override
    protected Triangle setSprite() {
        sprite = new Sprite(getManager().staticTriangle.getTexture());
        return this;
    }

}
