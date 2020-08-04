package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class TriangleAssets extends Assets {
    //Obstacles texture folder
    private static final String OBSTACLE_FOLDER = "Obstacles/";

    //Current Texture
    private String curStaticTriangleTexture = TEXTURES_FOLDER + OBSTACLE_FOLDER + BASE_TEXTURE;
    //All Textures
    @Asset(type = Texture.class, folder = TEXTURES_FOLDER + OBSTACLE_FOLDER)
    public static final String BASE_TEXTURE = "triangle.png",
            ROUNDED_TEXTURE = "RoundedTriangle.png";


    public TriangleAssets(AssetManager manager) {
        super(manager, "TriangleAssets");
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
