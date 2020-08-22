package com.elly.twowalls.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elly.twowalls.tools.Constants;
import com.elly.twowalls.tools.Drawable;


public class Hud implements Drawable {

    private Stage stage;
    private Viewport viewport;
    private BitmapFont font;
    private Label scoreLabel;
    private Label bestScoreLabel;
    private GameScreen screen;


    public Hud(GameScreen screen){
        this.screen = screen;
        viewport = new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, screen.getGame().getBatch());
        initFonts();
        initButtons();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        stage.act();
    }

    public void update(float dt) {
        scoreLabel.setText("Score: " + screen.getLevel().getScore());
        bestScoreLabel.setText("Best score: " + screen.getLevel().getBestScore());

        scoreLabel.setPosition(0, Constants.WORLD_HEIGHT - scoreLabel.getHeight());
        bestScoreLabel.setPosition(0, scoreLabel.getY() - bestScoreLabel.getHeight());

    }

    private void initFonts(){

        font = new BitmapFont();
        font.setColor(Color.BLUE);
        font.getData().setScale(2, 2);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        scoreLabel = new Label("Score", labelStyle);
        bestScoreLabel = new Label("Best Score", labelStyle);

        stage.addActor(scoreLabel);
        stage.addActor(bestScoreLabel);
    }

    private void initButtons(){
    }

    public Stage getStage(){
        return stage;
    }

}
