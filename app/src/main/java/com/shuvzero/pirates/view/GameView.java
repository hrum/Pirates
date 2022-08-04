package com.shuvzero.pirates.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.shuvzero.pirates.R;
import com.shuvzero.pirates.model.Biome;
import com.shuvzero.pirates.model.CellData;
import com.shuvzero.pirates.model.Game;
import com.shuvzero.pirates.model.HexCell;
import com.shuvzero.pirates.model.Point;

import java.util.HashMap;
import java.util.Map;

public class GameView extends View {

    private float size = 100;
    private Game game;
    private Layout layout;
    private Map<Biome, Integer> biomes;

    public GameView(Context context, Game game) {
        super(context);
        this.game = game;
        layout = new Layout(new Point(0,0), size);
        initBiomes();
    }

    public void initBiomes() {
        biomes = new HashMap<>();
        biomes.put(Biome.Ocean, R.drawable.ocean);
        biomes.put(Biome.Land, R.drawable.land);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap(canvas);
    }

    private void drawMap(Canvas canvas) {
        for(Map.Entry<HexCell, CellData> entry: game.getTreasureMap().getMap().entrySet()) {
            Drawable tile = getDrawable(biomes.get(entry.getValue().getBiome()));
            HexCell cell = entry.getKey();
            Point p = layout.getPoint(cell);
            tile.setBounds(Math.round(p.x()),
                    Math.round(p.y()),
                    Math.round(p.x() + 2 * size),
                    Math.round(p.y() + 2 * size));
            tile.draw(canvas);
        }
    }

    private Drawable getDrawable(int id) {
        if(id != 0)
            return ContextCompat.getDrawable(getContext(), id);
        return null;
    }

}
