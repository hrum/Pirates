package com.shuvzero.pirates.model;

public class CellData {

    private Biome biome;
    private CellObject cellObject;
    private int hint;
    private boolean isFoggy;

    public CellData(Biome biome, CellObject cellObject) {
        this.biome = biome;
        this.cellObject = cellObject;
    }

    public Biome getBiome() {
        return biome;
    }

    public CellObject getCellObject() {
        return cellObject;
    }
}
