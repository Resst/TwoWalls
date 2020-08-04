package com.elly.twowalls;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elly.twowalls.screens.GameScreen;
import com.elly.twowalls.screens.LevelChooseScreen;
import com.elly.twowalls.screens.MainScreen;
import com.elly.twowalls.screens.TextureChooseScreen;
import com.elly.twowalls.tools.MyAssetManager;


public class GameClass extends Game {
	private SpriteBatch batch;
	private MyAssetManager manager;
	private MainScreen mainScreen;
	private GameScreen gameScreen;
	private LevelChooseScreen levelChooseScreen;
	private TextureChooseScreen textureChooseScreen;

	@Override
	public void create () {
	    batch = new SpriteBatch();
	    manager = new MyAssetManager();
		textureChooseScreen = new TextureChooseScreen(this);
		mainScreen = new MainScreen(this);
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

	public MainScreen getMainScreen(){
		return mainScreen;
	}

	public GameScreen getGameScreen(){
		return gameScreen;
	}

	public LevelChooseScreen getLevelChooseScreen(){
		return levelChooseScreen;
	}

	public TextureChooseScreen getTextureChooseScreen(){
		return textureChooseScreen;
	}


	public SpriteBatch getBatch(){
	    return batch;
    }

    public MyAssetManager getManager(){
	    return manager;
    }

}
