package com.elly.twowalls.screens.UI;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.elly.twowalls.GameClass;

public class ScreenChooseImage extends ClickableImage {

    protected Screen screen;
    protected GameClass game;

    public ScreenChooseImage(Texture texture, Screen screen, GameClass game) {
        super(texture);
        this.screen = screen;
        this.game = game;
    }

    @Override
    public void onClick() {
        game.setScreen(screen);
    }
}
