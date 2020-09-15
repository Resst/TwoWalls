package com.elly.twowalls.obstacles.tools;

import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

public class Pallete {

    private HashMap<String, Color> colors;

    public Pallete(){
        colors = new HashMap<>();
    }


    public void setColor(String key, Color color){
        colors.put(key, color);
    }

    public Color getColor(String key){
        return colors.get(key);
    }

    public void setStaticTriangleColor(Color color){
        setColor("staticTriangle", color);
    }

    public Color getStaticTriangleColor(){
        return getColor("staticTriangle");
    }

    public void setRotatingTriangleColor(Color color){
        setColor("rotatingTriangle", color);
    }

    public Color getRotatingTriangleColor(){
        return getColor("rotatingTriangle");
    }

    public void setMovingTriangleColor(Color color){
        setColor("movingTriangle", color);
    }

    public Color getMovingTriangleColor(){
        return getColor("movingTriangle");
    }

}
