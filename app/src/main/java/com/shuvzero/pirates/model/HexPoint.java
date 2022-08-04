package com.shuvzero.pirates.model;

public class HexPoint {

    private final int q;
    private final int r;
    private final int s;

    public HexPoint(int q, int r, int s) {
        this.q = q;
        this.r = r;
        this.s = s;
        if(q + r + s != 0)
            throw new IllegalArgumentException("q + r + s must be 0");
    }

    public int q() {
        return q;
    }

    public int r() {
        return r;
    }

    public int s() {
        return s;
    }

    public HexPoint add(HexVector b) {
        return new HexPoint(q + b.q(), r + b.r(), s + b.s());
    }

    public HexPoint sub(HexVector b) {
        return new HexPoint(q - b.q(), r - b.r(), s - b.s());
    }

    public HexPoint getNeighbour(Direction direction) {
        return this.add(direction.getHexVector());
    }

    public int distance(HexPoint b) {
        return new HexVector(this, b).length();
    }
}
