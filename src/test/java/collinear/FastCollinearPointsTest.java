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

//    @Test
//    void nullEntires() {
//        var inputPoints = new Point[]{
//                null,
//                new Point(1,1),
//                new Point(2,2),
//                new Point(3,3),
//                new Point(4,4),
//        };
//
//        var brute = new FastCollinearPoints(inputPoints);
//
//        var expected = new LineSegment[]{
//                new LineSegment(new Point(1, 1), new Point(4,4))
//        };
//
//        assertArrayEquals(expected, brute.segments());
//    }

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
    void equidistant() {
        var inputPoints = new Point[]{
                new Point(10000, 0),
                new Point(8000, 2000),
                new Point(2000, 8000),
                new Point(0,  10000),
                new Point(20000, 0),
                new Point(18000, 2000),
                new Point(2000, 18000),
                new Point(10000, 20000),
                new Point(30000, 0),
                new Point(0, 30000),
                new Point(20000, 10000),
                new Point(13000, 0),
                new Point(11000, 3000),
                new Point(5000, 12000),
                new Point(9000, 6000),
        };

        var brute = new FastCollinearPoints(inputPoints);

        var expected = new LineSegment[]{
                new LineSegment(new Point(10000,0), new Point(0,10000)),
                new LineSegment(new Point(10000,0), new Point(30000,0)),
                new LineSegment(new Point(13000,0), new Point(5000,12000)), // missing
                new LineSegment(new Point(30000,0), new Point(0,30000)),
        };

        var actual = brute.segments();

        assertArrayEquals(expected, actual);
    }

//    @Test
//    void play() {
//        var inputPoints = new Point[]{
//                new Point(10000, 0),
//                new Point(8000, 2000),
////                new Point(2000, 8000),
//                new Point(0,  10000),
//                new Point(20000, 0),
//                new Point(18000, 2000),
//                new Point(2000, 18000),
//                new Point(10000, 20000),
//                new Point(30000, 0),
//                new Point(0, 30000),
//                new Point(20000, 10000),
//                new Point(13000, 0),
////                new Point(11000, 3000),
////                new Point(5000, 12000),
////                new Point(9000, 6000),
//        };
//
//        var brute = new FastCollinearPoints(inputPoints);
//
//        var expected = new LineSegment[]{
////                new LineSegment(new Point(10000,0), new Point(0,10000)),
//                new LineSegment(new Point(10000,0), new Point(30000,0)), // missing
////                new LineSegment(new Point(13000,0), new Point(5000,12000)),
////                new LineSegment(new Point(30000,0), new Point(0,30000)),
//        };
//
//        var actual = brute.segments();
//
//        assertArrayEquals(expected, actual);
//    }

    @Test
    void nine() {
        var inputPoints = new Point[]{
                new Point(9000 ,9000),
                new Point(8000 ,8000),
                new Point(7000 ,7000),
                new Point(6000 ,6000),
                new Point(5000 ,5000),
                new Point(4000 ,4000),
                new Point(3000 ,3000),
                new Point(2000 ,2000),
                new Point(1000 ,1000),
        };

        var brute = new FastCollinearPoints(inputPoints);

        var expected = new LineSegment[]{
                new LineSegment(new Point(1000,1000), new Point(9000,9000)),
        };

        var actual = brute.segments();

        assertArrayEquals(expected, actual);
    }

    @Test
    void twenty() {
        var inputPoints = new Point[]{
                new Point(4096, 20992),
                new Point(5120, 20992),
                new Point(6144, 20992),
                new Point(7168, 20992),
                new Point(8128, 20992),
                new Point(4096, 22016),
                new Point(4096, 23040),
                new Point(4096, 24064),
                new Point(4096, 25088),
                new Point(5120, 25088),
                new Point(7168, 25088),
                new Point(8192, 25088),
                new Point(8192, 26112),
                new Point(8192, 27136),
                new Point(8192, 28160),
                new Point(8192, 29184),
                new Point(4160, 29184),
                new Point(5120, 29184),
                new Point(6144, 29184),
                new Point(7168, 29184),
        };

        var brute = new FastCollinearPoints(inputPoints);

        var expected = new LineSegment[]{
            new LineSegment(new Point(4096, 20992), new Point(8128, 20992)),
            new LineSegment(new Point(4096, 20992), new Point(4096, 25088)),
//            new LineSegment(new Point(5120, 20992), new Point(8128, 20992)), just remove this?
            new LineSegment(new Point(4096, 25088), new Point(8192, 25088)),
            new LineSegment(new Point(8192, 25088), new Point(8192, 29184)),
            new LineSegment(new Point(4160, 29184), new Point(8192, 29184)),
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