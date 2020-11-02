package com.elly.twowalls.levels;

import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.obstacles.tools.motions.HorizontalMove;
import com.elly.twowalls.screens.GameScreen;

public class Level5 extends Level {
    public Level5(GameScreen screen) {
        super(screen,false);
        getPlayer().speed *= .75f;
    }

    @Override
    public void createObstacles() {
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.space();
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.spaceSeries(5);
        creator.movingTriangle(true);
        creator.spaceSeries(3);
        creator.movingTriangle(false);
        creator.spaceSeries(3);
        creator.movingTriangle(true);
        creator.spaceSeries(3);
        creator.movingTriangle(false);

        creator.spaceSeries(2);
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.spaceSeries(2);
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.spaceSeries(2);
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.spaceSeries(2);
        creator.movingTriangle(false);
        creator.movingTriangle(false);

        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.movingTriangle(false);

        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.staticTriangle(false);
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.movingTriangle(true);

        stTWall();
        stTWall();
        stTWall();
        stTWall();
        stTWall();

        mvTWall();
        mvTWall();
        mvTWall();
        mvTWall();
        mvTWall();

        creator.spaceSeries(3);
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();

        creator.spaceSeries(2);
        creator.movingTriangle(true);
        creator.spaceSeries(2);
        creator.movingTriangle(false);
        creator.spaceSeries(2);
        creator.movingTriangle(true);
        creator.spaceSeries(2);
        creator.movingTriangle(false);
        creator.spaceSeries(2);
        creator.movingTriangle(true);
        creator.spaceSeries(2);
        creator.movingTriangle(false);
        creator.spaceSeries(2);
        creator.movingTriangle(true);
        creator.spaceSeries(2);
        creator.movingTriangle(false);

        creator.space();
        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.space();
        creator.movingTriangle(true);
        creator.movingTriangle(true);
        creator.space();
        creator.movingTriangle(false);
        creator.movingTriangle(false);
        creator.space();

        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();
        mvTCenter();

        creator.end();

    }

    //static triangle wall
    void stTWall(){
        creator.staticTriangle(true).translate(Obstacle.standardSize / 2, 0);
        creator.space(-Obstacle.standardSize);
        creator.staticTriangle(false).translate(-Obstacle.standardSize / 2, 0);
    }

    //moving triangle wall
    void mvTWall(){
        creator.addTriangle(true)
                .setColor(pallete.getMovingTriangleColor())
                .addMotion(new HorizontalMove(Obstacle.standardSize / 2, Obstacle.standardSize));
        creator.space(-Obstacle.standardSize);
        creator.addTriangle(false)
                .setColor(pallete.getMovingTriangleColor())
                .addMotion(new HorizontalMove(-Obstacle.standardSize / 2, Obstacle.standardSize));
    }
    //moving triangles in the center
    void mvTCenter(){
        creator.addTriangle(true).
                addMotion(new HorizontalMove(-getWidth() / 2, getWidth()))
                .setColor(pallete.getMovingTriangleColor());
        creator.space(-Obstacle.standardSize);
        creator.addTriangle(false).
                addMotion(new HorizontalMove(getWidth() / 2, getWidth()))
                .setColor(pallete.getMovingTriangleColor());
    }

}
