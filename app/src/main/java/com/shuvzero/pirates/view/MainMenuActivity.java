package com.shuvzero.pirates.view;

import android.os.Bundle;

public class MainMenuActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainMenuView mainMenuView = new MainMenuView(this);
        setContentView(mainMenuView);
    }
}
