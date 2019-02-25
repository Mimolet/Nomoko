package com.n.nomoko.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.n.nomoko.*;
import com.n.nomoko.database.dao.CategorieDAO;

import java.io.IOException;

public class BienvenueActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue);
        /*TODO : ajouter un bouton pour passer la vidéo*/
        /*TODO : ajouter un bouton pour couper le son*/

        final SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);
        //Pour démarrer la page d'inscription après la vidéo de bienvenue

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (infoPerso.contains("prenom") && infoPerso.contains("testFini")) {
                    Intent intent = new Intent(BienvenueActivity.this, AccueilActivity.class);
                    startActivity(intent);
                } else if (infoPerso.contains("prenom")) {
                    Intent intent = new Intent(BienvenueActivity.this, TestPersoActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(BienvenueActivity.this, InscriptionActivity.class);
                    startActivity(intent);
                }

            }
        },21000 );

    }

    @Override
    protected void onResume() {

        super.onResume();

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(BienvenueActivity.this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDisplay(surfaceHolder);
        String video = ("android.resource://" + getPackageName() + "/" + R.raw.bienvenuefinale);
        try {
            mediaPlayer.setDataSource(this,Uri.parse(video));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepareAsync();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer player) {
        player.start();

    }
}
