package collinear;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    // constructs the point (x, y)
    public Point(int x, int y) {

    }

    // draws this point
    public void draw() {

    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {

    }

    // string representation
    public String toString() {
        return "";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        return 0;
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return null;
    }
}
