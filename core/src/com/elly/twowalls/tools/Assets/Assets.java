package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Assets {

    protected AssetManager manager;
    protected static final String TEXTURES_FOLDER = "Textures/";
    protected Preferences preferences;

    public Assets(AssetManager manager){
        this.manager = manager;

        AssetAnalyser.parse(manager, this);
    }
    protected abstract String getFolder();

    public Texture getTexture(String texture){
        return manager.get(TEXTURES_FOLDER + getFolder() + texture, Texture.class);
    }

}
