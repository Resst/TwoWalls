package com.elly.twowalls.levels;

import com.badlogic.gdx.graphics.Color;
import com.elly.twowalls.screens.GameScreen;

public class Level1 extends Level {

    public Level1(GameScreen screen) {
        super(screen, false);
        getPlayer().speed *= .75f;
    }

    @Override
    public void createObstacles() {
        pallete.setStaticTriangleColor(Color.YELLOW);

        creator.staticTriangle(true);

        creator.spaceSeries(2);
        creator.staticTriangle(false);

        creator.spaceSeries(4);
        creator.staticTriangle(false);
        creator.staticTriangle(false);

        creator.spaceSeries(2);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);

        creator.spaceSeries(6);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);

        creator.spaceSeries(2);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(true);

        creator.spaceSeries(3);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.spaceSeries(2);
        creator.staticTriangle(false);


    }
}
