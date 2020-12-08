package com.example.taskday8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView bgSplash, logoSplash, headingSplash;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        findViewById();
        myAnimation();
        thread.start();
    }

    void findViewById() {
        bgSplash = findViewById(R.id.bgsplash);
        logoSplash = findViewById(R.id.logosplash);
        lottieAnimationView = findViewById(R.id.lottie);
    }

    void myAnimation() {
        bgSplash.animate().translationY(-2600).setDuration(1000).setStartDelay(4000);
        logoSplash.animate().translationY(2600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-2600).setDuration(1000).setStartDelay(4000);
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                sleep(4000);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                SplashScreenActivity.this.startActivity(intent);
                finish();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };

}