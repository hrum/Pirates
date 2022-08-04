package com.shuvzero.pirates.model;

import java.util.HashMap;

public class TreasureMap {

    private HashMap<HexCell, CellData> map;

    public TreasureMap() {
        map = new HashMap<>();
    }

    public void fill(int size) {
        for (int r = 0; r < size; r++) {
            int r_offset = r/2;
            for (int q = 0 - r_offset; q < size - r_offset; q++) {
                map.put(new HexCell(q, r, -q-r), new CellData());
                System.out.println(q + " " + r);
            }
        }
    }


}
