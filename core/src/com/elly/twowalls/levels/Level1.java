package com.elly.twowalls.levels;

import com.elly.twowalls.screens.GameScreen;
import com.elly.twowalls.tools.Constants;

import java.util.Random;

public class Level1 extends Level {


    public Level1(GameScreen screen) {
        super(screen, false);
        getPlayer().changeWallSpeed = Constants.CELL_SIZE * 10;
        getPlayer().speed = Constants.WORLD_HEIGHT * .9f;
    }

    @Override
    public void createObstacles() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            switch (rand.nextInt(3)) {
                case 0:
                    rotatingTriangle(rand.nextBoolean());
                    break;
                case 1:
                    movingTriangle(rand.nextBoolean());
                    break;
                case 2:
                    staticTriangle(rand.nextBoolean());
                    break;
            }
            space();
            space();
        }
    }
}
