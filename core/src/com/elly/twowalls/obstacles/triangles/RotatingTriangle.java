package com.elly.twowalls.obstacles.triangles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.tools.motions.Rotation;

public class RotatingTriangle extends Triangle {

    public final float standardRotation = 116.9f;
    public final float standardRotationSpeed = 250;

    public RotatingTriangle(Level level, float y, boolean onRightWall){
        super(level, y, onRightWall);
        addMotion(new Rotation(onRightWall ? -standardRotation : standardRotation, standardRotationSpeed), 0);
        setColor(1,0,1);
    }

    @Override
    protected RotatingTriangle setSprite() {
        sprite = new Sprite(getManager().staticTriangle.getTexture());
        return this;
    }

}
