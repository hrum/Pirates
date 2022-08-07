package com.shuvzero.pirates.model;

public class Cell {

    private final int position;
    private Biome biome;
    private CellObject cellObject;
    private int hint;
    private boolean isFoggy;

    public Cell(int position) {
        this.position = position;
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

    public int getPosition() {
        return position;
    }
}
