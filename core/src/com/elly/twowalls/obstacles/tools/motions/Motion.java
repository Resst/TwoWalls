package com.elly.twowalls.obstacles.tools.motions;

import com.elly.twowalls.obstacles.MovingObstacle;

public abstract class Motion {

    protected float path;
    protected float speed;
    protected int direction;
    protected boolean ended;

    public Motion(float path, float speed){
        this.path = path;
        this.speed = speed;
        direction = (int) Math.signum(path);
    }

    public abstract void make(MovingObstacle obstacle, float dt);

    protected boolean checkCondition(){
        return path * direction < 0;
    }

    protected float extraMove(){
        return path;
    }

    public void end(){
        ended = true;
    }

    public boolean isEnded(){
        return ended;
    }
}
