package eightpuzzle;

// https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class Board implements Comparable<Board> {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final int[][] tiles;
    private final int hamming;
    private final int manhattan;

    // Constructor.  
    // You may assume that the constructor receives an n-by-n array containing the n2 integers between 0 and n2 − 1, where 0 represents the blank square. 
    // You may also assume that 2 ≤ n < 128.
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.hamming = calcHamming();
        this.manhattan = calcManhattan();
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
        return this.hamming;
    }

    private int calcHamming() {
        int numRows = tiles.length;
        int numCols = tiles[0].length; // Assuming all rows have the same number of columns
        int hamming = 0;

        // Iterate through the 2D array using nested loops
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int element = tiles[row][col];
                if (element == 0)
                    continue;

                int desired = (row * dimension()) + (col + 1);
                if (element != desired)
                    hamming++;
            }
        }

        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return this.manhattan;
    }

    private int calcManhattan() {
        int numRows = tiles.length;
        int numCols = tiles[0].length; // Assuming all rows have the same number of columns
        int manhattan = 0;

        // Iterate through the 2D array using nested loops
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int element = tiles[row][col];
                if (element == 0)
                    continue;

                int correctRow = element / dimension();
                int correctCol = ((element - 1) % dimension());
                int rowGap = Math.abs(row - correctRow);
                int colGap = Math.abs(col - correctCol);

                manhattan += rowGap + colGap;
            }
        }

        return manhattan;
    }

//    private boolean isCorrectPos(int x, int y, int value) {
//
//    }

    // is this board the goal board?
    public boolean isGoal() {
        int numRows = tiles.length;
        int numCols = tiles[0].length; // Assuming all rows have the same number of columns

        int currentExpected = 1;

        // Iterate through the 2D array using nested loops
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int element = tiles[row][col];

                // if we get the blank tile, it must only be in the last row and col
                if (element == 0) {
                    return (row == numRows - 1 && col == numCols - 1);
                }

                if (element != currentExpected++)
                    return false;
            }
        }

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;

        Board yBoard = (Board) y;

        return Arrays.deepEquals(yBoard.tiles, this.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        // find 0
        int zeroRow = 0;
        int zeroCol = 0;

        int numRows = tiles.length;
        int numCols = tiles[0].length; // Assuming all rows have the same number of columns

        // Iterate through the 2D array using nested loops
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int element = tiles[row][col];

                if (element == 0) {
                    zeroRow = row;
                    zeroCol = col;
                }
            }
        }

        var neighbourBoards = new ArrayList<Board>();

        // look for 0 -> right board
        if (zeroCol < numCols - 1) {
            int[][] rightMoveArray = deepCopy(tiles);

            // move the piece to the right of the 0 into the 0 spot
            rightMoveArray[zeroRow][zeroCol] = rightMoveArray[zeroRow][zeroCol + 1];
            // then set that piece to 0
            rightMoveArray[zeroRow][zeroCol + 1] = 0;

            neighbourBoards.add(new Board(rightMoveArray));
        }

        // look for 0 -> left board
        if (zeroCol > 0) {
            int[][] leftMoveArray = deepCopy(tiles);

            // move the piece to the left of the 0 into the 0 spot
            leftMoveArray[zeroRow][zeroCol] = leftMoveArray[zeroRow][zeroCol - 1];
            // then set that piece to 0
            leftMoveArray[zeroRow][zeroCol - 1] = 0;

            neighbourBoards.add(new Board(leftMoveArray));
        }

        // look for 0 -> up board
        if (zeroRow > 0) {
            int[][] upMoveArray = deepCopy(tiles);

            // move the piece to the left of the 0 into the 0 spot
            upMoveArray[zeroRow][zeroCol] = upMoveArray[zeroRow - 1][zeroCol];
            // then set that piece to 0
            upMoveArray[zeroRow - 1][zeroCol] = 0;

            neighbourBoards.add(new Board(upMoveArray));
        }

        // look for 0 -> down board
        if (zeroRow < numRows - 1) {
            int[][] downMoveArray = deepCopy(tiles);

            // move the piece to the left of the 0 into the 0 spot
            downMoveArray[zeroRow][zeroCol] = downMoveArray[zeroRow + 1][zeroCol];
            // then set that piece to 0
            downMoveArray[zeroRow + 1][zeroCol] = 0;

            neighbourBoards.add(new Board(downMoveArray));
        }


        return neighbourBoards;
    }

    private int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            // Ensure that the nested arrays are also copied
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

    @Override
    public int compareTo(Board that) {
        if (that == null)
            throw new IllegalArgumentException();

        return Integer.compare(this.manhattan(), that.manhattan());
    }
}
