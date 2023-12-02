package eightpuzzle;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class BoardTest {

    @Test
    void hamming() {
        int[][] inputTiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };

        var board = new Board(inputTiles);

        var actual = board.hamming();

        var expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    void manhattan() {
        int[][] inputTiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };

        var board = new Board(inputTiles);

        var actual = board.manhattan();

        var expected = 10;

        assertEquals(expected, actual);
    }

    @Test
    void isGoal_true() {
        int[][] inputTiles = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        var board = new Board(inputTiles);

        var actual = board.isGoal();

        var expected = true;

        assertEquals(expected, actual);
    }

    @Test
    void isGoal_false() {
        int[][] inputTiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };

        var board = new Board(inputTiles);

        var actual = board.isGoal();

        var expected = false;

        assertEquals(expected, actual);
    }

    @Test
    void neighbors() {
        int[][] inputTiles = {
                {1, 0, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        var board = new Board(inputTiles);

        var actual = board.neighbors();

        int[][] expected1 = {
                {1, 3, 0},
                {4, 2, 5},
                {7, 8, 6}
        };

        int[][] expected2 = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        int[][] expected3 = {
                {1, 2, 3},
                {4, 0, 5},
                {7, 8, 6}
        };

        var expected = Arrays.asList(new Board(expected1), new Board(expected2), new Board(expected3));

        assertIterableEquals(expected, actual);
    }
}
