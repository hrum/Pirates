package com.shuvzero.pirates.view;

import com.shuvzero.pirates.model.Cell;
import com.shuvzero.pirates.model.Point;
import com.shuvzero.pirates.model.TreasureMap;

public class MapLayout {

    private static final float b0 = (float)Math.sqrt(3)/3;
    private static final float b1 = -1.0f/3;
    private static final float b2 = 0;
    private static final float b3 = 2.0f/3;

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
        float y = row * 6.0f/4 * size;
        return new Point(x + origin.x(), y + origin.y());
    }

    public Cell getPosition(Point p) {
        Point point = new Point((p.x() - origin.x()) / size, (p.y() - origin.y()) / size);
        float qF = b0 * point.x() + b1 * point.y();
        float rF = b2 * point.x() + b3 * point.y();
        float sF = -qF - rF;

        int q = Math.round(qF);
        int r = Math.round(rF);
        int s = Math.round(sF);

        double qDiff = Math.abs(q - qF);
        double rDiff = Math.abs(r - rF);
        double sDiff = Math.abs(s - sF);

        if(qDiff > rDiff && qDiff > sDiff)
            q = -r - s;
        else if(rDiff > sDiff)
            r = -q - s;
        else
            s = -q - r;

        return new Cell(q);
    }

}
