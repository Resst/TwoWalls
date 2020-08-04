package com.elly.twowalls.levels;

import com.elly.twowalls.obstacles.MovingObstacle;
import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.obstacles.tools.ObstacleType;
import com.elly.twowalls.obstacles.tools.motions.HorizontalMove;
import com.elly.twowalls.obstacles.tools.motions.Rotation;
import com.elly.twowalls.obstacles.tools.motions.VerticalMove;
import com.elly.twowalls.obstacles.triangles.Triangle;
import com.elly.twowalls.tools.Constants;

public class Presets {

    private Level level;

    public Presets(Level level){
        this.level = level;
    }

    public MovingObstacle[] rhombus(boolean onRightWall){

        MovingObstacle[] res = new MovingObstacle[2];

        res[0] = level.movingTriangle(onRightWall)
                .addMotion(new Rotation(-180, 230), 1)
                .setOriginPercents(0, 0.5f);

        level.space(-Obstacle.standardSize);

        MovingObstacle t = level.movingTriangle(onRightWall);
        t
                .setSize(t.getSize().scl(-1f,1f))
                .setOriginPercents(0, 0.5f)
                .addMotion(new Rotation(-180, 230),1);
        res[1] = t;
        return res;
    }

    public MovingObstacle stalker(boolean onRightWall){
        return level
                .addTriangle(onRightWall)
                .setOriginPercents(0, 1)
                .addMotion(new HorizontalMove((level.gameField - Obstacle.standardSize) * (onRightWall ? -1:1), 6000), 0)
                .addMotion(new Rotation(onRightWall ? 90 : -90, 500), 0)
                .addMotion(new VerticalMove(Constants.WORLD_HEIGHT, 3000), 0)
                .setTriggerRange(-Constants.CELL_SIZE / 2)
                .setColor(0,1,0,1);
    }

    public Obstacle ghost(boolean onRightWall){
        return level.staticTriangle(onRightWall)
                .setColor(1,1,1,.5f)
                .setShape(ObstacleType.NONE);
    }

    public MovingObstacle playerLikeSquare(boolean onRightWall){
        return level.addSquare(onRightWall)
                .addMotion(new HorizontalMove((level.gameField - Obstacle.standardSize) * (onRightWall ? -1:1),level.getPlayer().changeWallSpeed),0)
                .setOriginPercents(.5f,.5f)
                .addMotion(new Rotation(onRightWall ? -360 : 360, level.getPlayer().changeWallSpeed / (level.gameField - Obstacle.standardSize) * 360),1);
    }




}
