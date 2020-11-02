package com.elly.twowalls.obstacles;

import com.elly.twowalls.levels.Level;

public abstract class Trigger {

    private Level level;
    private float y;
    private boolean triggered;

    public Trigger(Level level, float y) {
        this.level = level;
        this.y = y;
    }

    public void update(){
        check();
        if (triggered)
            trigger();
    }

    public void check(){
        if (!triggered && level.getPlayer().getPosition().y >= y)
            triggered = true;
    }

    public abstract void trigger();
}
