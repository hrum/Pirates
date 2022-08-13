package com.shuvzero.pirates.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.shuvzero.pirates.R;
import com.shuvzero.pirates.model.Cell;
import com.shuvzero.pirates.model.Game;
import com.shuvzero.pirates.model.Point;

public class GameView extends View {

    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;

    private Game game;
    private MapLayout layout;
    private float size = 60;
    private int selectedPosition;
    private Paint paint;

    public GameView(Context context, Game game) {
        super(context);
        this.game = game;
        layout = new MapLayout(game.getTreasureMap(), new Point(0,0), size);
        selectedPosition = -1;
        createPaint();
        //size = game.getTreasureMap().getHeight()
    }

    private void createPaint() {
        paint = new Paint();
        paint.setTextSize(60);
        paint.setColor(Color.DKGRAY);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap(canvas);
        drawFrame(canvas);
        drawInfoWindow(canvas);
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
            Drawable window = getDrawable(R.drawable.info_window);
            window.setBounds(0, getHeight() - SCREEN_WIDTH/2, SCREEN_WIDTH, getHeight());
            window.draw(canvas);

            Cell cell = game.getTreasureMap().getCell(selectedPosition);
            String title;
            if(cell.getFeature() != null) {
                title =  getContext().getString(cell.getFeature().getStringId());
            } else if (cell.isLand()) {
                title = getContext().getString(R.string.desert);
            } else {
                title = getContext().getString(R.string.ocean);
            }
            float textWidth = paint.measureText(title);
            float x = (SCREEN_WIDTH - textWidth)/2;
            canvas.drawText(title, x, getHeight() - 0.42f * SCREEN_WIDTH, paint);
        }
    }

    private Drawable getDrawable(int id) {
        if(id != 0)
            return ContextCompat.getDrawable(getContext(), id);
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            selectedPosition = layout.getPosition(new Point(x, y));
            invalidate();
        }
        return true;
    }

}
