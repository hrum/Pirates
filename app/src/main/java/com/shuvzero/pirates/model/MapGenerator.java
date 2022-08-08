package com.shuvzero.pirates.model;

import java.util.Random;

public class MapGenerator {

    TreasureMap map;
    private final Random random = new Random();

    public MapGenerator(TreasureMap map) {
        this.map = map;
    }

    public void generate() {
        generateLand();

    }

    private void generateLand() {
        for(Cell cell: map.getCells()) {
            if(map.isEdge(cell.getPosition()))
                cell.setLand(false);
            else {
                cell.setLand(random.nextInt(100) > 25);
            }
        }
    }

    private void generateRiver() {
        //select random position on the land



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
