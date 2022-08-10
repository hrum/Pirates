package com.shuvzero.pirates.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private TreasureMap map;
    private List<Integer> emptyWaterCells;
    private List<Integer> emptyLandCells;
    private final Random random = new Random();

    public MapGenerator(TreasureMap map) {
        this.map = map;
    }

    public void generate() {
        generateLand();
        generateLinear(Feature.River, 3);
        generateLinear(Feature.Road, 3);

    }

    private void generateLand() {
        emptyWaterCells = new ArrayList<>();
        emptyLandCells = new ArrayList<>();
        boolean isLand;
        for(Cell cell: map.getCells()) {
            if(map.isEdge(cell.getPosition()))
                isLand = false;
            else {
                isLand = random.nextInt(100) > 20;
            }
            cell.setLand(isLand);
            if(isLand)
                emptyLandCells.add(cell.getPosition());
            else
                emptyWaterCells.add(cell.getPosition());

        }
    }

    private Direction getRandomDirection() {
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    private void generateLinear(Feature feature, int quantity) {
        for(int i = 0; i < quantity; i++)
            generateLinear(feature);
    }

    private void generateLinear(Feature feature) {
        int position = emptyLandCells.get(random.nextInt(emptyLandCells.size()));


        emptyLandCells.remove((Integer)position);
        map.getCell(position).setFeature(feature);
        Direction direction = getRandomDirection();

        boolean finish = false;
        while(!finish) {
            Cell adj = map.getAdjacent(position, direction);
            if(adj.isLand() && adj.getFeature() == null) {
                position = adj.getPosition();
                emptyLandCells.remove((Integer)adj.getPosition());
                adj.setFeature(feature);
                direction = updateDirection(direction);
            } else
                finish = true;
        }
    }

    private Direction updateDirection(Direction direction) {
        Direction dir = direction;
        int num = random.nextInt(1000);
        if(num < 600) {
            if(num % 2 == 0)
                dir = direction.next();
            else
                dir = direction.previous();
        }
        else if(num < 200) {
            if(num % 2 == 0)
                dir = direction.next().next();
            else
                dir = direction.previous().previous();
        }
        return dir;
    }

    private void generateObjects() {
        //rework algorithm - should be similar to linear
    }


}
