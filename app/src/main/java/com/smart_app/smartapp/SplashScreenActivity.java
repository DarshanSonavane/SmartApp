package com.smart_app.smartapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smart_app.smartapp.language.activity.SelectLanguageActivity;
import com.smart_app.smartapp.login.activity.LoginActivity;
import com.smart_app.smartapp.util.CustomSharedPreference;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 3000;
    String lang = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        lang = (CustomSharedPreference.getString(SplashScreenActivity.this,"language_id"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(lang == null){
                    Intent intent = new Intent(SplashScreenActivity.this,SelectLanguageActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        },SPLASH_SCREEN_TIME_OUT);
    }
}
