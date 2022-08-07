package com.shuvzero.pirates;

import com.shuvzero.pirates.model.TreasureMap;

import org.junit.Test;

public class MapTest {

    @Test
    public void checkTreasureMap() {
        int width = 4;
        int height = 4;
        int totalCells = (height / 2) * (2 * width + 1) + (height % 2) * width;
        System.out.println(totalCells);
    }
}