package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.elly.twowalls.tools.ColliderCreator;
import com.elly.twowalls.tools.Constants;

public abstract class Assets {

    protected AssetManager manager;
    protected static final String TEXTURES_FOLDER = "Textures/";
    protected Preferences preferences;
    private String nameSpace;

    public Assets(AssetManager manager, String nameSpace){
        this.manager = manager;
        this.nameSpace = nameSpace;

        AssetAnalyser.parse(manager, this);
        preferences = Gdx.app.getPreferences(Constants.PREFERENCES_NAME);

        if (!preferences.contains(nameSpace)){
            preferences.putString(nameSpace, getBaseTexture());
            preferences.flush();
        }
    }
    protected abstract String getFolder();

    public void setTexture(String relativePath) {
        preferences.putString(nameSpace, TEXTURES_FOLDER + getFolder() + relativePath);
        preferences.flush();
    }

    public void setTexture(String path, boolean fullPath) {
        if (fullPath){
            preferences.putString(nameSpace, path);
            preferences.flush();
        }
        else
            setTexture(path);
    }


    public Texture getTexture() {
        return manager.get(preferences.getString(nameSpace), Texture.class);
    }

    public Texture getTexture(String texture){
        return manager.get(texture, Texture.class);
    }

    protected abstract String getBaseTexture();

}
