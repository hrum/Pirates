package com.shuvzero.pirates.model;

public class HexCell {

    private final int q;
    private final int r;
    private final int s;

    public HexCell(int q, int r, int s) {
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

    public HexCell add(HexVector b) {
        return new HexCell(q + b.q(), r + b.r(), s + b.s());
    }

    public HexCell sub(HexVector b) {
        return new HexCell(q - b.q(), r - b.r(), s - b.s());
    }

    public HexCell getNeighbour(Direction direction) {
        return this.add(direction.getHexVector());
    }

    public int distance(HexCell b) {
        return new HexVector(this, b).length();
    }
}
