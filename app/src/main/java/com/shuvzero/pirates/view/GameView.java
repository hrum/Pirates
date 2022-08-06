package com.shuvzero.pirates.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.shuvzero.pirates.model.Cell;
import com.shuvzero.pirates.model.Game;
import com.shuvzero.pirates.model.Point;

import java.util.Map;

public class GameView extends View {

    private float size = 58;
    private Game game;
    private Layout layout;

    public GameView(Context context, Game game) {
        super(context);
        this.game = game;
        layout = new Layout(new Point(0,0), size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap(canvas);
    }

    private void drawMap(Canvas canvas) {
        for(Cell cell: game.getTreasureMap().getCells()) {
            Drawable tile = getDrawable(cell.getBiome().getId());
            Point p = layout.getPoint(cell);
            tile.setBounds(Math.round(p.x()),
                    Math.round(p.y()),
                    Math.round(p.x() + 2 * size),
                    Math.round(p.y() + 2 * size));
            tile.draw(canvas);
            /*if(cell.getCellObject() != null) {
                Drawable obj = getDrawable(cell.getCellObject().getId());
                obj.setBounds(Math.round(p.x()),
                        Math.round(p.y()),
                        Math.round(p.x() + 2 * size),
                        Math.round(p.y() + 2 * size));
                obj.draw(canvas);
            }*/
        }
    }

    private Drawable getDrawable(int id) {
        if(id != 0)
            return ContextCompat.getDrawable(getContext(), id);
        return null;
    }

}
