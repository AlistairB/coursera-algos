package eightpuzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
