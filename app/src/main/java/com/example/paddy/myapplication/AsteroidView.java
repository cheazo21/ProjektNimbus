package com.example.paddy.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;



/**
 * Created by Paddy on 08.12.16.
 */

public class AsteroidView extends View {
    private Asteroid asteroid;
    private Background bg;
    private Context context;


    public AsteroidView(Context context){
        super (context);
    }
    public AsteroidView(Context context, Asteroid asteroid, Background bg){
        super (context);
        this.asteroid = asteroid;
        this.context = context;
        this.bg = bg;
    }


    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(asteroid.getImage(), asteroid.getX(), asteroid.getY() ,null);

    }




    public Asteroid getAsteroid(){
        return asteroid;
    }



}


