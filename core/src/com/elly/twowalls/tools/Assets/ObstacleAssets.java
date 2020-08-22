package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ObstacleAssets extends Assets {


    //Obstacles texture folder
    private static final String FOLDER = "Obstacles/";


    @Asset(type = Texture.class, folder = TEXTURES_FOLDER + FOLDER)
    public static final String
            SQUARE = "square.png",
            TRIANGLE = "RoundedTriangle.png",
            CIRCLE = "gear.png";


    public ObstacleAssets(AssetManager manager) {
        super(manager);
    }

    @Override
    protected String getFolder() {
        return FOLDER;
    }

}
