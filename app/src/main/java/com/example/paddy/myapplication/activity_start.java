package com.example.paddy.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Denis
 */

public class activity_start extends AppCompatActivity {

    MediaPlayer mySound;
    //zum hochzaehlen des doppelklick bei der soundwiedergabe
    int soundcount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        //MediaPlayaer intialisieren und mit Sounddatei aus res->raw fuellen. ACHTUNG: Datei dateiformat
        //nur Kleinbuchstaben und Zahlen von 0...9 enthalten
        final MediaPlayer playBenderMP = MediaPlayer.create(this, R.raw.futuramaopeningtheme);

        // ImageButton bekannt machen
        ImageButton playMusic = (ImageButton) this.findViewById( R.id.playMusic);

        // OnClickListener setzen, der permanent den button beobachtet
        playMusic.setOnClickListener(new View.OnClickListener(){
            @Override

            //mit "soundcound" wird gezählt wie oft der button betätigt wurde und fuehsrt die
            //entsprechende aktion aus.. play oder pause...
            public void onClick (View v){
                //Mediaplayer wird gestartet
                soundcount ++;
                if (soundcount==1) {

                    playBenderMP.start();
                }
                else if(soundcount == 2){

                    playBenderMP.pause();
                    soundcount=0;
                }

            }
        });
    }

    // Buttom "Los" - vom Startbildschirm zu Levelauswahl
    public void los (View v) {
        Intent intent = new Intent(this, activity_level.class);
        startActivity(intent);
    }


}
