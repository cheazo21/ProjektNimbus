package com.example.paddy.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.TranslateAnimation;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Paddy on 08.12.16.
 */

public class AsteroidView extends View {
    int width = getContext().getResources().getDisplayMetrics().widthPixels;
    int height = getContext().getResources().getDisplayMetrics().heightPixels;
    private Asteroid asteroid;
    private Background bg;
    private Context context;

    Random rand = new Random();
    //jetzt muss man nur noch den punkt an dem y-festgelegt wird ausgetauscht werden
 //   int positionY = rand.nextInt(1-height);




    private float[] pos = new float[2];



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
        //geht nicht !!! translationy hat keine auswirkung :/ 
        canvas.drawBitmap(asteroid.getImage(), asteroid.getX(), asteroid.getY() ,null);

        // canvas.drawBitmap(asteroid.getImage(), width - 400, pos[0], null);
    }




    public Asteroid getAsteroid(){
        return asteroid;
    }



}


