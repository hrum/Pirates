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

import java.util.HashMap;
import java.util.Map;

public class GameView extends View {

    private Game game;
    private Map<Biome, Integer> biomes;

    public GameView(Context context) {
        super(context);
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
            tile.setBounds(0, 0, 100, 100);
            tile.draw(canvas);
        }
    }

    private Drawable getDrawable(int id) {
        if(id != 0)
            return ContextCompat.getDrawable(getContext(), id);
        return null;
    }

}
