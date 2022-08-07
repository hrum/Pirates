package com.shuvzero.pirates.model;

public class Cell {

    private final int q;
    private final int r;
    private final int s;

    private Biome biome;
    private CellObject cellObject;
    private int hint;
    private boolean isFoggy;

    public Cell(int q, int r, int s) {
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


    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public void setCellObject(CellObject cellObject) {
        this.cellObject = cellObject;
    }

    public CellObject getCellObject() {
        return cellObject;
    }

    public Cell getNeighbour(Direction direction) {
        return null; //todo
    }

}
