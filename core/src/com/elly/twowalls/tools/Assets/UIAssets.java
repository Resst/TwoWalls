package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.StringJoiner;

public class UIAssets {

    protected AssetManager manager;
    protected static final String UI_FOLDER = "Textures/UI/";
    @Asset(type = Texture.class, folder = UI_FOLDER)
    public static final String
            ARROW = "arrow.png",
            ARROW_BACK = "arrow-back.png";


    public UIAssets(AssetManager manager){
        this.manager = manager;
        AssetAnalyser.parse(manager, this);
    }

    public Texture arrow(){
        return manager.get(getFolder() + ARROW);
    }

    public Texture arrowBack(){
        return manager.get(getFolder() + ARROW_BACK);
    }

    protected String getFolder(){
        return UI_FOLDER;
    }
}
