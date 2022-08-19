package com.shuvzero.pirates.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import com.shuvzero.pirates.R;
import com.shuvzero.pirates.model.Cell;
import com.shuvzero.pirates.model.Game;
import com.shuvzero.pirates.model.Point;

public class GameView extends View {

    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

    private static final int CLOSE_BUTTON_SIZE = SCREEN_WIDTH/10;
    private static final int CLOSE_BUTTON_GAP = CLOSE_BUTTON_SIZE/10;
    private static final int DIG_BUTTON_SIZE = SCREEN_WIDTH/5;
    private float size = SCREEN_WIDTH/18;

    private Game game;
    private MapLayout layout;
    private int selectedPosition;
    private boolean treasureFound;
    private Paint titlePaint;
    private Paint messagePaint;
    private Paint hintPaint;
    private Paint hintSmallPaint;

    private GestureDetectorCompat gestureDetector;

    public GameView(Context context) {
        super(context);
        startNewGame();
        layout = new MapLayout(game.getTreasureMap(), SCREEN_WIDTH, SCREEN_HEIGHT, size);
        selectedPosition = -1;
        createPaint();
        //size = game.getTreasureMap().getHeight()

        gestureDetector = new GestureDetectorCompat(context, new MyGestureListener());
    }

    private void startNewGame() {
        game = new Game();
        game.start();
    }

