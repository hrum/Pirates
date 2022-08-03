package com.shuvzero.pirates.model;

public class HexVector {

    private int q;
    private int r;
    private int s;

    public HexVector(int q, int r, int s) {
        this.q = q;
        this.r = r;
        this.s = s;
        if(q + r + s != 0)
            throw new IllegalArgumentException("q + r + s must be 0");
    }

    public HexVector(float qFloat, float rFloat, float sFloat) {
        int q = Math.round(qFloat);
        int r = Math.round(rFloat);
        int s = Math.round(sFloat);

        double qDiff = Math.abs(q - qFloat);
        double rDiff = Math.abs(r - rFloat);
        double sDiff = Math.abs(s - sFloat);

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

    public HexVector add(HexVector b) {
        return new HexVector(q + b.q, r + b.r, s + b.s);
    }

    public HexVector sub(HexVector b) {
        return new HexVector(q - b.q, r - b.r, s - b.s);
    }

    public HexVector scale(int k) {
        return new HexVector(q * k, r * k, s * k);
    }

    public HexVector neighbour(Direction direction) {
        return this.add(direction.getHexVector());
    }

    public int length() {
        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }

    public int distance(HexVector b) {
        return this.sub(b).length();
    }

}