package com.shuvzero.pirates.model;

public enum Direction {
    NE,
    E,
    SE,
    SW,
    W,
    NW;

    public Direction next() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public Direction previous() {
        return values()[(ordinal() - 1  + values().length) % values().length];
    }
}
