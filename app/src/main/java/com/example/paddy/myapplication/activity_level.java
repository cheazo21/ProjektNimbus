package com.example.paddy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Denis
 */

public class activity_level extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    //Button "level01" zu level01
    public void level01 (View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Button BackToStart - activity_start
    public void BackToStart (View v){
        Intent intent = new Intent(this, activity_start.class);
        startActivity(intent);
    }


    //Button oben RECHTS - transparenter Hintergrund und kein Text
    public void FakeInvisible42popUp (View v) {
        Toast toast = Toast.makeText(this, " 42 ", Toast.LENGTH_SHORT);
        toast.show();
    }

    }
