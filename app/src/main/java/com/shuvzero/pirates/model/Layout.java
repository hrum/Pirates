package com.shuvzero.pirates.model;

public class Layout {

    private static final float f0 = (float)Math.sqrt(3);
    private static final float f1 = (float)Math.sqrt(3)/2;
    private static final float f2 = 0;
    private static final float f3 = 1.5f;
    private static final float b0 = (float)Math.sqrt(3)/3;
    private static final float b1 = -1.0f/3;
    private static final float b2 = 0;
    private static final float b3 = 2.0f/3;

    private Point origin;
    private float size;

    public Layout(Point origin, float size) {
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

    public Point getPoint(HexPoint h) {
        float x = (f0 * h.q() + f1 * h.r()) * size;
        float y = (f2 * h.q() + f3 * h.r()) * size;
        return new Point(x + origin.x(), y + origin.y());
    }

    public HexPoint getHexPoint(Point p) {
        Point point = new Point((p.x() - origin.x()) / size, (p.y() - origin.y()) / size);
        float q = b0 * point.x() + b1 * point.y();
        float r = b2 * point.x() + b3 * point.y();
        return new HexPoint(q, r, -q - r);
    }

}
