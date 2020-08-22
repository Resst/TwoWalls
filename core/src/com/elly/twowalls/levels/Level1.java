package com.elly.twowalls.levels;

import com.badlogic.gdx.graphics.Color;
import com.elly.twowalls.obstacles.tools.ObstacleShape;
import com.elly.twowalls.obstacles.tools.motions.Rotation;
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
        creator.space(Constants.WORLD_HEIGHT);
        for (int i = 0; i < 1000; i++) {
//            switch (rand.nextInt(6)) {
//                case 0:
//                    creator.rotatingTriangle(rand.nextBoolean());
//                    break;
//                case 1:
//                    creator.movingTriangle(rand.nextBoolean());
//                    break;
//                case 2:
//                    creator.addTriangle(rand.nextBoolean()).setShape(ObstacleShape.CIRCLE);
//                    break;
//                case 3:
//                case 4:
//                case 5:
//                    presets.playerLikeSquare(rand.nextBoolean()).setColor(0,1,0);
//
//            }

            creator.addCircle(rand.nextBoolean()).scaleSize(2.3f)
                    .setOriginPercents(.5f, .5f)
                    .addMotion(new Rotation(1, -180))
                    .setTriggerRange(Constants.WORLD_HEIGHT * 2)
                    .setColor(0,0,1);

            creator.space();
            creator.space();
        }
    }
}
