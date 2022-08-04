package com.shuvzero.pirates.model;

public class HexPoint {

    private int q;
    private int r;
    private int s;

    public HexPoint(int q, int r, int s) {
        this.q = q;
        this.r = r;
        this.s = s;
        if(q + r + s != 0)
            throw new IllegalArgumentException("q + r + s must be 0");
    }

    public HexPoint(float qF, float rF, float sF) {
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

        this.q = q;
        this.r = r;
        this.s = s;
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
        return new HexPoint(q - b.q(), r -= b.r(), s -= b.s());
    }

    public HexPoint getNeighbour(Direction direction) {
        return this.add(direction.getHexVector());
    }

    public int distance(HexPoint b) {
        return new HexVector(this, b).length();
    }
}
