package collinear;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteCollinearPointsTest {

    @Test
    void happyDay() {
        var inputPoints = new Point[]{
                new Point(1,1),
                new Point(2,2),
                new Point(3,3),
                new Point(4,4),
        };

        var brute = new BruteCollinearPoints(inputPoints);

        var expected = new LineSegment[]{
                new LineSegment(new Point(1, 1), new Point(4,4))
        };

        assertArrayEquals(expected, brute.segments());
    }

    @Test
    void shouldFail() {
        var inputPoints = new Point[]{
                new Point(1,1),
                new Point(2,2),
                new Point(3,3),
                new Point(5,4),
        };

        var brute = new BruteCollinearPoints(inputPoints);

        var expected = new LineSegment[]{
        };

        assertArrayEquals(expected, brute.segments());
    }
}