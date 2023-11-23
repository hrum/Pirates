package com.shuvzero.pirates.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MapGenerator {

    private static final int MAX_OBJECTS_COUNT = 8;
    private static final int OCEAN_COUNT = 5;

    private TreasureMap map;
    private List<Integer> emptyWaterCells;
    private List<Integer> emptyLandCells;
    private final Random random = new Random();

    public MapGenerator(TreasureMap map) {
        this.map = map;
    }

    public void generate() {
        /*generateLand();
        generateTreasure();
        generateLinearFeature(Feature.River);
        generateLinearFeature(Feature.Road);
        generateFlatFeatures();
        generateSingleFeatures();
        sortWaterCells();
        //generateSingleOceanFeatures();
        fillEmptyCells();*/
        setFixedField();
        generateTreasure();
    }

    private void setFixedField() {
        for(Cell cell: map.getCells()) {
            if(map.isEdge(cell.getPosition()))
                cell.setFeature(Feature.Ocean);
            else
                cell.setFeature(Feature.Desert);
        }
        map.setRow(1, new String[]{"Ocean","Skull","River","Jungle","Jungle","Road","Cactus","Desert","Sea","Sea","Sea","Mountain","Ocean"});
        map.setRow(2, new String[]{"Ocean","Grass","River","Palm","Forest","Road","Road","Grass","Grass","Sea","Desert","Ocean"});
        map.setRow(3, new String[]{"Ocean","Grass","Grass","River","Forest","Desert","Plains","Road","Volcano","House","Forest","Desert","Ocean"});
        map.setRow(4, new String[]{"Ocean","Cave","River","Desert","Sea","Sea","Plains","Road","Road","Forest","Road","Ocean"});
        map.setRow(5, new String[]{"Ocean","Cactus","Plains","River","Plains","Sea","Plains","Road","Mountain","Road","Road","House", "Ocean"});
        map.setRow(6, new String[]{"Ocean","Plains","River","Plains","Cave","Hills","Hills","Road","Cactus","Skull","Swamp","Ocean"});
        map.setRow(7, new String[]{"Ocean","Mountain","Swamp","River","River","River","Hills","Forest","Road","Plains","Swamp","Hills","Ocean"});
        map.setRow(8, new String[]{"Ocean","House","Swamp","Swamp","Forest","River","Forest","Forest","Road","Plains","Hills","Ocean"});
        map.setRow(9, new String[]{"Ocean","Sea","Sea","Sea","Forest","River","Forest","Desert","Road","Desert","Sea","Sea","Ocean"});
        map.setRow(10, new String[]{"Ocean","Hills","Sea","Volcano","River","Forest","Cactus","Road","Desert","Sea","Desert","Ocean"});
        map.setRow(11, new String[]{"Ocean","Hills","Hills","Desert","River","Palm","Desert","Grass","Road","Skull","Swamp","Desert","Ocean"});
        map.setRow(12, new String[]{"Ocean","Jungle","Volcano","Sea","Sea","Cave","Grass","Grass","Road","Jungle","Swamp","Ocean"});
        map.setRow(13, new String[]{"Ocean","Jungle","Jungle","Sea","Sea","Sea","Desert","Grass","Road","Palm","Jungle","Swamp","Ocean"});
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

    private void generateTreasure() {
        boolean done = false;
        while(!done) {
            int treasurePosition = random.nextInt(map.getCells().size());
            if(map.getCell(treasurePosition).isLand()) {
                map.setTreasurePosition(treasurePosition);
                done = true;
            }
        }
    }

    private Direction getRandomDirection() {
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    private void generateLinearFeature(Feature feature) {
        for(int number = 0; number < random.nextInt(MAX_OBJECTS_COUNT + 1); ) {
            Set<Integer> cells = new HashSet<>();
            int position = emptyLandCells.get(random.nextInt(emptyLandCells.size()));
            cells.add(position);
            Direction direction = getRandomDirection();

            boolean finish = false;
            while (!finish) {
                Cell adj = map.getAdjacent(position, direction);
                if (adj.isLand() && adj.getFeature() == null) {
                    position = adj.getPosition();
                    cells.add(position);
                    direction = updateDirection(direction);
                } else
                    finish = true;
            }
            if (cells.size() >= 2) {
                number++;
                for (Integer cell : cells) {
                    emptyLandCells.remove(cell);
                    map.getCell(cell).setFeature(feature);
                }
            }
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

    private void generateFlatFeatures() {
        for (Feature feature : Feature.values()) {
            if(feature.isLand() && feature.getFeatureType() == FeatureType.Flat) {
                for(int i = 0; i < random.nextInt(MAX_OBJECTS_COUNT + 1); i++) {
                    int position = emptyLandCells.get(random.nextInt(emptyLandCells.size()));
                    emptyLandCells.remove((Integer) position);
                    map.getCell(position).setFeature(feature);

                    for (Direction direction : Direction.values()) {
                        Cell adj = map.getAdjacent(position, direction);
                        if (adj.isLand() && adj.getFeature() == null) {
                            if (random.nextInt(100) < 70) {
                                position = adj.getPosition();
                                emptyLandCells.remove((Integer) position);
                                map.getCell(position).setFeature(feature);
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateSingleFeatures() {
        for(Feature feature: Feature.values()) {
            if (feature.isLand() && feature.getFeatureType() == FeatureType.Single) {
                for (int i = 0; i < random.nextInt(MAX_OBJECTS_COUNT + 1); i++) {
                    int position = emptyLandCells.get(random.nextInt(emptyLandCells.size()));
                    emptyLandCells.remove(emptyLandCells.indexOf(position));
                    map.getCell(position).setFeature(feature);
                }
            }
        }
    }

    private void generateSingleOceanFeatures() {
        for(Feature feature: Feature.values()) {
            if (!feature.isLand() && feature.getFeatureType() == FeatureType.Single && feature != Feature.Lake) {
                for (int i = 0; i < OCEAN_COUNT; i++) {
                    int position = emptyWaterCells.get(random.nextInt(emptyWaterCells.size()));
                    emptyWaterCells.remove((Integer) position);
                    map.getCell(position).setFeature(feature);
                }
            }
        }
    }

    private void sortWaterCells() {
        Iterator<Integer> iterator = emptyWaterCells.iterator();
        while(iterator.hasNext()) {
            int position = iterator.next();
            Cell cell = map.getCell(position);
            if (!map.isEdge(position)) {
                cell.setFeature(Feature.Sea);
                iterator.remove();
            }
        }
    }

    private void fillEmptyCells() {
        for(int position: emptyWaterCells) {
            Cell cell = map.getCell(position);
            cell.setFeature(Feature.Ocean);
        }

        for(int position: emptyLandCells) {
            Cell cell = map.getCell(position);
            cell.setFeature(Feature.Desert);
        }
    }

}
