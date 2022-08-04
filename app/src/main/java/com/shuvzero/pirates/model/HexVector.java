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

    public HexVector(HexCell origin, HexCell end) {
        this.q = end.q() - origin.q();
        this.r = end.r() - origin.r();
        this.s = end.s() - origin.s();
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

    public void add(HexVector b) {
        q += b.q;
        r += b.r;
        s += b.s;
    }

    public void sub(HexVector b) {
        q -= b.q;
        r -= b.r;
        s -= b.s;
    }

    public void scale(int k) {
        q *= k;
        r *= k;
        s *= k;
    }

    public int length() {
        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }

}