package com.shuvzero.pirates;

import com.shuvzero.pirates.model.Cell;
import com.shuvzero.pirates.model.Direction;
import com.shuvzero.pirates.model.TreasureMap;

import org.junit.Test;

public class MapTest {

    @Test
    public void checkTreasureMap() {
        TreasureMap map = new TreasureMap(10, 4);
        for(int pos = 0; pos < 15; pos++)
            for(Direction direction: Direction.values()) {
                Cell cell = map.getAdjacent(pos, direction);
                if(cell != null) {
                    System.out.println(pos + ": " + cell.getPosition());
                }
            }
    }
}