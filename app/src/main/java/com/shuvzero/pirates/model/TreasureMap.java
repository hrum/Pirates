package com.shuvzero.pirates.model;

import java.util.HashSet;
import java.util.Random;

public class TreasureMap {

    private int rows;
    private int cols;
    private HashSet<Cell> cells;
    private final Random random = new Random();

    public TreasureMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new HashSet<>();
    }

    public void generate() {
        for (int r = 0; r < rows; r++) {
            int r_offset = r/2;
            for (int q = 0 - r_offset; q < cols - r_offset; q++) {
                Biome biome;
                if(r == 0 || r == rows - 1 || q == -r_offset || q == cols - 1 - r_offset)
                    biome = Biome.Ocean;
                else
                    biome = Biome.get(random.nextInt(Biome.getTotalRarity()));
                CellObject cellObject = null;
                if(random.nextInt(100) > 20)
                    cellObject = generateObject(biome);
                Cell cell = new Cell(q, r, -q-r);
                cell.setBiome(biome);
                cell.setCellObject(cellObject);
                cells.add(cell);
            }
        }
    }

    private void createCells() {
        for (int r = 0; r < rows; r++) {
            int r_offset = r/2;
            for (int q = 0 - r_offset; q < cols - r_offset; q++) {
                cells.add(new Cell(q, r, -q-r));
            }
        }
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

    public HashSet<Cell> getCells() {
        return cells;
    }
}
