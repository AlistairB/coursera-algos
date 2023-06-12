package percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int[] percolationThreshold;

    private int n;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        percolationThreshold = new int[trials];

        for (int i = 0; i < trials; i++) {
            var threshold = runPercolation(n);

            percolationThreshold[i] = threshold;
        }
    }

    private int runPercolation(int n) {
        var run = new Percolation(n);

        do
        {
            var toOpenRow = StdRandom.uniformInt(1, n + 1);
            var toOpenCol = StdRandom.uniformInt(1, n + 1);

            if (run.isOpen(toOpenRow, toOpenCol)) continue;

            run.open(toOpenRow, toOpenCol);
        } while (!run.percolates());

        return run.numberOfOpenSites();
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(this.percolationThreshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.percolationThreshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (2 * stddev());
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (2 * stddev());
    }

    // test client (see below)
    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        var percStats = new PercolationStats(gridSize, trials);

        StdOut.print("mean: ");
        StdOut.println(percStats.mean());

        StdOut.print("stddev: ");
        StdOut.println(percStats.stddev());

        StdOut.print("95% low: ");
        StdOut.println(percStats.confidenceLo());

        StdOut.print("95% high: ");
        StdOut.println(percStats.confidenceHi());
    }

}