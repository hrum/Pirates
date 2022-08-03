package com.shuvzero.pirates.model;

import java.util.ArrayList;

public class Hex
{
    public static ArrayList<Hex> directions = new ArrayList<Hex>(){{
        add(new Hex(1, 0, -1));
        add(new Hex(1, -1, 0));
        add(new Hex(0, -1, 1));
        add(new Hex(-1, 0, 1));
        add(new Hex(-1, 1, 0));
        add(new Hex(0, 1, -1));}};
    public static ArrayList<Hex> diagonals = new ArrayList<Hex>(){{
        add(new Hex(2, -1, -1));
        add(new Hex(1, -2, 1));
        add(new Hex(-1, -1, 2));
        add(new Hex(-2, 1, 1));
        add(new Hex(-1, 2, -1));
        add(new Hex(1, 1, -2));}};

    public final int q;
    public final int r;
    public final int s;

    public Hex(int q, int r, int s) {
        this.q = q;
        this.r = r;
        this.s = s;
        if(q + r + s != 0)
            throw new IllegalArgumentException("q + r + s must be 0");
    }

    public Hex(float qFloat, float rFloat, float sFloat) {
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

    public Hex add(Hex b) {
        return new Hex(q + b.q, r + b.r, s + b.s);
    }

    public Hex subtract(Hex b) {
        return new Hex(q - b.q, r - b.r, s - b.s);
    }

    public Hex scale(int k) {
        return new Hex(q * k, r * k, s * k);
    }

    public Hex rotateLeft() {
        return new Hex(-s, -q, -r);
    }

    public Hex rotateRight() {
        return new Hex(-r, -s, -q);
    }

    public static Hex direction(int direction) {
        return Hex.directions.get(direction);
    }

    public Hex neighbor(int direction) {
        return add(Hex.direction(direction));
    }

    public Hex diagonalNeighbor(int direction) {
        return add(Hex.diagonals.get(direction));
    }

    public int length() {
        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }

    public int distance(Hex b) {
        return subtract(b).length();
    }

}