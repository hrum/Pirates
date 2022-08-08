package com.shuvzero.pirates.model;

import java.util.Iterator;
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

    private Direction getRandomDirection() {
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    private void generateRiver() {
        int position = landCells.get(random.nextInt(landCells.size()));
        List<Cell> adjCells = map.getAdjacent(position);
        Iterator<Cell> iterator = adjCells.iterator();
        while(iterator.hasNext()) {
            Cell adjCell = iterator.next();
            if(adjCell.isLand())
                iterator.remove();
        }
        Direction dir;
        if(!adjCells.isEmpty()) {
            Cell waterCell = adjCells.get(random.nextInt(adjCells.size()));
            dir = map.getDirection(waterCell.getPosition(), position);
        } else {
            dir = getRandomDirection();
        }

        //on each step:
        //    don't change direction - %
        //    change direction 60 - %
        //    change direction 120 - %
        //    finish river - %


    }

}
