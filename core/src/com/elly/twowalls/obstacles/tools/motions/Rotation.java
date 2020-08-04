package com.elly.twowalls.obstacles.tools.motions;

import com.elly.twowalls.obstacles.MovingObstacle;

public class Rotation extends Motion {

    public Rotation(float path, float speed) {
        super(path, speed);
    }

    @Override
    public void make(MovingObstacle obstacle, float dt) {
        path -= speed * direction * dt;
        obstacle.setAngle(obstacle.getAngle() - speed * direction * dt);
        if (checkCondition()){
            obstacle.setAngle(obstacle.getAngle() - extraMove());
            path = 0;
            end();
        }
    }
}
