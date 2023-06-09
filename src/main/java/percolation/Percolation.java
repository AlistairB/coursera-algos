package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php

public class Percolation {
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF iFwqu;
    private boolean[][] grid;
    private int top;
    private int bottom;
    private int size;

    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        wqu = new WeightedQuickUnionUF((n * n) + 2);

        this.top = (n * n);
        this.bottom = (n * n) + 1;
        // connect top to the top row
        for (int i = 0; i < n; i++) {
            wqu.union(this.top, i);
        }

        // connect bottom to the bottom row
        int bottomStartIndex = (n * n) - n;
        int endBottomIndex = bottomStartIndex + n - 1;

        for (int i = bottomStartIndex; i <= endBottomIndex; i++) {
            wqu.union(i, this.bottom);
        }

        iFwqu = new WeightedQuickUnionUF((n * n) + 1);

        // connect top to the top row
        for (int i = 0; i < n; i++) {
            iFwqu.union(this.top, i);
        }

        grid = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }

        size = n;
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException();

        // if we are already open, then don't want to do anything
        if (grid[row - 1][col - 1])
            return;

        grid[row - 1][col - 1] = true;
        int value = getValue(row, col);

        connectIfOpenAndExists(value, row + 1, col);
        connectIfOpenAndExists(value, row - 1, col);
        connectIfOpenAndExists(value, row, col + 1);
        connectIfOpenAndExists(value, row, col - 1);

        openSites++;
    }

    private int getValue(int row, int col) {
        return ((row-1) * this.size) + (col - 1);
    }

    private boolean gridVal(int row, int col) {
        return grid[row - 1][col - 1];
    }

    private void connectIfOpenAndExists(int source, int row, int col) {
        if (row > 0 && row <= size && col > 0 && col <= size && gridVal(row, col)) {
            int value = getValue(row, col);
            wqu.union(value, source);
            iFwqu.union(value, source);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException();

        return gridVal(row, col);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException();

        if (!isOpen(row, col)) return false;

        int value = getValue(row, col);

        return iFwqu.find(value) == iFwqu.find(this.top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // needed to handle single cell case
        if (openSites == 0) return false;

        return wqu.find(this.bottom) == wqu.find(this.top);
    }

//    private void getGrid() {
//        int rows_length = this.grid.length;
//        int columns_length = this.grid[0].length;
//
//        for (int i = 0; i < rows_length; i++) {
//            for (int j = 0; j < columns_length; j++) {
//
//                if (this.grid[i][j]) {
//                    System.out.print("X");
//                } else {
//                    System.out.print(" ");
//                }
//
//            }
//            System.out.println();
//        }
//    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
