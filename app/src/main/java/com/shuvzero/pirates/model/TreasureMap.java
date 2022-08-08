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
    private final Random random = new Random();

    public TreasureMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.block = 2 * width + 1;
        this.totalCells = (height / 2) * block + (height % 2) * width;
        createCells();
    }

    public void generate() {

        generateLand();


        /*CellObject cellObject = null;
        if(random.nextInt(100) > 20)
            cellObject = generateObject(biome);
        cell.setLand(biome);
        cell.setCellObject(cellObject);
        cells.add(cell);*/
    }

    private void createCells() {
        cells = new ArrayList<>();
        for(int position = 0; position < totalCells; position++)
            cells.add(new Cell(position));
    }

    private boolean isEdge(int position) {
        if(position < width)
            return true;
        if(position >= totalCells - width - (height + 1) % 2)
            return true;
        int rem = position % block;
        return (rem == 0 || rem == width || rem == width - 1 || rem == 2 * width);

    }

    private void generateLand() {
        for(Cell cell: cells) {
            if(isEdge(cell.getPosition()))
                cell.setLand(false);
            else {
                cell.setLand(random.nextInt(100) > 25);
            }
        }
    }

    private CellObject generateObject(boolean isLand) {
        CellObject cellObject;
        do {
            cellObject = CellObject.values()[random.nextInt(CellObject.values().length)];
        } while (cellObject.isLand() != isLand);
            return cellObject;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getNeighbour(Cell cell, Direction direction) {
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
