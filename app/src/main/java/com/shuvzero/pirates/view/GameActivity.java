package com.shuvzero.pirates.view;

import android.os.Bundle;

public class GameActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int difficulty = getIntent().getIntExtra("Difficulty", 0);
        GameView gameView = new GameView(this, difficulty);
        setContentView(gameView);
    }

}
