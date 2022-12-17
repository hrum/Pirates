package com.shuvzero.pirates.model;

import java.util.ArrayList;
import java.util.List;

/*
    Treasure map always have the following symmetrical structure:
    row 0: width cells
    row 1: width+1 cells
    row 2: width cells
    and so on
 */
public class TreasureMap {

    private final int width;
    private final int height;
    private final int block;
    private final int totalCells;
    private final int bottomEdge;

    private List<Cell> cells;
    private int treasurePosition;
    private List<Feature> treasureFeatures;

    public TreasureMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.block = 2 * width + 1;
        this.totalCells = (height / 2) * block + (height % 2) * width;
        this.bottomEdge = totalCells - width - (height + 1) % 2;
        createCells();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTreasurePosition() {
        return treasurePosition;
    }

    public void setTreasurePosition(int treasurePosition) {
        this.treasurePosition = treasurePosition;
    }

    public List<Feature> getTreasureFeatures() {
        return treasureFeatures;
    }

    public void setTreasureFeatures() {
        treasureFeatures = getFeatures(treasurePosition);
    }

    public List<Feature> getFeatures(int position) {
        List<Feature> features = new ArrayList<>();
        features.add(cells.get(position).getFeature());
        for(Direction direction: Direction.values())
            features.add(getAdjacent(position, direction).getFeature());
        return features;
    }

    public Feature getFeature(int position) {
        return cells.get(position).getFeature();
    }

    private void createCells() {
        cells = new ArrayList<>();
        for(int position = 0; position < totalCells; position++)
            cells.add(new Cell(position));
    }

    public boolean isEdge(int position) {
        if(position < width)
            return true;
        if(position >= bottomEdge)
            return true;
        int rem = position % block;
        return (rem == 0 || rem == width || rem == width - 1 || rem == 2 * width);
    }

    public boolean hasAdjacent(int position, Direction direction) {
        int rem = position % block;
        switch (direction) {
            case NW:
                return position >= width && rem != width;
            case NE:
                return position >= width && rem != 2 * width;
            case SW:
                return position < bottomEdge && rem != width;
            case SE:
                return position < bottomEdge && rem != 2 * width;
            case W:
                return rem != 0 && rem != width;
            case E:
                return rem != width - 1 && rem != 2 * width;
        }
        return false;
    }

    public Cell getAdjacent(int position, Direction direction) {
        if(hasAdjacent(position, direction)) {
            switch (direction) {
                case NW:
                    return cells.get(position - width - 1);
                case NE:
                    return cells.get(position - width);
                case SW:
                    return cells.get(position + width);
                case SE:
                    return cells.get(position + width + 1);
                case W:
                    return cells.get(position - 1);
                case E:
                    return cells.get(position + 1);
            }
        }
        return null;
    }

    public List<Cell> getAdjacent(int position) {
        List<Cell> adjCells = new ArrayList<>();
        for(Direction direction: Direction.values()) {
            Cell adjCell = getAdjacent(position, direction);
            if(adjCell != null)
                adjCells.add(adjCell);
        }
        return adjCells;
    }

    public Direction getDirection(int startPos, int endPos) {
        for(Direction dir: Direction.values()) {
            Cell adj = getAdjacent(startPos, dir);
            if(adj != null && adj.getPosition() == endPos)
                return dir;
        }
        return null;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getCell(int position) {
        return cells.get(position);
    }

    public int getRow(int position) {
        int row = position / block * 2;
        if(isOddRow(position))
        row++;
        return row;
    }

    public int getColumn(int position) {
        int column = position % block;
        if(isOddRow(position))
            column -= width;
        return column;
    }

    public int getPosition(int row, int column) {
        return (row / 2) * block + (row % 2) * width + column;
    }

    public boolean isOddRow(int position) {
        return position % block >= width;
    }

    public boolean isDigPossible(int position) {
        return cells.get(position).isLand();
    }
}
