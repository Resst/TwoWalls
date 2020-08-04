package com.elly.twowalls.obstacles.tools;

import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.squares.Square;
import com.elly.twowalls.obstacles.triangles.MovingTriangle;
import com.elly.twowalls.obstacles.triangles.RotatingTriangle;
import com.elly.twowalls.obstacles.triangles.StaticTriangle;
import com.elly.twowalls.obstacles.triangles.Triangle;
import com.elly.twowalls.tools.Constants;

public class ObstacleCreator {

    public static float standardSpeed = Constants.CELL_SIZE * 6;
    public static float standardTriggerZone = Constants.CELL_SIZE * 5;

    private Level level;


    public ObstacleCreator(Level level) {
        this.level = level;
    }

    public Triangle addTriangle(float y, boolean onRightWall){
        return new Triangle(level, y, onRightWall);
    }

    public Square addSquare(float y, boolean onRightWall){
        return new Square(level, y, onRightWall);
    }

    public StaticTriangle staticTriangle(float y, boolean onRightWall){
        return new StaticTriangle(level, y, onRightWall);
    }

    public MovingTriangle movingTriangle(float y, boolean onRightWall){
        return new MovingTriangle(level, y, onRightWall);
    }

    public RotatingTriangle rotatingTriangle(float y, boolean onRightWall){
        return new RotatingTriangle(level, y, onRightWall);
    }
}
