package com.shuvzero.pirates.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.shuvzero.pirates.R;

public class SplashActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int splashDisplayLength = 1500;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        }, splashDisplayLength);
    }

}
