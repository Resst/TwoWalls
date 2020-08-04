package com.elly.twowalls.obstacles.tools.motions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.elly.twowalls.obstacles.MovingObstacle;

public class Animate extends Motion {

    Animation animation;

    public Animate(Animation animation) {
        super(0, animation.getAnimationDuration());
        this.animation = animation;
    }

    @Override
    public void make(MovingObstacle obstacle, float dt) {
        path += speed * dt;
        obstacle.setSprite((Texture) animation.getKeyFrame(path));

        if (animation.isAnimationFinished(path)){
            end();
        }
    }
}
