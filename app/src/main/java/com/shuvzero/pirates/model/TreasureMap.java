package com.shuvzero.pirates.model;

import java.util.HashMap;
import java.util.Random;

public class TreasureMap {

    private int size;
    private HashMap<HexCell, CellData> map;
    private final Random random = new Random();

    public TreasureMap(int size) {
        this.size = size;
        map = new HashMap<>();
    }

    public void fill() {
        for (int r = 0; r < size; r++) {
            int r_offset = r/2;
            for (int q = 0 - r_offset; q < size - r_offset; q++) {
                Biome randomBiome = Biome.values()[random.nextInt(Biome.values().length)];
                map.put(new HexCell(q, r, -q-r), new CellData(randomBiome));
                System.out.println(q + " " + r + " " + randomBiome);
            }
        }
    }

    public HashMap<HexCell, CellData> getMap() {
        return map;
    }
}
