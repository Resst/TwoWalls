package com.elly.twowalls.obstacles.tools.motions;

import com.elly.twowalls.obstacles.MovingObstacle;

public class HorizontalMove extends Motion {


    public HorizontalMove(float path, float speed) {
        super(path, speed);
    }

    @Override
    public void make(MovingObstacle obstacle, float dt) {
        path -= speed * direction * dt;
        obstacle.getPosition().x += speed * direction * dt;
        if (checkCondition()){
            obstacle.getPosition().x += extraMove();
            path = 0;
            end();
        }
    }
}
