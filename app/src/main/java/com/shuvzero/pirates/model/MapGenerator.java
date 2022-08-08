package com.shuvzero.pirates.model;

import java.util.List;
import java.util.Random;

public class MapGenerator {

    TreasureMap map;
    List<Integer> waterCells;
    List<Integer> landCells;
    private final Random random = new Random();

    public MapGenerator(TreasureMap map) {
        this.map = map;
    }

    public void generate() {
        generateLand();

    }

    private void generateLand() {
        boolean isLand;
        for(Cell cell: map.getCells()) {
            if(map.isEdge(cell.getPosition()))
                isLand = false;
            else {
                isLand = random.nextInt(100) > 25;
            }
            cell.setLand(isLand);
            if(isLand)
                landCells.add(cell.getPosition());
            else
                waterCells.add(cell.getPosition());

        }
    }

    private void generateRiver() {
        int position = landCells.get(random.nextInt(landCells.size()));

        //check for water in adjacent cells
        //if found water, connect river and get direction (if more than one cell with water, select random one)
        //otherwise select random direction
        //on each step:
        //    don't change direction - %
        //    change direction 60 - %
        //    change direction 120 - %
        //    finish river - %


    }

}
