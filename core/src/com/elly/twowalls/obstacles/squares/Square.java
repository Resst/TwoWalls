package com.elly.twowalls.obstacles.squares;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.MovingObstacle;
import com.elly.twowalls.obstacles.tools.ObstacleType;

public class Square extends MovingObstacle {

    public Square(Level level, float y, boolean onRightWall){
        super(level, new Vector2(onRightWall ? level.rightBorder : level.leftBorder + level.borderSize, y),
                new Vector2(onRightWall ? -standardSize : standardSize, standardSize), ObstacleType.SQUARE);
    }

    @Override
    protected Square setSprite() {
        sprite = new Sprite(getManager().square.getTexture());
        return this;
    }

    public Square setSprite(Texture texture){
        sprite = new Sprite(texture);
        return this;
    }
}
