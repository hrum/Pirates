package com.shuvzero.pirates.view;

import android.os.Bundle;

import com.shuvzero.pirates.model.Game;

public class GameActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game();
        game.start();
        GameView gameView = new GameView(this, game);
        setContentView(gameView);
    }
}
