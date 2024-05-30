package eightpuzzle;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SolverTest {
    @Test
    void case1() {
        int[][] inputTiles = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        var board = new Board(inputTiles);

        var solver = new Solver(board);

        var expectedSolvable = true;
        var expectedMoves = 2;
//        var expectedSolutionBoards = null;

        assertEquals(expectedSolvable, solver.isSolvable());
        assertEquals(expectedMoves, solver.moves());
//        assertEquals(expectedSolutionBoards, solver.solution());
    }
}
