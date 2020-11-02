package com.elly.twowalls;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.elly.twowalls.levels.Level1;
import com.elly.twowalls.screens.GameScreen;
import com.elly.twowalls.screens.LevelChooseScreen;
import com.elly.twowalls.tools.MyAssetManager;


public class GameClass extends Game {
	private SpriteBatch batch;
	private MyAssetManager manager;
	private GameScreen gameScreen;
	private LevelChooseScreen levelChooseScreen;
	private Skin skin;

	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("Textures/UI/menu.json"));
		batch = new SpriteBatch();
		manager = new MyAssetManager();
		gameScreen = new GameScreen(this);
		levelChooseScreen = new LevelChooseScreen(this);
		setScreen(levelChooseScreen);
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}

	public SpriteBatch getBatch(){
	    return batch;
    }

    public MyAssetManager getManager(){
	    return manager;
    }

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public LevelChooseScreen getLevelChooseScreen() {
		return levelChooseScreen;
	}

	public Skin getSkin(){
		return skin;
	}
}
