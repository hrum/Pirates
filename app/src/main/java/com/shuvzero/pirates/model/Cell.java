package com.shuvzero.pirates.model;

public class Cell {

    private final int position;
    private boolean isLand;
    private CellObject cellObject;
    private int hint;
    private boolean isFoggy;

    public Cell(int position) {
        this.position = position;
    }

    public boolean isLand() {
        return isLand;
    }

    public void setLand(boolean isLand) {
        this.isLand = isLand;
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
