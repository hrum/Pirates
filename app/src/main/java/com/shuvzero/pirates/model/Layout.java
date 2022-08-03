package com.shuvzero.pirates.model;

import java.util.ArrayList;

public class Layout {

    public static final float f0 = (float)Math.sqrt(3);
    public static final float f1 = (float)Math.sqrt(3)/2;
    public static final float f2 = 0;
    public static final float f3 = 1.5f;
    public static final float b0 = (float)Math.sqrt(3)/3;
    public static final float b1 = -1.0f/3;
    public static final float b2 = 0;
    public static final float b3 = 2.0f/3;
    public static final float start_angle = 0.5f;

    public final Point size;
    public final Point origin;

    public Layout(Point size, Point origin) {
        this.size = size;
        this.origin = origin;
    }

    public Point hexToPixel(Hex h) {
        float x = (f0 * h.q + f1 * h.r) * size.x;
        float y = (f2 * h.q + f3 * h.r) * size.y;
        return new Point(x + origin.x, y + origin.y);
    }

    public Hex pixelToHex(Point p) {
        Point point = new Point((p.x - origin.x) / size.x, (p.y - origin.y) / size.y);
        float q = b0 * point.x + b1 * point.y;
        float r = b2 * point.x + b3 * point.y;
        return new Hex(q, r, -q - r);
    }

    public Point hexCornerOffset(int corner) {
        float angle = (float)(2.0 * Math.PI * (start_angle - corner) / 6.0);
        return new Point((float)(size.x * Math.cos(angle)), (float)(size.y * Math.sin(angle)));
    }

    public ArrayList<Point> polygonCorners(Hex h) {
        ArrayList<Point> corners = new ArrayList<Point>(){{}};
        Point center = hexToPixel(h);
        for (int i = 0; i < 6; i++) {
            Point offset = hexCornerOffset(i);
            corners.add(new Point(center.x + offset.x, center.y + offset.y));
        }
        return corners;
    }

}
