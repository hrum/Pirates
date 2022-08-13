package com.shuvzero.pirates.view;

import com.shuvzero.pirates.model.Point;
import com.shuvzero.pirates.model.TreasureMap;

public class MapLayout {

    private TreasureMap map;
    private Point origin;
    private float size;

    public MapLayout(TreasureMap map, Point origin, float size) {
        this.map = map;
        this.origin = origin;
        this.size = size;
    }

    public Point getOrigin() {
        return origin;
    }

    public float getSize() {
        return size;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
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
        return new Point(x + origin.x(), y + origin.y());
    }

    public int getPosition(Point p) {
        Point point = new Point((p.x() - origin.x()) / size, (p.y() - origin.y()) / size);
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
