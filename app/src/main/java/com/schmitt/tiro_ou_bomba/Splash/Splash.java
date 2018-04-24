package com.schmitt.tiro_ou_bomba.Splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.schmitt.tiro_ou_bomba.Menu;
import com.schmitt.tiro_ou_bomba.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startAnimation();

    }

    public void startAnimation(){
        new CountDownTimer(3000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(Splash.this, Menu.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }.start();
    }
}
