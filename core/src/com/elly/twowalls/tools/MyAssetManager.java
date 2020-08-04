package com.elly.twowalls.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.elly.twowalls.tools.Assets.BackgroundAssets;
import com.elly.twowalls.tools.Assets.PlayerAssets;
import com.elly.twowalls.tools.Assets.SquareAssets;
import com.elly.twowalls.tools.Assets.TriangleAssets;
import com.elly.twowalls.tools.Assets.UIAssets;

public class MyAssetManager {

    private AssetManager manager;

    public final PlayerAssets player;
    public final BackgroundAssets background;
    public final TriangleAssets staticTriangle;
    public final SquareAssets square;
    public final UIAssets ui;


    public MyAssetManager() {
        manager = new AssetManager();

        background = new BackgroundAssets(manager);
        player = new PlayerAssets(manager);
        staticTriangle = new TriangleAssets(manager);
        square = new SquareAssets(manager);
        ui = new UIAssets(manager);

        //finishing loading
        manager.finishLoading();
    }


    public void dispose() {
        manager.dispose();
    }
}
