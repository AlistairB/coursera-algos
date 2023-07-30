package collinear;

import java.util.Arrays;
import java.util.LinkedList;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0)
            throw new IllegalArgumentException();

        var linkedListSegments = new LinkedList<LineSegment>();

        for (int i = 0; i < points.length; i++) {
            var point = points[i];

            if (point == null)
                throw new IllegalArgumentException();

            for (int j = i + 1; j < points.length; j++) {
                var point2 = points[j];

                if (point2 == null)
                    throw new IllegalArgumentException();

                if (point.equals(point2))
                    throw new IllegalArgumentException();

                var startingSlope = point.slopeTo(point2);

                for (int k = j + 1; k < points.length; k++) {
                    var point3 = points[k];

                    if (point3 == null)
                        throw new IllegalArgumentException();

                    if (point.slopeTo(point3) != startingSlope) {
                        continue;
                    }

                    for (int z = k + 1; z < points.length; z++) {
                        var point4 = points[z];

                        if (point4 == null)
                            throw new IllegalArgumentException();

                        if (point.slopeTo(point4) == startingSlope) {
                            var linePoints = new Point[]{point, point2, point3, point4};

                            Arrays.sort(linePoints);

                            linkedListSegments.add(new LineSegment(linePoints[0], linePoints[3]));
                        }
                    }
                }
            }
        }

        var count = linkedListSegments.size();
        segments = new LineSegment[count];

        for (int k = 0; k < count; k++) {
            segments[k] = linkedListSegments.removeFirst();
        }
    }

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
