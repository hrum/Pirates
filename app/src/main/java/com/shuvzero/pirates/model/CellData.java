package com.shuvzero.pirates.model;

public class CellData {

    private Biome biome;
    private CellObject cellObject;
    private int hint;
    private boolean isFoggy;

    public CellData(Biome biome) {
        this.biome = biome;
    }
}
