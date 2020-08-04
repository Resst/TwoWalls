package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class SquareAssets extends Assets{
    //Obstacles texture folder
    private static final String OBSTACLE_FOLDER = "Obstacles/";



    //Current Texture
    private String curStaticTriangleTexture = TEXTURES_FOLDER + OBSTACLE_FOLDER + BASE_TEXTURE;
    //All Textures
    @Asset(type = Texture.class, folder = TEXTURES_FOLDER + OBSTACLE_FOLDER)
    public static final String BASE_TEXTURE = "square.png";

    public SquareAssets(AssetManager manager) {
        super(manager, "squareTexture");
    }

    @Override
    protected String getFolder() {
        return OBSTACLE_FOLDER;
    }

    @Override
    protected String getBaseTexture() {
        return TEXTURES_FOLDER + getFolder() + BASE_TEXTURE;
    }
}
