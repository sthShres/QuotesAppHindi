package com.newnepquotes.shyarihin2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar();
        setContentView(R.layout.splash_screen);

        ImageView logo = findViewById(R.id.logo);
        TextView appBrand = findViewById(R.id.poweredby);

        int splashTimeOut = 1500;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             Intent intent = new Intent(SplashScreen.this,Home_Activity.class);
             startActivity(intent);
             finish();

            }
        }, splashTimeOut);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation);
        logo.startAnimation(animation);
        appBrand.startAnimation(animation);


    }
}