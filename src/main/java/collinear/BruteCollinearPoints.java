package collinear;

public class BruteCollinearPoints {

    LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        segments = new LineSegment[];

        for (int i = 0; i < points.length; i++) {
            var point = points[i];

            for (int j = 1; j < points.length; j++) {
                var point2 = points[j];

                var startingSlope = point.slopeTo(point2);

                for (int k = 2; k < points.length; k++) {
                    var point3 = points[k];

                    if (point3.slopeTo(point2) != startingSlope) {
                        continue;
                    }

                    for (int l = 3; l < points.length; l++) {
                        var point4 = points[l];

                        if (point2.slopeTo(point4) == startingSlope) {
                            Point biggestPoint;
                            Point smallestPoint;

                            if (point.compareTo(point2) > 0) {
                                biggestPoint = point;
                                smallestPoint = point2;
                            } else {
                                biggestPoint = point2;
                                smallestPoint = point;
                            }

                            if (biggestPoint.compareTo(point3) > 0) {

                            }


                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return 0;
    }

    // the line segments
    public LineSegment[] segments() {
        return null;
    }
}
