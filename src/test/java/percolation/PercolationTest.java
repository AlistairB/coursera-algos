package percolation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    @Test
    void singleCell() {
        var perc = new Percolation(1);

        assertFalse(perc.percolates());

        perc.open(1, 1);

        assertTrue(perc.percolates());
    }

    @Test
    void twoByTwoCell() {
        var perc = new Percolation(2);

        assertFalse(perc.percolates());

        perc.open(1, 1);

        assertFalse(perc.percolates());

        perc.open(2, 1);

        assertTrue(perc.percolates());
    }

    @Test
    void diagonal() {
        var perc = new Percolation(3);

        assertFalse(perc.percolates());

        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(2, 3);

        assertFalse(perc.percolates());

        perc.open(3, 3);

        assertTrue(perc.percolates());
    }

    @Test
    void hmm() {
        var perc = new Percolation(3);

        perc.open(1, 2);
        perc.open(2, 1);
        perc.open(2, 3);

        assertFalse(perc.percolates());

        perc.open(3, 1);

        assertFalse(perc.percolates());
    }

    @Test
    void manyByManyCell() {
        var perc = new Percolation(8);

        assertFalse(perc.percolates());

        perc.open(1, 8);
        perc.open(2, 8);
        perc.open(3, 8);
        perc.open(4, 8);
        perc.open(5, 8);
        perc.open(6, 8);
        perc.open(7, 8);

        assertFalse(perc.percolates());

        perc.open(8, 8);

        assertTrue(perc.percolates());
    }

    @Test
    void noBackWash() {
        var perc = new Percolation(3);

        assertFalse(perc.percolates());

        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(3, 1);

        assertTrue(perc.percolates());

        perc.open(3, 3);

        assertFalse(perc.isFull(3, 3));
    }
}