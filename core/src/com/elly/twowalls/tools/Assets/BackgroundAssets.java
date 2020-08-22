package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class BackgroundAssets extends Assets {


    public BackgroundAssets(AssetManager manager) {
        super(manager);
    }


    //Levels texture folder
    private static final String FOLDER = "Level/";

    //All textures
    @Asset(type = Texture.class, folder = TEXTURES_FOLDER + FOLDER)
    public static final String
            BASE_TEXTURE = "PlusesBG.png";


    @Override
    protected String getFolder() {
        return FOLDER;
    }

}