    private void createPaint() {
        titlePaint = new Paint();
        titlePaint.setTextSize(SCREEN_WIDTH/18);
        titlePaint.setColor(Color.DKGRAY);
        titlePaint.setTypeface(Typeface.DEFAULT_BOLD);

        messagePaint = new Paint();
        messagePaint.setTextSize(SCREEN_WIDTH/27);
        messagePaint.setColor(Color.DKGRAY);

        hintPaint = new Paint();
        hintPaint.setTextSize(SCREEN_WIDTH/7);
        hintPaint.setColor(Color.DKGRAY);
        hintPaint.setTypeface(Typeface.DEFAULT_BOLD);

        hintSmallPaint = new Paint();
        hintSmallPaint.setTextSize(SCREEN_WIDTH/27);
        hintSmallPaint.setColor(Color.DKGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap(canvas);
        drawFrame(canvas);
        drawHints(canvas);
        drawInfoWindow(canvas);
        drawHelpButton(canvas);
        drawTreasure(canvas);
    }

    private void drawMap(Canvas canvas) {
        for(Cell cell: game.getTreasureMap().getCells()) {
            Drawable tile;
            if(cell.isLand())
                tile = getDrawable(R.drawable.land);
            else
                tile = getDrawable(R.drawable.ocean);
            Point p = layout.getPoint(cell.getPosition());
            tile.setBounds(Math.round(p.x()),
                    Math.round(p.y()),
                    Math.round(p.x() + 2 * size),
                    Math.round(p.y() + 2 * size));
            tile.draw(canvas);
            if(cell.getFeature() != null && cell.getFeature().getDrawableId() != 0) {
                Drawable feature = getDrawable(cell.getFeature().getDrawableId());
                feature.setBounds(Math.round(p.x()),
                        Math.round(p.y()),
                        Math.round(p.x() + 2 * size),
                        Math.round(p.y() + 2 * size));
                feature.draw(canvas);
            }

        }
    }

    private void drawHints(Canvas canvas) {
        for(Cell cell: game.getTreasureMap().getCells()) {
            if (cell.getHint() != -1) {
                Point p = layout.getPoint(cell.getPosition());
                Drawable hintBackground = getDrawable(R.drawable.hint_background);
                hintBackground.setBounds(Math.round(p.x()),
                        Math.round(p.y()),
                        Math.round(p.x() + 2 * size),
                        Math.round(p.y() + 2 * size));
                hintBackground.draw(canvas);
                canvas.drawText(String.valueOf(cell.getHint()), p.x() + 0.8f * size, p.y() + 0.7f * size, hintSmallPaint);
            }
        }
    }

    private void drawFrame(Canvas canvas) {
        if(selectedPosition != -1) {
            Drawable frame = getDrawable(R.drawable.frame);
            Point p = layout.getPoint(selectedPosition);
            frame.setBounds(Math.round(p.x()),
                    Math.round(p.y()),
                    Math.round(p.x() + 2 * size),
                    Math.round(p.y() + 2 * size));
            frame.draw(canvas);
        }
    }

    private void drawInfoWindow(Canvas canvas) {
        if(selectedPosition != -1) {
            Drawable window;
            if(!game.getTreasureMap().getCell(selectedPosition).isLand())
                window = getDrawable(R.drawable.info_window);
            else
                window = getDrawable(R.drawable.info_window2);
            window.setBounds(0, getHeight() - SCREEN_WIDTH/2, SCREEN_WIDTH, getHeight());
            window.draw(canvas);
            drawTitle(canvas);
            drawCloseButton(canvas);
            drawDigButton(canvas);
        }
    }

    private void drawTitle(Canvas canvas) {
        Cell cell = game.getTreasureMap().getCell(selectedPosition);
        String title;
        if(cell.getFeature() != null) {
            title =  getContext().getString(cell.getFeature().getStringId());
        } else if (cell.isLand()) {
            title = getContext().getString(R.string.desert);
        } else {
            title = getContext().getString(R.string.ocean);
        }
        float textWidth = titlePaint.measureText(title);
        float x = (SCREEN_WIDTH - textWidth)/2;
        canvas.drawText(title, x, getHeight() - 0.42f * SCREEN_WIDTH, titlePaint);
    }

    private void drawCloseButton(Canvas canvas) {
        Drawable closeButton = getDrawable(R.drawable.close_button);
        closeButton.setBounds(
                SCREEN_WIDTH - CLOSE_BUTTON_SIZE - CLOSE_BUTTON_GAP,
                getHeight() - SCREEN_WIDTH/2 + CLOSE_BUTTON_GAP,
                SCREEN_WIDTH - CLOSE_BUTTON_GAP,
                getHeight() - SCREEN_WIDTH/2 + CLOSE_BUTTON_SIZE + CLOSE_BUTTON_GAP);
        closeButton.draw(canvas);
    }

    private boolean isCloseButtonClicked(float x, float y) {
        return selectedPosition != -1
                && x > SCREEN_WIDTH - CLOSE_BUTTON_SIZE - CLOSE_BUTTON_GAP
                && x < SCREEN_WIDTH - CLOSE_BUTTON_GAP
                && y > getHeight() - SCREEN_WIDTH/2 + CLOSE_BUTTON_GAP
                && y < getHeight() - SCREEN_WIDTH/2 + CLOSE_BUTTON_SIZE + CLOSE_BUTTON_GAP;
    }

    private void drawDigButton(Canvas canvas) {
        int hint = game.getTreasureMap().getCell(selectedPosition).getHint();
        if(hint != -1) {
            float textWidth = hintPaint.measureText(String.valueOf(hint));
            float x = (SCREEN_WIDTH - textWidth)/2;
            canvas.drawText(String.valueOf(hint), x, getHeight() - SCREEN_WIDTH * 4 / 10 + DIG_BUTTON_SIZE, hintPaint);
        } else if(game.getTreasureMap().isDigPossible(selectedPosition)) {
            Drawable digButton = getDrawable(R.drawable.dig_button);
            digButton.setBounds((SCREEN_WIDTH - DIG_BUTTON_SIZE) / 2, getHeight() - SCREEN_WIDTH * 3 / 10,
                    (SCREEN_WIDTH + DIG_BUTTON_SIZE) / 2, getHeight() - SCREEN_WIDTH * 3 / 10 + DIG_BUTTON_SIZE);
            digButton.draw(canvas);
        } else {
            String message = getContext().getString(R.string.dig_message);
            float textWidth = messagePaint.measureText(message);
            float x = (SCREEN_WIDTH - textWidth)/2;
            canvas.drawText(message, x, getHeight() - 0.3f * SCREEN_WIDTH, messagePaint);
        }
    }

    private void drawTreasure(Canvas canvas) {
        if(treasureFound) {
            Drawable treasure = getDrawable(R.drawable.treasure);
            treasure.setBounds(
                    SCREEN_WIDTH/4,
                    (SCREEN_HEIGHT - SCREEN_WIDTH/2) /2,
                    3 * SCREEN_WIDTH /4,
                    (SCREEN_HEIGHT + SCREEN_WIDTH/2) /2);
            treasure.draw(canvas);
        }
    }

    private void drawHelpButton(Canvas canvas) {
        Drawable helpButton = getDrawable(R.drawable.help_button);
        helpButton.setBounds(
                SCREEN_WIDTH - CLOSE_BUTTON_SIZE - CLOSE_BUTTON_GAP,
                 CLOSE_BUTTON_GAP,
                SCREEN_WIDTH - CLOSE_BUTTON_GAP,
                CLOSE_BUTTON_SIZE + CLOSE_BUTTON_GAP);
        helpButton.draw(canvas);
    }

    private boolean isHelpButtonClicked(float x, float y) {
        return  x > SCREEN_WIDTH - CLOSE_BUTTON_SIZE - CLOSE_BUTTON_GAP
                && x < SCREEN_WIDTH - CLOSE_BUTTON_GAP
                && y > CLOSE_BUTTON_GAP
                && y < CLOSE_BUTTON_SIZE + CLOSE_BUTTON_GAP;
    }

    private boolean isDigButtonClicked(float x, float y) {
        return x > (SCREEN_WIDTH - DIG_BUTTON_SIZE) / 2
                && x < (SCREEN_WIDTH + DIG_BUTTON_SIZE) / 2
                && y > getHeight() - SCREEN_WIDTH * 3 / 10
                && y < getHeight() - SCREEN_WIDTH * 3 / 10 + DIG_BUTTON_SIZE;
    }

    private boolean isInfoWindowClicked(float x, float y) {
        return selectedPosition != -1
                && y > getHeight() - SCREEN_WIDTH/2;
    }

    private Drawable getDrawable(int id) {
        if(id != 0)
            return ContextCompat.getDrawable(getContext(), id);
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return true;
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
            layout.changeOriginX(distanceX);
            layout.changeOriginY(distanceY);
            invalidate();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            return true;
        }

        public boolean onSingleTapUp(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            if(treasureFound) {
                treasureFound = false;
                selectedPosition = -1;
                startNewGame();
            } else if(isHelpButtonClicked(x, y)) {
                Intent intent = new Intent(getContext(), HelpActivity.class);
                getContext().startActivity(intent);
            } else if(isInfoWindowClicked(x, y)) {
                if (isCloseButtonClicked(x, y)) {
                    selectedPosition = -1;
                } else if (isDigButtonClicked(x, y)) {
                    if(game.getTreasureMap().isDigPossible(selectedPosition)) {
                        treasureFound = game.dig(selectedPosition);
                    }
                }
            } else {
                selectedPosition = layout.getPosition(new Point(x, y));
            }
            invalidate();
            return true;
        }
    }

}
