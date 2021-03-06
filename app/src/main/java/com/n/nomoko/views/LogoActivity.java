package com.n.nomoko.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import com.n.nomoko.*;
import com.n.nomoko.database.dao.CategorieDAO;

public class LogoActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        /*Création de la base de données*/

        CategorieDAO categorieDAO = new CategorieDAO(this);
        categorieDAO.open();
        categorieDAO.close();

        final SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);

        //Pour démarrer automatiquement cette activité après celle du logo
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (infoPerso.contains("testFini")) {
                    intent = new Intent(LogoActivity.this, AccueilActivity.class);
                } else {
                    intent = new Intent(LogoActivity.this, BienvenueActivity.class);
                }
                startActivity(intent);
            }
        }, 6000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView2);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(LogoActivity.this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDisplay(surfaceHolder);
        String video = ("android.resource://" + getPackageName() + "/" + R.raw.animationfinale);
        try {
            mediaPlayer.setDataSource(this, Uri.parse(video));
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
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
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