package com.elly.twowalls.levels;

import com.badlogic.gdx.graphics.Color;
import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.screens.GameScreen;

public class Level3 extends Level {
    public Level3(GameScreen screen) {
        super(screen, false);
        getPlayer().speed *= .75f;
    }

    @Override
    public void createObstacles() {
        pallete.setStaticTriangleColor(Color.YELLOW);

        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(true);
        creator.staticTriangle(true);

        creator.spaceSeries(3);

        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);

        creator.space();

        creator.staticTriangle(true).translate(-Obstacle.standardSize * 2.5f, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(Obstacle.standardSize * 2.5f, 0);
        creator.staticTriangle(true).translate(-Obstacle.standardSize * 2.5f, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(Obstacle.standardSize * 2.5f, 0);
        creator.staticTriangle(true).translate(-Obstacle.standardSize * 2.5f, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(Obstacle.standardSize * 2.5f, 0);

        creator.space();


        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);

        creator.spaceSeries(5);

        creator.staticTriangle(true);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);

        creator.spaceSeries(2);

        creator.staticTriangle(false);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);

        creator.spaceSeries(2);

        creator.staticTriangle(true);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);

        creator.spaceSeries(2);

        creator.staticTriangle(false);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);

        creator.spaceSeries(2);

        creator.staticTriangle(true);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space(-Obstacle.standardSize / 2);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize / 2);
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
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(false);

    }
}
