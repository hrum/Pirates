package com.shuvzero.pirates.model;

import java.util.HashMap;
import java.util.Random;

public class TreasureMap {

    private int rows;
    private int cols;
    private HashMap<HexCell, CellData> map;
    private final Random random = new Random();

    public TreasureMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        map = new HashMap<>();
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
                CellObject cellObject = generateObject(biome);
                map.put(new HexCell(q, r, -q-r), new CellData(biome, cellObject));
            }
        }
    }

    private CellObject generateObject(Biome biome) {
        CellObject cellObject;
        do {
            cellObject = CellObject.values()[random.nextInt(CellObject.values().length)];
        } while (cellObject.isLand() != biome.isLand());
            return cellObject;
    }

    public HashMap<HexCell, CellData> getMap() {
        return map;
    }
}
