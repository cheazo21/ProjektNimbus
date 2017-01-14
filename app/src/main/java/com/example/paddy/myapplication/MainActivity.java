package com.example.paddy.myapplication;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;



public class MainActivity extends Activity implements SensorEventListener{


    private BackgroundView bgv;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private PlaneView pv;
    private AsteroidView av;
    private AsteroidView av2;
    private AsteroidView av3;
    private AsteroidView av4;

    private float mScreenWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int heigth = display.getHeight();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        bgv = new BackgroundView(this);

        FrameLayout fl = new FrameLayout(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        fl.setLayoutParams(lp);

        pv = new PlaneView(this, new Plane(0, 0,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship)), bgv.getBackgroundModel());
        av = new AsteroidView(this, new Asteroid(width-200 , 0, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_03)), bgv.getBackgroundModel());
        av2 = new AsteroidView(this, new Asteroid(width-200 , 0, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_03)), bgv.getBackgroundModel());
        av3 = new AsteroidView(this, new Asteroid(width-200 , 0, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_03)), bgv.getBackgroundModel());
        av4 = new AsteroidView(this, new Asteroid(width-200 , 0, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_03)), bgv.getBackgroundModel());

        fl.addView(bgv);
        fl.addView(pv);
        fl.addView(av);
        fl.addView(av2);
        fl.addView(av3);
        fl.addView(av4);
        av.setVisibility(View.INVISIBLE);
        av2.setVisibility(View.INVISIBLE);
        av3.setVisibility(View.INVISIBLE);
        av4.setVisibility(View.INVISIBLE);
        setContentView(fl);
        av.getAsteroid().setY(0);
        av2.getAsteroid().setY(250);
        av3.getAsteroid().setY(600);
        av4.getAsteroid().setY(heigth-250);

        //Timer für die verschiedenen AteroidViews
       av.postDelayed(new Runnable() {
           @Override
           public void run() {
               onStartAnimation();
               av.setVisibility(View.VISIBLE);
               av.postDelayed(this, 5123);
           }
       },5000);

        av2.postDelayed(new Runnable(){
            public void run(){
                onStartAnimationTwo();
                av2.setVisibility(View.VISIBLE);
                av2.postDelayed(this, 7234);
            }
        },7500);

        av3.postDelayed(new Runnable(){
            public void run(){
                onStartAnimationThree();
                av3.setVisibility(View.VISIBLE);
                av3.postDelayed(this, 10000);
            }
        },9998);

        av4.postDelayed(new Runnable(){
            public void run(){
                onStartAnimationFour();
                av4.setVisibility(View.VISIBLE);
                av4.postDelayed(this, 15000);
            }
        },12435);




    }


    public void onStartAnimation(){

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mScreenWidth , -2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            public void onAnimationUpdate(ValueAnimator animation){
                float value = (float) animation.getAnimatedValue();
                av.setTranslationX(value);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
    }
    public void onStartAnimationTwo(){

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mScreenWidth , -2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            public void onAnimationUpdate(ValueAnimator animation){
                float value = (float) animation.getAnimatedValue();
                av2.setTranslationX(value);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
    }
    public void onStartAnimationThree(){

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mScreenWidth , -2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            public void onAnimationUpdate(ValueAnimator animation){
                float value = (float) animation.getAnimatedValue();
                av3.setTranslationX(value);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
    }
    public void onStartAnimationFour(){

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mScreenWidth , -2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            public void onAnimationUpdate(ValueAnimator animation){
                float value = (float) animation.getAnimatedValue();
                av4.setTranslationX(value);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Ruhezustand deaktivieren
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);       //Sensor_Delay_Game verringert die abtastrate des Sensors
        bgv.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        bgv.pause();
    }

    //Errechnet anhand des Kippwinkels, die aktuelle Position des Flugzeugs auf dem Bildschirm
    public float[] getPlanePosition(float y, float z, float maxAngle) {
        float[] coordinates = new float[2];

        coordinates[0] = (pv.getWidth()/2)+((y/maxAngle)*pv.getWidth()/2)-(pv.getPlane().getImage().getWidth()/2);
        coordinates[1] = (pv.getHeight()/2)+((z/maxAngle)*pv.getHeight()/2)-(pv.getPlane().getImage().getHeight()/2);

        return coordinates;
    }



    @Override
    public void onAccuracyChanged(Sensor s, int acc) {
        // TODO Auto-generated method stub

    }


    //Steuerung via Neigungssensoren
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            //invertieren der Steuerung
            float y = - event.values[1];
            //Bildausgangsposition mittig auf dem Bildschirm/leicht schräge haltung
            float z = event.values[2]-45;
            //Optimierung der Steuerung, Kippwinkel
            float maxAngle = 30;

            if (y > maxAngle)
                y = maxAngle;
            if (y < -maxAngle)
                y = -maxAngle;

            if (z > maxAngle)
                z = maxAngle;
            if (z < -maxAngle)
                z = -maxAngle;

            float[] coords= getPlanePosition(y,z,maxAngle);
            //Hintergrund läuft schneller wenn Flugzeug im rechten Bildrand kommt
            //Hintergrund läuft langsamer wenn Flugzeug in linken Bildrand kommt
            int bgBaseSpeed = 12;

            int bgSpeed = (int) (bgBaseSpeed + ((24)*(y/maxAngle)));
            bgv.getBackgroundModel().setSpeed(bgSpeed < 3 ? 3 : bgSpeed);


            pv.getPlane().setPosition(coords);
            pv.invalidate();

        }
    }

}
