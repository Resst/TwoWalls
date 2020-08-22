package com.elly.twowalls.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.elly.twowalls.tools.Assets.BackgroundAssets;
import com.elly.twowalls.tools.Assets.ObstacleAssets;
import com.elly.twowalls.tools.Assets.PlayerAssets;

public class MyAssetManager {

    private AssetManager manager;

    public final PlayerAssets player;
    public final BackgroundAssets background;
    public final ObstacleAssets obstacles;

    public MyAssetManager() {
        manager = new AssetManager();

        background = new BackgroundAssets(manager);
        player = new PlayerAssets(manager);
        obstacles = new ObstacleAssets(manager);

        //finishing loading
        manager.finishLoading();
    }


    public void dispose() {
        manager.dispose();
    }
}
