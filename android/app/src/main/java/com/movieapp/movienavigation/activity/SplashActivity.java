package com.movieapp.movienavigation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.movieapp.movienavigation.R;

public class SplashActivity extends AppCompatActivity {

    ImageView image_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image_splash = findViewById(R.id.splash_image);
        Animation splash_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_anim);
        image_splash.startAnimation(splash_anim);

        final Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        Thread splash_timer = new Thread(){
            public void run(){
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        splash_timer.start();
    }
}