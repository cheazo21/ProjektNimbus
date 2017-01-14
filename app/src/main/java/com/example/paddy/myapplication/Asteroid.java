package com.example.paddy.myapplication;

import android.graphics.Bitmap;

public class Asteroid {

    private float x,y;
    private Bitmap img;
    public float width, height;




    public Asteroid(float x, float y, Bitmap img) {
        this.x = x;
        this.y = y;
        this.img = img;
        width = img.getWidth();
        height = img.getHeight();
    }

    public Bitmap getImage() {
        return img;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
