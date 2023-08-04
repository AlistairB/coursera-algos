package collinear;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    private class SlopedPoint implements Comparable {
        Point point;
        double slope;

        SlopedPoint(Point point, double slope) {
            this.point = point;
            this.slope = slope;
        }

        @Override
        public int compareTo(Object o) {
            var slopePoint = (SlopedPoint) o;

            return Double.compare(this.slope, slopePoint.slope);

        }
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || points.length == 0)
            throw new IllegalArgumentException();




        var linkedListSegments = new LinkedList<LineSegment>();

        for (int i = 0; i < points.length; i++) {
            var point = points[i];

            var slopes = new SlopedPoint[points.length - 1];

            for (int j = i + 1; j < points.length; j++) {
                var point2 = points[j];

                if (point2 == null)
                    throw new IllegalArgumentException();

                if (point.equals(point2))
                    throw new IllegalArgumentException();

                slopes[j] = new SlopedPoint(point2, point.slopeTo(point2));
            }

            Arrays.sort(slopes);

            var matchingSlopeCount = 0;

            // loop slopes adding if 4 or more in a row are the same
            for (int k = 0; k < slopes.length; k++) {
                
            }
        }

        var count = linkedListSegments.size();
        segments = new LineSegment[count];

        for (int k = 0; k < count; k++) {
            segments[k] = linkedListSegments.removeFirst();
        }
    }

//    private LineSegment[] getLineSegments(SlopedPoint[] slopes) {
//        var lineSegments :=
//
//        // loop slopes adding if 4 or more in a row are the same
//        for (int k = 0; k < slopes.length; k++) {
//
//        }
//    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        var newSegments = new LineSegment[segments.length];

        System.arraycopy(segments, 0, newSegments, 0, segments.length);

        return newSegments;
    }
}