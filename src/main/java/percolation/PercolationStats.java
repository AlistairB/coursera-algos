package percolation;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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

        var currentThread = ThreadLocalRandom.current();
        do
        {
            var toOpenRow = currentThread.nextInt(1, n);
            var toOpenCol = currentThread.nextInt(1, n);

            if (run.isOpen(toOpenRow, toOpenCol)) continue;

            run.open(toOpenRow, toOpenCol);
        } while (!run.percolates());

        return run.numberOfOpenSites();
    }

    // sample mean of percolation threshold
    public double mean() {
        return ((double) Arrays.stream(this.percolationThreshold).sum()) / this.trials;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();

        // calculate the standard deviation
        double standardDeviation = 0.0;
        for (double num : this.percolationThreshold) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / this.n);
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

    }

}