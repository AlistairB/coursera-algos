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
}