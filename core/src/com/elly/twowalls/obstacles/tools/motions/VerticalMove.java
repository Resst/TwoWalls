package com.elly.twowalls.obstacles.tools.motions;

import com.elly.twowalls.obstacles.MovingObstacle;

public class VerticalMove extends Motion {

    public VerticalMove(float path, float speed) {
        super(path, speed);
    }

    @Override
    public void make(MovingObstacle obstacle, float dt) {
        path -= speed * direction * dt;
        obstacle.getPosition().y += speed * direction * dt;
        if (checkCondition()){
            obstacle.getPosition().y += extraMove();
            path = 0;
            end();
        }
    }
}
