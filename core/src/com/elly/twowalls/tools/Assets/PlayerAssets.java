package com.elly.twowalls.tools.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class PlayerAssets extends Assets {

    //Players texture folder
    private static final String PLAYER_FOLDER = "Player/";

    //All Textures
    @Asset(type = Texture.class, folder = TEXTURES_FOLDER + PLAYER_FOLDER)
    public static final String
            BASE_TEXTURE = "PlayerGray.png",
            UFO_TEXTURE = "PlayerUFO.png";

    public PlayerAssets(AssetManager manager) {
        super(manager, "PlayerTexture");
    }

    @Override
    protected String getFolder() {
        return PLAYER_FOLDER;
    }

    @Override
    protected String getBaseTexture() {
        return TEXTURES_FOLDER + getFolder() + BASE_TEXTURE;
    }


}
