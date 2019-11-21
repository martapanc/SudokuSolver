package com.example.sudokusolver.sudoku;

import java.awt.*;

public class Cell extends Point implements Comparable<Cell> {

    public Cell(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y +")";
    }


    @Override
    public int compareTo(Cell that) {
        if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return 1;
        }

        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        }

        return 0;
    }
}
