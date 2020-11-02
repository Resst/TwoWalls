package com.elly.twowalls.levels;

import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.screens.GameScreen;

public class Level6 extends Level {
    public Level6(GameScreen screen) {
        super(screen, false);
        getPlayer().speed *= .75f;
    }

    @Override
    public void createObstacles() {
        presets.rhombus(true);
        creator.spaceSeries(2);
        presets.rhombus(false);
        creator.spaceSeries(2);
        presets.rhombus(true);
        creator.spaceSeries(2);
        presets.rhombus(false);
        creator.spaceSeries(2);

        creator.staticTriangle(true);
        creator.space();
        creator.staticTriangle(false);
        creator.space();

        stTAndGhost(true);
        creator.space();
        stTAndGhost(false);
        creator.space();
        stTAndGhost(true);
        creator.space();
        stTAndGhost(true);
        creator.space();
        stTAndGhost(false);
        creator.space();
        stTAndGhost(true);
        creator.space();
        stTAndGhost(false);
        creator.space();
        stTAndGhost(false);
        creator.space();
        stTAndGhost(true);
        creator.space();
        stTAndGhost(true);

        creator.spaceSeries(3);

        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(false);
        stTAndGhost(false);
        stTAndGhost(false);
        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(false);
        stTAndGhost(false);
        stTAndGhost(false);
        stTAndGhost(false);
        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(true);
        stTAndGhost(false);

        creator.spaceSeries(3);

        presets.rhombus(true);
        creator.spaceSeries(2);
        presets.rhombus(false);
        creator.spaceSeries(2);
        presets.rhombus(true);
        creator.spaceSeries(2);
        presets.rhombus(false);
        creator.spaceSeries(2);
        presets.rhombus(true);

        creator.spaceSeries(5);

        creator.staticTriangle(true).scaleSize(2);
        creator.space();
        creator.staticTriangle(false).scaleSize(2);
        creator.space();
        creator.staticTriangle(true).scaleSize(2);
        creator.space();
        creator.staticTriangle(true).scaleSize(2);
        creator.space();
        creator.staticTriangle(false).scaleSize(2);
        creator.space();
        creator.staticTriangle(false).scaleSize(2);
        creator.space();
        creator.staticTriangle(true).scaleSize(2);
        creator.space();
        creator.staticTriangle(true).scaleSize(2);
        creator.space();
        largeStTAndGhost(true);
        largeStTAndGhost(true);
        largeStTAndGhost(true);
        largeStTAndGhost(false);
        largeStTAndGhost(false);
        largeStTAndGhost(true);
        largeStTAndGhost(false);

        creator.end();



    }

    //static triangle on one wall and ghost on another
    void stTAndGhost(boolean ghostOnRightWall){
        creator.staticTriangle(!ghostOnRightWall);

        creator.space(-Obstacle.standardSize);

        presets.ghost(ghostOnRightWall).setColor(pallete.getStaticTriangleColor()).setAlpha(.4f);
    }

    // scaled x2 of stTAndGhost
    void largeStTAndGhost(boolean ghostOnRightWall){
        creator.staticTriangle(!ghostOnRightWall).scaleSize(2);

        creator.space(-Obstacle.standardSize);

        presets.ghost(ghostOnRightWall).setColor(pallete.getStaticTriangleColor()).setAlpha(.4f).scaleSize(2);
        creator.space();
    }
}
