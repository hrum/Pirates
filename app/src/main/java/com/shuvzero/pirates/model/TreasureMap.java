package com.shuvzero.pirates.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*
    Treasure map always have the following symmetrical structure:
    row 0: width cells
    row 1: width+1 cells
    row 2: width cells
    and so on
 */
public class TreasureMap {

    private final int width;
    private final int height;
    private final int block;
    private final int totalCells;
    private List<Cell> cells;

    public TreasureMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.block = 2 * width + 1;
        this.totalCells = (height / 2) * block + (height % 2) * width;
        createCells();
    }

    private void createCells() {
        cells = new ArrayList<>();
        for(int position = 0; position < totalCells; position++)
            cells.add(new Cell(position));
    }

    public boolean isEdge(int position) {
        if(position < width)
            return true;
        if(position >= totalCells - width - (height + 1) % 2)
            return true;
        int rem = position % block;
        return (rem == 0 || rem == width || rem == width - 1 || rem == 2 * width);

    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getAdjacent(Cell cell, Direction direction) {
        return null; //todo
    }

    public int getRow(int position) {
        int row = position / block * 2;
        if(isOddRow(position))
        row++;
        return row;
    }

    public int getColumn(int position) {
        int column = position % block;
        if(isOddRow(position))
            column -= width;
        return column;
    }

    public boolean isOddRow(int position) {
        return position % block >= width;
    }
}
