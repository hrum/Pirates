package com.shuvzero.pirates.view;

import android.util.Log;

import com.shuvzero.pirates.model.Point;
import com.shuvzero.pirates.model.TreasureMap;

public class MapLayout {

    private final int LEFT_LIMIT;
    private final int RIGHT_LIMIT;
    private final int TOP_LIMIT;
    private final int BOTTOM_LIMIT;

    private TreasureMap map;
    private float originX;
    private float originY;
    private float size;

    public MapLayout(TreasureMap map, int screenWidth, int screenHeight, float size) {
        this.map = map;
        this.size = size;
        LEFT_LIMIT = -(Math.round((float)Math.sqrt(3) * (map.getWidth() + 1) * size) - screenWidth/2);
        RIGHT_LIMIT = screenWidth/2;
        TOP_LIMIT = -(Math.round(1.5f * map.getHeight() * size) - screenHeight/2);
        BOTTOM_LIMIT = screenHeight/2;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

    public float getSize() {
        return size;
    }

    public void changeOriginX(float change) {
        originX -= change;
        if(originX < LEFT_LIMIT)
            originX = LEFT_LIMIT;
        if(originX > RIGHT_LIMIT)
            originX = RIGHT_LIMIT;
    }

    public void changeOriginY(float change) {
        originY -= change;
        if(originY < TOP_LIMIT)
            originY = TOP_LIMIT;
        if(originY > BOTTOM_LIMIT)
            originY = BOTTOM_LIMIT;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Point getPoint(int position) {
        int row = map.getRow(position);
        float column = map.getColumn(position);
        if(!map.isOddRow(position))
            column += 0.5f;
        float x = column * (float)Math.sqrt(3) * size;
        float y = row * 1.5f * size;
        return new Point(x + originX, y + originY);
    }

    public int getPosition(Point p) {
        Point point = new Point((p.x() - originX) / size, (p.y() - originY) / size);
        int row = Math.round(point.y() * 2/3) - 1;
        if(row < 0 || row >= map.getHeight())
            return -1;
        float col = point.x() / (float)Math.sqrt(3) - 1;
        if(row % 2 == 0)
            col -= 0.5f;
        int column = Math.round(col);
        return map.getPosition(row, column);
    }

}
