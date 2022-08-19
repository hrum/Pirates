package com.shuvzero.pirates.view;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.shuvzero.pirates.R;

public class HelpActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView textView = findViewById(R.id.textView);
        textView.setText(R.string.help_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

}