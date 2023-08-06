package collinear;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FastCollinearPointsTest {

    @Test
    void happyDay() {
        var inputPoints = new Point[]{
                new Point(1,1),
                new Point(2,2),
                new Point(3,3),
                new Point(4,4),
        };

        var brute = new FastCollinearPoints(inputPoints);

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

        var brute = new FastCollinearPoints(inputPoints);

        var expected = new LineSegment[]{
        };

        assertArrayEquals(expected, brute.segments());
    }

    @Test
    void eight() {
        var inputPoints = new Point[]{
                new Point(10000 ,     0),
                new Point(0,  10000),
                new Point(3000,   7000),
                new Point(7000,   3000),
                new Point(20000,  21000),
                new Point(3000,   4000),
                new Point(14000,  15000),
                new Point(6000,   7000),
        };

        var brute = new FastCollinearPoints(inputPoints);

        var expected = new LineSegment[]{
            new LineSegment(new Point(10000,0), new Point(0,10000)),
            new LineSegment(new Point(3000,4000), new Point(20000,21000)),
        };

        var actual = brute.segments();

        assertArrayEquals(expected, actual);
    }

    @Test
    void duplicate() {
        var inputPoints = new Point[]{
                new Point(30196 ,     13179),
                new Point(30009,  17252),
                new Point(8532,   31244),
                new Point(22609,   3316),
                new Point(22609,  3316),
        };

        assertThrows(IllegalArgumentException.class, () -> {
            new FastCollinearPoints(inputPoints);
        });
    }
}