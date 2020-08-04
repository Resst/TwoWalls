package com.elly.twowalls.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class DrawQueue extends Array<Array<Drawable>> implements Drawable{

    private int maxLayer = -1;

    public void add(Drawable object, int layer){
        while (layer > maxLayer){
            super.add(new Array<Drawable>());
            maxLayer++;
        }
        this.get(layer).add(object);
    }

    public void draw(SpriteBatch batch){

        for (int i = this.maxLayer; i >= 0; i--){
            for (Drawable o : this.get(i))
                o.draw(batch);
        }

        if (!isEmpty() && get(maxLayer).isEmpty()){
            this.removeIndex(maxLayer);
            maxLayer--;
        }
    }
    @Override
    public void clear(){
        super.clear();
        maxLayer = -1;
    }

}
