package com.example.paddy.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.TranslateAnimation;

import java.util.Random;
import java.util.Timer;

/**
 * Created by Paddy on 08.12.16.
 */

public class AsteroidView extends View {
    int width = getContext().getResources().getDisplayMetrics().widthPixels;
    int height = getContext().getResources().getDisplayMetrics().heightPixels;

    private Asteroid asteroid;
    private Context context;
    private float pos[] = new float[2];
    Random rand = new Random();


    public AsteroidView(Context context){
        super (context);
    }
    public AsteroidView(Context context, Asteroid asteroid){
        super (context);
        this.asteroid = asteroid;
        this.context = context;
    }


    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(asteroid.getImage(), width-250 , 0, null);
    }



    public Asteroid getAsteroid(){
        return asteroid;
    }



}


