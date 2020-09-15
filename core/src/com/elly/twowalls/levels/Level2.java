package com.elly.twowalls.levels;

import com.badlogic.gdx.graphics.Color;
import com.elly.twowalls.screens.GameScreen;

public class Level2 extends Level {


    public Level2(GameScreen screen) {
        super(screen, false);
        getPlayer().speed *= .75f;
    }

    @Override
    public void createObstacles() {

        pallete.setStaticTriangleColor(Color.YELLOW);

        creator.spaceSeries(5);
        creator.staticTriangle(true);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(true);

        creator.spaceSeries(6);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(false);
        creator.staticTriangle(false);

        creator.spaceSeries(3);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);

        creator.spaceSeries(6);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);

        creator.spaceSeries(6);
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
    }
}
