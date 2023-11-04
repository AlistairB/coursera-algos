package eightpuzzle;

// https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php

import edu.princeton.cs.algs4.StdOut;

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[][] tiles;

    // Constructor.  
    // You may assume that the constructor receives an n-by-n array containing the n2 integers between 0 and n2 − 1, where 0 represents the blank square. 
    // You may also assume that 2 ≤ n < 128.
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    // string representation of this board The toString() method returns a string composed of n + 1 lines. 
    // The first line contains the board size n; the remaining n lines contains the n-by-n grid of tiles in row-major order, using 0 to designate the blank square.
    public String toString() {
        var stringBuilder = new StringBuilder();

        stringBuilder.append(tiles.length);

        int numRows = tiles.length;
        int numCols = tiles[0].length; // Assuming all rows have the same number of columns

        // Iterate through the 2D array using nested loops
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int element = tiles[row][col];
                stringBuilder.append(element);
            }
            stringBuilder.append("\n"); // Move to the next row
        }

        return stringBuilder.toString();
    }

    // board dimension n
    public int dimension() {
            return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {

        int numRows = tiles.length;
        int numCols = tiles[0].length; // Assuming all rows have the same number of columns
        int hamming = 0;

        // Iterate through the 2D array using nested loops
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int element = tiles[row][col];
                if (element == 0)
                    continue;

                int desired = (row + 1) * (col + 1);
                if (element != desired)
                    hamming++;
            }
        }


        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}