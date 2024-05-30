package eightpuzzle;

import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {
    private final MinPQ<Board> boardPq;
    private ArrayList<Board> solution;
    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();

        this.boardPq = new MinPQ<>();
        this.solution = new ArrayList<>();

        // add initial
        boardPq.insert(initial);

        Board minBoard;

        Board previousMin = initial;

        while (true) {
            // del min
            minBoard = boardPq.delMin();

            solution.add(minBoard);

            if (minBoard.isGoal()) {
                break;
            }

            moves++;

            // add neighbours
            var neighbours = minBoard.neighbors();

            for (var neighbour : neighbours) {
                // don't re-add the root of the previous min board
                if (!neighbour.equals(previousMin))
                    boardPq.insert(neighbour);
            }

            previousMin = minBoard;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    // test client (see below)3
    public static void main(String[] args) {
        
    }

}
