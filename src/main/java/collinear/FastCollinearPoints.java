package collinear;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    private static class SlopedPoint implements Comparable<SlopedPoint> {
        Point point;
        double slope;

        SlopedPoint(Point point, double slope) {
            this.point = point;
            this.slope = slope;
        }

        @Override
        public int compareTo(SlopedPoint slopedPoint) {
            if (this.slope > slopedPoint.slope) return 1;
            if (this.slope < slopedPoint.slope) return -1;

            return this.point.compareTo(slopedPoint.point);
        }
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] inputPoints) {
        if (inputPoints == null || inputPoints.length == 0)
            throw new IllegalArgumentException();

        var points = Arrays.copyOf(inputPoints, inputPoints.length);

        var linkedListSegments = new LinkedList<LineSegment>();
        var usedSlopeLength = (int) Math.pow(points.length, 2);
        var usedSlopes = new SlopedPoint[usedSlopeLength];
        var usedSlopeCount = 0;

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            var point = points[i];

            if (point == null)
                throw new IllegalArgumentException();

            // as we skip the slope with itself, we minus a length off
            var slopesLength = points.length - i - 1;
            var slopes = new SlopedPoint[slopesLength];
            var slopesIndex = 0;

            for (int j = i + 1; j < points.length; j++) {

                var point2 = points[j];

                if (point2 == null)
                    throw new IllegalArgumentException();

                if (point.equals(point2))
                    throw new IllegalArgumentException();

                var slopeFromPoint = point.slopeTo(point2);

                slopes[slopesIndex++] = new SlopedPoint(point2, slopeFromPoint);
            }

            if (slopesIndex == 0)
                continue;

            Arrays.sort(slopes, new Comparator<SlopedPoint>() {
                @Override
                public int compare(SlopedPoint o1, SlopedPoint o2) {
                    if (o1 == null && o2 == null) {
                        return 0;
                    }
                    if (o1 == null) {
                        return 1;
                    }
                    if (o2 == null) {
                        return -1;
                    }
                    return o1.compareTo(o2);
                }});

            var matchingSlopeCount = 0;
            SlopedPoint lastPoint = null;
            // loop slopes adding if 3 or more in a row are the same
            for (int k = 0; k < slopesIndex; k++) {
                var currentPoint = slopes[k];

                if (matchingSlopeCount == 0) {
                    matchingSlopeCount++;
                }
                else if (currentPoint.slope == lastPoint.slope) {
                    matchingSlopeCount++;

                    // there is an edge case that on the last item it could be part of a segment
                    // but we will not loop again to go into the else case to create the segment
                    if (k == slopesIndex - 1 && matchingSlopeCount >= 3) {
                        if (!slopedPointUsed(usedSlopes, currentPoint)) {
                            usedSlopes[usedSlopeCount++] = currentPoint;
                            linkedListSegments.add(new LineSegment(point, currentPoint.point));
                        }
                    }
                } else if (matchingSlopeCount >= 3) {
                    if (!slopedPointUsed(usedSlopes, lastPoint)) {
                        usedSlopes[usedSlopeCount++] = lastPoint;
                        linkedListSegments.add(new LineSegment(point, lastPoint.point));
                    }

                    // we start at 1 in this case as we count the current point as a new slope start
                    matchingSlopeCount = 1;
                } else {
                    matchingSlopeCount = 1;
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

    private boolean slopedPointUsed(SlopedPoint[] usedSlopePoints, SlopedPoint slopedPoint) {
        for (int g = 0; g < usedSlopePoints.length; g++) {
            var usedSlope = usedSlopePoints[g];

            if (usedSlope == null) {
                return false;
            }

            if (usedSlopePoints[g].compareTo(slopedPoint) == 0) {
                return true;
            }
        }

        return false;
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