import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php

public class Percolation {

    private WeightedQuickUnionUF wqu;
    private boolean[][] grid;
    private int maxIndex;
    private int size;

    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        wqu = new WeightedQuickUnionUF(n);
        grid = new boolean[n][n];
        size = n;
        maxIndex = n - 1;
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        grid[row - 1][col - 1] = true;
        int value = row * col;

        connectIfOpenAndExists(value, row + 1, col);
        connectIfOpenAndExists(value, row - 1, col);
        connectIfOpenAndExists(value, row, col + 1);
        connectIfOpenAndExists(value, row, col - 1);

        openSites++;
    }

    private boolean gridVal(int row, int col) {
        return grid[row - 1][col - 1];
    }

    private void connectIfOpenAndExists(int source, int row, int col) {
        if (row >= 0 && row <= maxIndex && col >= 0 && col <= maxIndex && gridVal(row, col)) {
            wqu.union(source, row * col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return gridVal(row, col);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int value = row * col;

        for (int i = 1; i <= size; i++) {
            if (wqu.connected(value, i)) return true;
        }

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 1; i <= size; i++) {
            if (isFull(size, i)) return true;
        }

        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
