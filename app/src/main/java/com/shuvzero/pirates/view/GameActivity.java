package com.shuvzero.pirates.view;

import android.os.Bundle;

public class GameActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView gameView = new GameView(getBaseContext());
        setContentView(gameView);
    }
}
