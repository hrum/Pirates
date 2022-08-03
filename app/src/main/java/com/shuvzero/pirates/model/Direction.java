package com.shuvzero.pirates.model;

public enum Direction {

    NE(new HexVector(1, -1, 0)),
    E(new HexVector(1, 0, -1)),
    SE(new HexVector(0, 1, -1)),
    SW(new HexVector(-1, 1, 0)),
    W(new HexVector(-1, 0, 1)),
    NW(new HexVector(0, -1, 1));

    private HexVector hexVector;

    Direction(HexVector hexVector) {
        this.hexVector = hexVector;
    }

    public HexVector getHexVector() {
        return hexVector;
    }
}
