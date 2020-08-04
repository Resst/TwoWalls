package com.elly.twowalls.obstacles.triangles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.tools.ObstacleType;
import com.elly.twowalls.obstacles.tools.motions.HorizontalMove;
import com.elly.twowalls.tools.Constants;

public class MovingTriangle extends Triangle {

    private float standardSpeed = Constants.WORLD_WIDTH;

    public MovingTriangle(Level level, float y, boolean onRightWall){
        super(level, y, onRightWall);
        setShape(ObstacleType.TRIANGLE);
        addMotion(new HorizontalMove((level.rightBorder - level.leftBorder - level.borderSize + 1) * (onRightWall ? -1 : 1)
                ,standardSpeed),0);
        setColor(1,0,0);
    }

    @Override
    protected MovingTriangle setSprite(){
        sprite = new Sprite(getManager().staticTriangle.getTexture());
        return this;
    }




}
