package com.elly.twowalls.obstacles.tools;

import com.badlogic.gdx.math.Vector2;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.obstacles.MovingObstacle;
import com.elly.twowalls.obstacles.Obstacle;
import com.elly.twowalls.obstacles.tools.motions.HorizontalMove;
import com.elly.twowalls.obstacles.tools.motions.Rotation;
import com.elly.twowalls.tools.Assets.ObstacleAssets;
import com.elly.twowalls.tools.Constants;

public class ObstacleCreator {

    public static float standardSize = Constants.CELL_SIZE;
    public static float standardTriggerZone = Constants.CELL_SIZE * 5;

    public static float standardSpeed = Constants.CELL_SIZE * 6;

    public final float standardRotation = 116.9f;
    public final float standardRotationSpeed = 250;

    private ObstacleAssets assets;

    private float lastY = Constants.WORLD_HEIGHT * 2;

    private Level level;

    public ObstacleCreator(Level level) {
        this.level = level;
        this.assets = level.getScreen().getGame().getManager().obstacles;


    }

    public MovingObstacle addCircle(float y, boolean onRightWall){
        MovingObstacle circle = new MovingObstacle(level, new Vector2(onRightWall ? level.rightBorder : level.leftBorder + level.borderSize, y),
                new Vector2(onRightWall ? -standardSize : standardSize, standardSize), ObstacleShape.CIRCLE)
                .setSprite(assets.getTexture(ObstacleAssets.CIRCLE));
        initObstacle(circle);
        return circle;
    }

    public MovingObstacle addCircle(boolean onRightWall){
        return addCircle(lastY, onRightWall);
    }



    public MovingObstacle addSquare(float y, boolean onRightWall){
        MovingObstacle square = new MovingObstacle(level, new Vector2(onRightWall ? level.rightBorder : level.leftBorder + level.borderSize, y),
                new Vector2(onRightWall ? -standardSize : standardSize, standardSize), ObstacleShape.SQUARE)
                .setSprite(assets.getTexture(ObstacleAssets.SQUARE));//TODO remake this
        initObstacle(square);
        return square;
    }

    public MovingObstacle addSquare(boolean onRightWall) {
        return addSquare(lastY, onRightWall);
    }



    public MovingObstacle addTriangle(float y, boolean onRightWall){
        MovingObstacle triangle = new MovingObstacle(level, new Vector2(onRightWall ? level.rightBorder : level.leftBorder + level.borderSize, y),
                new Vector2(onRightWall ? -standardSize : standardSize, standardSize), ObstacleShape.TRIANGLE)
                .setSprite(assets.getTexture(ObstacleAssets.TRIANGLE));//TODO remake this
        initObstacle(triangle);
        return triangle;
    }

    public MovingObstacle addTriangle(boolean onRightWall) {
        return addTriangle(lastY, onRightWall);
    }

    public Obstacle staticTriangle(float y, boolean onRightWall){
        Obstacle triangle = new Obstacle(
                level, new Vector2(onRightWall ? level.rightBorder : level.leftBorder + level.borderSize, y),
                        new Vector2(onRightWall ? -standardSize : standardSize, standardSize), ObstacleShape.TRIANGLE
        ).setSprite(assets.getTexture(ObstacleAssets.TRIANGLE));//TODO remake this
        initObstacle(triangle);
        return triangle;
    }

    public Obstacle staticTriangle(boolean onRightWall) {
        return staticTriangle(lastY, onRightWall);
    }

    public MovingObstacle movingTriangle(float y, boolean onRightWall){
        return addTriangle(y, onRightWall).
                addMotion(new HorizontalMove((level.rightBorder - level.leftBorder - level.borderSize - standardSize) * (onRightWall ? -1 : 1)
                        ,standardSpeed),0);
    }

    public MovingObstacle movingTriangle(boolean onRightWall) {
        return movingTriangle(lastY, onRightWall);
    }

    public MovingObstacle rotatingTriangle(float y, boolean onRightWall){
        return addTriangle(y, onRightWall).
                addMotion(new Rotation(onRightWall ? -standardRotation : standardRotation, standardRotationSpeed), 0);
    }

    public MovingObstacle rotatingTriangle(boolean onRightWall) {
        return rotatingTriangle(lastY, onRightWall);
    }

    public void space(float spaceSize){
        lastY += spaceSize;
    }

    public void space(){
        space(Constants.CELL_SIZE);
    }

    public void reset(){
        lastY = Constants.WORLD_HEIGHT * 2;
    }

    private void initObstacle(Obstacle obstacle){
        level.addObstacle(obstacle);
        space(obstacle.getSize().y);
    }

}
