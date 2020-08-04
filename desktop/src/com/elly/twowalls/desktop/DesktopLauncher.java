package com.elly.twowalls.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.tools.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)Constants.WORLD_WIDTH / 2;
		config.height = (int)Constants.WORLD_HEIGHT / 2;
		config.x = 0;
		config.y = 0;
		new LwjglApplication(new GameClass(), config);
	}
}
