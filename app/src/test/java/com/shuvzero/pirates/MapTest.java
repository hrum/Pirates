package com.shuvzero.pirates;

import com.shuvzero.pirates.model.TreasureMap;

import org.junit.Test;

public class MapTest {

    @Test
    public void checkTreasureMap() {
        TreasureMap map = new TreasureMap(10, 4);
        for(int pos = 0; pos < 30; pos++)
            System.out.println(pos + ": " + map.getRow(pos) + " " + map.getColumn(pos));
    }
}