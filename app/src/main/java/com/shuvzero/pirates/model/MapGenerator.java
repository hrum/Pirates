package com.shuvzero.pirates.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private TreasureMap map;
    private List<Integer> waterCells;
    private List<Integer> landCells;
    private final Random random = new Random();

    public MapGenerator(TreasureMap map) {
        this.map = map;
    }

    public void generate() {
        generateLand();
        generateRivers(3);
        generateRoads(3);
    }

    private void generateLand() {
        waterCells = new ArrayList<>();
        landCells = new ArrayList<>();
        boolean isLand;
        for(Cell cell: map.getCells()) {
            if(map.isEdge(cell.getPosition()))
                isLand = false;
            else {
                isLand = random.nextInt(100) > 20;
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

    private void generateRivers(int quantity) {
        for(int river = 0; river < quantity; river++)
            generateRiver();
    }

    private void generateRiver() {
        int position = landCells.get(random.nextInt(landCells.size()));
        map.getCell(position).setFeature(Feature.River);
        Direction direction = getRiverDirection(position);
        boolean finish = false;
        while(!finish) {
            Cell adj = map.getAdjacent(position, direction);
            if(adj.isLand())
                adj.setFeature(Feature.River);
            else
                finish = true;
            direction = updateDirection(direction);
            position = adj.getPosition();
        }
    }

    private void generateRoads(int quantity) {
        for(int road = 0; road < quantity; road++)
            generateRoad();
    }

    private void generateRoad() {
        int position = landCells.get(random.nextInt(landCells.size()));
        map.getCell(position).setFeature(Feature.Road);
        Direction direction = getRandomDirection();
        boolean finish = false;
        while(!finish) {
            Cell adj = map.getAdjacent(position, direction);
            if(adj.isLand())
                adj.setFeature(Feature.Road);
            else
                finish = true;
            direction = updateDirection(direction);
            position = adj.getPosition();
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

    private Direction getRiverDirection(int position) {
        List<Cell> adjCells = map.getAdjacent(position);
        Iterator<Cell> iterator = adjCells.iterator();
        while(iterator.hasNext()) {
            Cell adjCell = iterator.next();
            if(adjCell.isLand())
                iterator.remove();
        }
        Direction direction;
        if(!adjCells.isEmpty()) {
            Cell waterCell = adjCells.get(random.nextInt(adjCells.size()));
            direction = map.getDirection(waterCell.getPosition(), position);
        } else {
            direction = getRandomDirection();
        }
        return direction;
    }

    private void generateObjects() {
        //cycle through all land tiles
        //if tile is empty
        //x% tile should be empty
        //check adjacent cells
        //y% flat object from adjacent cell
        //0% single object from adjacent cell
    }


}
