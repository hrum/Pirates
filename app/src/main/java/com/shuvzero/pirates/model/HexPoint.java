package com.shuvzero.pirates.model;

public class HexPoint {

    private int q;
    private int r;
    private int s;

    public int q() {
        return q;
    }

    public int r() {
        return r;
    }

    public int s() {
        return s;
    }

    public void add(HexVector b) {
        q += b.q();
        r += b.r();
        s += b.s();
    }

    public void sub(HexVector b) {
        q -= b.q();
        r -= b.r();
        s -= b.s();
    }

    public HexPoint getNeighbour(Direction direction) {
        return this.add(direction.getHexVector());
    }

    public int distance(HexPoint b) {
        return new HexVector(this, b).length();
    }
}
