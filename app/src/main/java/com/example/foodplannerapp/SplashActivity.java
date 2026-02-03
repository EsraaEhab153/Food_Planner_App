package com.example.foodplannerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodplannerapp.ui.auth.AuthActivity;


public class SplashActivity extends AppCompatActivity {
  private Handler handler = new Handler();
  private Runnable runnable;
  SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OnBoarding.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(()->{
            onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
            boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);
            if(isFirstTime){
                SharedPreferences.Editor editor = onBoardingScreen.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
                runnable.run();
            }
            else{
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }


        }, 3000);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}