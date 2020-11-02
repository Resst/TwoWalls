package com.elly.twowalls.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elly.twowalls.GameClass;
import com.elly.twowalls.levels.Level;
import com.elly.twowalls.levels.Level1;
import com.elly.twowalls.levels.Level2;
import com.elly.twowalls.levels.Level3;
import com.elly.twowalls.levels.Level4;
import com.elly.twowalls.levels.Level5;
import com.elly.twowalls.levels.Level6;
import com.elly.twowalls.tools.Assets.BackgroundAssets;
import com.elly.twowalls.tools.Constants;

import javax.xml.soap.Text;

public class LevelChooseScreen implements Screen {

    private GameClass game;
    private Stage stage;
    private Viewport viewport;
    private Grid grid;
    private Viewport bgViewport;

    public LevelChooseScreen(final GameClass game) {
        this.game = game;
        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        bgViewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, new OrthographicCamera());

        grid = new Grid();

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().setProjectionMatrix(bgViewport.getCamera().combined);
        stage.getBatch().begin();
        stage.getBatch().draw(game.getManager().background.getTexture(BackgroundAssets.FOREST_TEXTURE),
                bgViewport.getScreenX() - bgViewport.getWorldWidth() / 2, bgViewport.getScreenY() - bgViewport.getWorldHeight() / 2, bgViewport.getWorldWidth(), bgViewport.getWorldHeight());
        stage.getBatch().end();

        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        grid.drawConnections(stage.getBatch());

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        bgViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private class Grid {
        private Button[] buttons;
        private Button buttonBack, buttonForward;
        private String[] states;
        private Table table;
        private Skin skin;
        private Array<Sprite> connections;

        public Grid() {
            initButtons();
        }

        private void initButtons() {
            createButtonTable();
            createListeners();

            stage.addActor(table);
//            table.debug();
            table.setPosition(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);

            buttonBack = new Button(skin, "back");
            buttonBack.setTransform(true);
            buttonBack.setSize(Constants.CELL_SIZE * 1.3f, Constants.CELL_SIZE * 3 / 4);
            buttonBack.setPosition(0, viewport.getWorldHeight(), Align.topLeft);
            buttonBack.setTransform(false);
            stage.addActor(buttonBack);

            stage.act();
            stage.draw();

            buttonForward = new Button(skin, "backLocked");
            buttonForward.setTransform(true);
            buttonForward.setPosition(getOriginButtonCenterPosition(7).x + buttonForward.getWidth(),
                    getOriginButtonCenterPosition(7).y - Constants.CELL_SIZE * 1.5f + buttonForward.getHeight(),
                    Align.center);
            buttonForward.rotateBy(180);
            stage.addActor(buttonForward);

            //updating stage
            stage.act();
            stage.draw();

            createConnections();

        }

        private void createButtonTable() {
            int levelsSize = 9;

            table = new Table();
            buttons = new Button[levelsSize];
            states = new String[levelsSize];
            skin = game.getSkin();

            //TODO create from save file
            states[0] = "completed";
            states[1] = "completed";
            states[2] = "unlocked";
            states[3] = "completed";
            states[4] = "locked";
            states[5] = "locked";
            states[6] = "unlocked";
            states[7] = "locked";
            states[8] = "locked";

            for (int i = 0; i < levelsSize; i++) {
                buttons[i] = new Button(skin, states[i]);
                table.add(buttons[i]).width(Constants.CELL_SIZE).height(Constants.CELL_SIZE).pad(Constants.CELL_SIZE * .25f);
                if (i % 3 == 2)
                    table.row();
            }
        }

        private void createListeners() {
            addLevelButtonListener(0, Level1.class);
            addLevelButtonListener(1, Level2.class);
            addLevelButtonListener(2, Level3.class);
            addLevelButtonListener(3, Level4.class);
            addLevelButtonListener(4, Level5.class);
            addLevelButtonListener(5, Level6.class);
        }

        private void addLevelButtonListener(int i, final Class<? extends Level> level) {
            buttons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        game.setScreen(game.getGameScreen().setLevel(level.getConstructor(GameScreen.class).newInstance(game.getGameScreen())));
                    } catch (Exception ignored) {}
                }
            });
        }

        public Vector2 getOriginButtonCenterPosition(int i) {
            return new Vector2(buttons[i].getX() + table.getX() + buttons[i].getWidth() / 2, buttons[i].getY() + table.getY() + buttons[i].getHeight() / 2);
        }

        public void drawConnections(Batch batch) {
            batch.begin();
            for (Sprite s : connections) {
                s.draw(batch);
            }
            batch.end();
        }

        private void createConnections() {
            connections = new Array<>();
            createConnection(1, 0, 2, 4);
            createConnection(0, 3);
            createConnection(2, 5);
            createConnection(3, 4, 6);
            createConnection(5, 4, 8);
            createConnection(4, 7);
            createConnection(6, 7);
            createConnection(8, 7);

            Sprite s = new Sprite(skin.getSprite(states[7] + "Connection"));
            s.setSize(Constants.CELL_SIZE / 7, s.getHeight());
            Vector2 pos = getOriginButtonCenterPosition(7);
            s.setPosition(pos.x - s.getWidth() / 2, pos.y);
            s.setSize(s.getWidth(), buttonForward.getY() - buttonForward.getHeight() - getOriginButtonCenterPosition(7).y);
            connections.add(s);

            if (states[7].equals("completed"))
                buttonForward.setStyle(skin.get("back", Button.ButtonStyle.class));

        }

        private void createConnection(int source, int... destination) {
            for (int i : destination) {
                Sprite s = new Sprite(skin.getSprite(states[source] + "Connection"));
                s.setSize(Constants.CELL_SIZE / 7, s.getHeight());
                Vector2 pos = getOriginButtonCenterPosition(source);
                s.setPosition(pos.x - s.getWidth() / 2, pos.y);
                Vector2 size = (getOriginButtonCenterPosition(source).sub(getOriginButtonCenterPosition(i)));

                if (size.x == 0) {
                    s.setSize(s.getWidth(), -size.y);
                } else if (size.y == 0) {
                    s.setSize(s.getWidth(), size.x);
                    s.setOrigin(s.getWidth() / 2, 0);
                    s.rotate(90);
                }
                connections.add(s);
            }
        }
    }

}
