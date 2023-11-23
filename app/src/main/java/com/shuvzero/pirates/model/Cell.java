package com.shuvzero.pirates.model;

public class Cell {

    private final int position;
    private boolean isLand;
    private Feature feature;
    private int hint;
    private boolean isFoggy;

    public Cell(int position) {
        this.position = position;
        hint = -1;
    }

    public boolean isLand() {
        return isLand;
    }

    public void setLand(boolean isLand) {
        this.isLand = isLand;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
        this.isLand = feature.isLand();
    }

    public Feature getFeature() {
        return feature;
    }

    public int getPosition() {
        return position;
    }

    public void setHint(int hint) {
        this.hint = hint;
    }

    public int getHint() {
        return hint;
    }
}
