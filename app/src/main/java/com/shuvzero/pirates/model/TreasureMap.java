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
    private final int totalCells;
    private List<Cell> cells;
    private final Random random = new Random();

    public TreasureMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.totalCells = (height / 2) * (2 * width + 1) + (height % 2) * width;
        createCells();
    }

    public void generate() {

        Biome biome;
        if(r == 0 || r == height - 1 || q == -r_offset || q == width - 1 - r_offset)
            biome = Biome.Ocean;
        else
            biome = Biome.get(random.nextInt(Biome.getTotalRarity()));


        CellObject cellObject = null;
        if(random.nextInt(100) > 20)
            cellObject = generateObject(biome);
        cell.setBiome(biome);
        cell.setCellObject(cellObject);
        cells.add(cell);
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
        int rem = position % (2 * width + 1);
        return (rem == 0 || rem == width || rem == width - 1 || rem == 2 * width);

    }

    private void fillEdges() {
        //for(Cell cell: cells)
    }

    private CellObject generateObject(Biome biome) {
        CellObject cellObject;
        do {
            cellObject = CellObject.values()[random.nextInt(CellObject.values().length)];
        } while (cellObject.isLand() != biome.isLand());
            return cellObject;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getNeighbour(Cell cell, Direction direction) {
        return null; //todo
    }
}
