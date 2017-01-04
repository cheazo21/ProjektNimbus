package com.example.paddy.myapplication;

import android.graphics.Bitmap;

import java.util.Random;

public class Asteroid {

    private float x,y;
    private float speed = -12;
    private Bitmap img;



    public Asteroid(float x, float y, Bitmap img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public Bitmap getImage() {
        return img;
    }


   public void update() {

        x += speed;

        if (x <= -img.getWidth()) {
            x = 100;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
