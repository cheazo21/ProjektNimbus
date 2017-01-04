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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements SensorEventListener{


    private BackgroundView bgv;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private PlaneView pv;
    private AsteroidView av;

    private float mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //  Timer();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        bgv = new BackgroundView(this);

        FrameLayout fl = new FrameLayout(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        fl.setLayoutParams(lp);

        pv = new PlaneView(this, new Plane(0, 0,BitmapFactory.decodeResource(getResources(), R.drawable.spaceship)), bgv.getBackgroundModel());
        av = new AsteroidView(this, new Asteroid(0 , 0, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_03)));


        fl.addView(bgv);
        fl.addView(pv);
        fl.addView(av);


        setContentView(fl);


   /*     new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            }
        }, 0, 10000);
*/

       av.postDelayed(new Runnable() {
           @Override
           public void run() {
               onStartAnimation();
               av.postDelayed(this, 5000);
           }
       },5000);

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

           // av.getAsteroid().setPosition(pos);
           // av.invalidate();
        }
    }

}
