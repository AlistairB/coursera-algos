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

            // as we skip the slope with itself, we minus a length off
            var slopesLength = points.length - i - 1;
            var slopes = new SlopedPoint[slopesLength];

            var slopesIndex = 0;

            for (int j = i + 1; j < points.length; j++) {
//                if (i == j)
//                    continue;

                var point2 = points[j];

                if (point2 == null)
                    throw new IllegalArgumentException();

                if (point.equals(point2))
                    throw new IllegalArgumentException();

                slopes[slopesIndex++] = new SlopedPoint(point2, point.slopeTo(point2));
            }

            Arrays.sort(slopes);

            var matchingSlopeCount = 0;
            SlopedPoint lastPoint = null;
            // loop slopes adding if 3 or more in a row are the same
            for (int k = 0; k < slopes.length; k++) {
                var currentPoint = slopes[k];

                if (matchingSlopeCount == 0) {
                    matchingSlopeCount++;
                }
                else if (currentPoint.slope == lastPoint.slope) {
                    matchingSlopeCount++;

                    // there is an edge case that on the last item it could be part of a segment
                    // but we will not loop again to go into the else case to create the segment
                    if (k == slopes.length - 1 && matchingSlopeCount >= 3) {
                        linkedListSegments.add(new LineSegment(point, currentPoint.point));
                        continue;
                    }
                } else if (matchingSlopeCount >= 3) {
                    linkedListSegments.add(new LineSegment(point, lastPoint.point));
                    matchingSlopeCount = 0;
                }

                lastPoint = currentPoint;
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