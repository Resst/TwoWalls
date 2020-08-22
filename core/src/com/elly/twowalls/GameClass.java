package com.elly.twowalls;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elly.twowalls.screens.GameScreen;
import com.elly.twowalls.tools.MyAssetManager;


public class GameClass extends Game {
	private SpriteBatch batch;
	private MyAssetManager manager;
	private GameScreen gameScreen;

	@Override
	public void create () {
	    batch = new SpriteBatch();
	    manager = new MyAssetManager();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
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

}
