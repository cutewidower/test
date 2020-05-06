package com.example.donordarah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
     private RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout = findViewById(R.id.layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        final int loading =6000;
        ProgressBar proses = (ProgressBar) findViewById(R.id.progressBar);

        ProgressBarAnimation mProgressAnimationTot = new ProgressBarAnimation(proses, loading);
        mProgressAnimationTot.setProgress(100);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, ItemList.class);
                startActivity(intent);
            }
        },loading);



    }
}
