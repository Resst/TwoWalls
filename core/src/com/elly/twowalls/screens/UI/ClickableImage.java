package com.elly.twowalls.screens.UI;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class ClickableImage extends Button {

    public boolean active = true;
    private Sprite sprite;

    public ClickableImage(Texture texture) {
        super(new NotDrawable());
        sprite = new Sprite(texture);
        init();
    }

    public abstract void onClick();

    private void init() {
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (active)
                    onClick();
            }
        });
    }

    @Override
    public void act(float dt){
        super.act(dt);
        sprite.setPosition(getX(), getY());
        sprite.setSize(getWidth(),getHeight());
        sprite.setRotation(getRotation());
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        sprite.draw(batch);
    }

    public Sprite getSprite(){
        return sprite;
    }





}
