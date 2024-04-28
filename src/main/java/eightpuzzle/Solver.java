package eightpuzzle;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private final MinPQ<Board> boardPq;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.boardPq = new MinPQ<>();

        // add initial
        boardPq.insert(initial);

        Board minBoard;

        while (true) {
            // del min
            minBoard = boardPq.delMin();

            if (minBoard.isGoal()) {
                break;
            }

            // add neighbours
            var neighbours = minBoard.neighbors();

            for (var neighbour : neighbours) {
                boardPq.insert(neighbour);
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return null;
    }

    // test client (see below)3
    public static void main(String[] args) {
        
    }

}
