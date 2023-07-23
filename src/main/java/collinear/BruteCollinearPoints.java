package collinear;

import java.util.LinkedList;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        var linkedListSegments = new LinkedList<LineSegment>();

        for (int i = 0; i < points.length; i++) {
            var point = points[i];

            for (int j = i + 1; j < points.length; j++) {
                var point2 = points[j];

                var startingSlope = point.slopeTo(point2);

                for (int k = j + 1; k < points.length; k++) {
                    var point3 = points[k];

                    if (point.slopeTo(point3) != startingSlope) {
                        continue;
                    }

                    for (int l = k + 1; l < points.length; l++) {
                        var point4 = points[l];

                        if (point.slopeTo(point4) == startingSlope) {
                            var linePoints = new Point[]{point2, point3, point4};

                            Point biggestPoint = point;
                            Point smallestPoint = point;

                            for (int m = 0; m < 3; m++) {
                                var toCompare = linePoints[m];

                                if (toCompare.compareTo(biggestPoint) > 0)
                                    biggestPoint = toCompare;

                                if (toCompare.compareTo(smallestPoint) < 0)
                                    smallestPoint = toCompare;
                            }

                            linkedListSegments.add(new LineSegment(smallestPoint, biggestPoint));
                        }
                    }
                }
            }
        }

        segments = new LineSegment[linkedListSegments.size()];

        for (int k = 0; k < linkedListSegments.size(); k++) {
            segments[k] = linkedListSegments.removeFirst();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }
}
