/* Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int maxPoints(Point[] points) {
        if (points.length == 0) {
            return 0;
        }

        int maxSeen = 1;
        for (int i = 0; i < points.length; i++) {
            final Map<Line, Integer> lineMap = new HashMap<Line, Integer>();
            for (int j = (i + 1); j < points.length; j++) {
                final Line line = getLineFromPoints(points[i], points[j]);
                if (!lineMap.containsKey(line)) {
                    lineMap.put(line, 2); // for each line we have found thus far, there are at least the first two
                    // points
                } else {
                    lineMap.put(line, (lineMap.get(line) + 1));
                }
            }

            for (final Integer value : lineMap.values()) {
                if (maxSeen < value) {
                    maxSeen = value;
                }
            }
        }

        return maxSeen;
    }

    private static Line getLineFromPoints(final Point p1, final Point p2) {
        if (p2.x - p1.x == 0) {
            final Line line = new Line(0, p2.x);
            line.slopeUndefined = true;
            return line;
        } else {
            final double m = (p2.y - p1.y) / (p2.x - p1.x); // slope
            final double b = p1.y - (m * p1.x); // intercept
            return new Line(m, b);
        }
    }

    private static class Line {
        boolean slopeUndefined = false;
        double m;
        double b;
        Line(double d1, double d2) { m = d1; b = d2; }

        @Override
        public int hashCode() {
            return ((Double)this.m).hashCode() + ((Double)this.b).hashCode() +
                ((Boolean)this.slopeUndefined).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Line) {
                final Line line2 = (Line)obj;
                return this.slopeUndefined == line2.slopeUndefined && this.m == line2.m && this.b == line2.b;
            }
            return false;
        }
    }

    private static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    public static void main(String[] args) {
        System.out.println("Result is " + maxPoints(new Point[] { new Point (0, 0), new Point (1, 1), new Point (2, 2), new Point (3, 3), new Point (2, 0), new Point (0, 2) }));
    }
}
