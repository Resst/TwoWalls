package com.elly.twowalls.screens.UI;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.screens.GameScreen;

import java.lang.reflect.InvocationTargetException;

public class LevelChooseImage extends ScreenChooseImage {

    private Class<? extends Level> level;

    public LevelChooseImage(Texture texture, GameClass game, Class<? extends Level> level) {
        super(texture, game.getGameScreen(), game);
        this.level = level;
    }

    @Override
    public void onClick(){
        super.onClick();
        try {
            game.getGameScreen().setLevel(level.getConstructor(GameScreen.class).newInstance(game.getGameScreen()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
