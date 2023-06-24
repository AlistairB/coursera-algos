package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private int[] percolationThreshold;

    private int size;
    private int trials;

    private int cellCount;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        this.size = n;
        this.trials = trials;
        this.cellCount = n * n;
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

            run.open(toOpenRow, toOpenCol);
        } while (!run.percolates());

        return run.numberOfOpenSites();
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(this.percolationThreshold) / cellCount;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.percolationThreshold) / cellCount;
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

        StdOut.print("mean ");
        StdOut.println(percStats.mean());

        StdOut.print("stddev ");
        StdOut.println(percStats.stddev());

        StdOut.println("95% confidence interval = " + String.format("[%f, %f]", percStats.confidenceLo(), percStats.confidenceHi()));
    }

}