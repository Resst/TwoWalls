package com.elly.twowalls.obstacles.tools;

import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.MovingObstacle;
import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.obstacles.tools.ObstacleCreator;
import com.elly.twowalls.obstacles.tools.ObstacleShape;
import com.elly.twowalls.obstacles.tools.motions.HorizontalMove;
import com.elly.twowalls.obstacles.tools.motions.Rotation;
import com.elly.twowalls.obstacles.tools.motions.VerticalMove;
import com.elly.twowalls.tools.Constants;

public class Presets {

    private Level level;
    private ObstacleCreator creator;

    public Presets(Level level){
        this.level = level;
        creator = level.getCreator();
    }

    public MovingObstacle[] rhombus(boolean onRightWall){

        MovingObstacle[] res = new MovingObstacle[2];

        res[0] = creator.addTriangle(onRightWall)
                .setColor(level.getPallete().getMovingTriangleColor())
                .addMotion(new HorizontalMove((level.getWidth()) * (onRightWall ? -1 : 1)
                                ,Constants.CELL_SIZE * 6),0)
                .addMotion(new Rotation(onRightWall ? -180 : 180, 230), 1)
                .setOriginPercents(0, 0.5f);


        creator.space(-Obstacle.standardSize);

        res[1] = creator.addTriangle(onRightWall)
                .setColor(level.getPallete().getMovingTriangleColor())
                .addMotion(new HorizontalMove((level.getWidth()) * (onRightWall ? -1 : 1)
                        ,Constants.CELL_SIZE * 6),0)
                .setAngle(180)
                .setOriginPercents(0, 0.5f)
                .addMotion(new Rotation(onRightWall ? -180 : 180, 230),1);
        return res;
    }

    public MovingObstacle stalker(boolean onRightWall){
        return creator
                .addTriangle(onRightWall)
                .setOriginPercents(0, 1)
                .addMotion(new HorizontalMove((level.gameField - Obstacle.standardSize) * (onRightWall ? -1:1), 6000), 0)
                .addMotion(new Rotation(onRightWall ? 90 : -90, 500), 0)
                .addMotion(new VerticalMove(Constants.WORLD_HEIGHT, 3000), 0)
                .setTriggerRange(-Constants.CELL_SIZE / 2)
                .setColor(0,1,0,1);
    }

    public Obstacle ghost(boolean onRightWall){
        return creator.staticTriangle(onRightWall)
                .setColor(1,1,1,.5f)
                .setShape(ObstacleShape.NONE);
    }

    public MovingObstacle playerLikeSquare(boolean onRightWall){
        return creator.addSquare(onRightWall)
                .addMotion(new HorizontalMove((level.gameField - Obstacle.standardSize) * (onRightWall ? -1:1),level.getPlayer().changeWallSpeed),0)
                .setOriginPercents(.5f,.5f)
                .addMotion(new Rotation(onRightWall ? -360 : 360, level.getPlayer().changeWallSpeed / (level.gameField - Obstacle.standardSize) * 360),1);
    }




}
