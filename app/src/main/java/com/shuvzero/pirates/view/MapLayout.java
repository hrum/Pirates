package com.shuvzero.pirates.view;

import com.shuvzero.pirates.model.Point;
import com.shuvzero.pirates.model.TreasureMap;

public class MapLayout {

    private TreasureMap map;
    private float originX;
    private float originY;
    private float size;

    public MapLayout(TreasureMap map, float size) {
        this.map = map;
        this.size = size;
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
    }

    public void changeOriginY(float change) {
        originY -= change;
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
