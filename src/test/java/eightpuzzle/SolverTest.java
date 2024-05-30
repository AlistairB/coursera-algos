package eightpuzzle;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SolverTest {
    @Test
    void isTheSolution() {
        int[][] inputTiles = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        var board = new Board(inputTiles);

        var solver = new Solver(board);

        var expectedSolvable = true;
        var expectedMoves = 0;
        var expectedSolutionBoards = List.of(
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                })
        );

        assertEquals(expectedSolvable, solver.isSolvable());
        assertEquals(expectedMoves, solver.moves());
        assertEquals(expectedSolutionBoards, solver.solution());
    }

    @Test
    void oneStepToSolution() {
        int[][] inputTiles = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        };

        var board = new Board(inputTiles);

        var solver = new Solver(board);

        var expectedSolvable = true;
        var expectedMoves = 1;
        var expectedSolutionBoards = List.of(
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 0, 8}
                }),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                })
        );

        assertEquals(expectedSolvable, solver.isSolvable());
        assertEquals(expectedMoves, solver.moves());
        assertEquals(expectedSolutionBoards, solver.solution());
    }
}
