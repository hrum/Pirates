package com.shuvzero.pirates;

import com.shuvzero.pirates.model.TreasureMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void checkTreasureMap() {
        TreasureMap treasureMap = new TreasureMap(5);
        treasureMap.fill();
    }
}