package eightpuzzle;

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
        solution.add(initial);

        Board minBoard;

        while (true) {
            // del min
            minBoard = boardPq.delMin();

            if (minBoard.isGoal()) {
                break;
            }

            moves++;
            solution.add(minBoard);

            // add neighbours
            var neighbours = minBoard.neighbors();

            for (var neighbour : neighbours) {
                boardPq.insert(neighbour);
            }
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
