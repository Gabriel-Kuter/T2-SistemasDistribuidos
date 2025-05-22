package view;

import controller.Controller;
import controller.Observer;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import model.RoadDirection;

public class Table extends JPanel implements Observer {

    private final int size = 575;
    private int cellSize;
    private int rows;
    private int cols;
    private Controller c;
    private Cell[][] road;

    public Table() {
        this.c = Controller.getInstance();
        c.addObserver(this);
        setOpaque(false);
        draw();
    }

    public void draw() {
        rows = c.getMatrixRoad().getRowCount();
        cols = c.getMatrixRoad().getColCount();
        road = new Cell[rows][cols];
        setLayout(new GridLayout(rows, cols));
        cellSize = size / rows;
        setPreferredSize(
                new Dimension(cellSize * (int) Math.round(cols * 1.2), cellSize * (int) Math.round(rows * 1.2)));

        this.removeAll();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String p = RoadDirection.getDirectionPath(c.getMatrixRoad().getCellDirection(i, j));
                Cell cell = new Cell(p, cellSize, cellSize);
                road[i][j] = cell;
                this.add(cell);
            }
        }

        this.revalidate();
    }

    public Cell getTileByPosition(int i, int j) {
        return road[i][j];
    }

    @Override
    public void updateCarPosition(Integer[][] blockPositions) {
        Integer oldCell[] = blockPositions[0];
        Integer newCell[] = blockPositions[1];

        if (oldCell[0] != null && oldCell[1] != null) {
            road[oldCell[0]][oldCell[1]].reset();
        }

        if (newCell[0] != null && newCell[1] != null) {
            road[newCell[0]][newCell[1]].addCarLabel();
        }
    }

    @Override
    public void reset() {
        draw();
    }

    @Override
    public void updateControllStatus(boolean status) {
    }

    @Override
    public void updateThreadCounter(int counter) {
    }

    @Override
    public void initRoadFiles(String[] roadFiles) {
    }

}