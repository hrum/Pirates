package com.shuvzero.pirates.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.shuvzero.pirates.R;

public class MainMenuView extends GeneralView {

    public static final int BUTTON_WIDTH = Math.round(0.40f * SCREEN_WIDTH);
    public static final int BUTTON_HEIGHT = Math.round(0.1f * SCREEN_WIDTH);
    public static final int FONT_COLOR = 0xFFF2E3A7;

    public static final int PLAY = 0;
    private static final float MARGIN_RATIO = 0.68f;
    private static final float TEXT_SIZE_RATIO = 0.50f;

    private int menuButtonYSpace;
    private int xMenuStart, yMenuStart;
    private Paint textPaint;

    public MainMenuView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initValues();
        drawBackground(canvas);
        drawMenuItem(canvas, PLAY, getContext().getString(R.string.play));
    }

    private void drawBackground(Canvas canvas) {
        Drawable field = getDrawable(R.drawable.main_menu);
        field.setBounds(0, 0, getWidth(), getHeight());
        field.draw(canvas);
    }

    private void initValues() {
        textPaint = new Paint();
        textPaint.setColor(FONT_COLOR);
        menuButtonYSpace = Math.round(BUTTON_HEIGHT * 1.4f);
        xMenuStart = Math.round(0.5f * (getWidth() - BUTTON_WIDTH));
        yMenuStart =  Math.round(0.5f * (getHeight() - BUTTON_HEIGHT));
    }

    private void drawMenuItem(Canvas canvas, int index, String text) {
        Drawable button = getDrawable(R.drawable.button);
        button.setBounds(xMenuStart, yMenuStart + menuButtonYSpace * index,
                xMenuStart + BUTTON_WIDTH, yMenuStart + BUTTON_HEIGHT + menuButtonYSpace * index);
        button.draw(canvas);
        drawText(canvas, button, text);
    }

    private void drawText(Canvas canvas, Drawable button, String text) {
        textPaint.setTextSize(TEXT_SIZE_RATIO * button.getBounds().height());
        float textWidth = textPaint.measureText(text);
        float x = button.getBounds().exactCenterX() - textWidth/2;
        float y = button.getBounds().top + MARGIN_RATIO * button.getBounds().height();
        canvas.drawText(text, x, y, textPaint);
    }

    private boolean isMenuItemClicked(int index, float x, float y) {
        if(x > xMenuStart && x < xMenuStart + BUTTON_WIDTH
                && y > yMenuStart + menuButtonYSpace * index
                && y < yMenuStart + menuButtonYSpace * index + BUTTON_HEIGHT)
            return true;
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if(isMenuItemClicked(PLAY, x, y)) {
                    Intent intent = new Intent(getContext(), GameActivity.class);
                    getContext().startActivity(intent);
                }
        }
        return true;
    }
}
