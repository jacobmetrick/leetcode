/*Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        final List<Boundary> boundaryList = new ArrayList<Boundary>(intervals.size() * 2);
        for (final Interval interval : intervals) {
            boundaryList.add(new Boundary(interval.start, BoundaryType.Start));
            boundaryList.add(new Boundary(interval.end, BoundaryType.End));
        }

        Collections.sort(boundaryList, new BoundaryComparator());

        final List<Interval> intervalList = new ArrayList<Interval>(intervals.size());
        int openBracketsLessClosed = 0;
        int currentIntervalStart = 0;
        for (final Boundary boundary : boundaryList) {
            if (openBracketsLessClosed == 0) {
                currentIntervalStart = boundary.value;
            }

            if (boundary.type == BoundaryType.Start) {
                openBracketsLessClosed++;
            } else {
                openBracketsLessClosed--;
            }

            if (openBracketsLessClosed == 0) {
                intervalList.add(new Interval(currentIntervalStart, boundary.value));
            }
        }
        return intervalList;
    }

    public enum BoundaryType { Start, End }

    private class BoundaryComparator implements Comparator<Boundary> {
        @Override
        public int compare(Boundary o1, Boundary o2) {
            final int integerCompare = Integer.compare(o1.value, o2.value);
            if (integerCompare == 0) {
                if (o1.type == BoundaryType.Start && o2.type == BoundaryType.End) {
                    return -1;
                } else if (o2.type == BoundaryType.Start && o1.type == BoundaryType.End) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return integerCompare;
            }
        }
    }

    private class Boundary {
        final public int value;
        final public BoundaryType type;
        public Boundary(int v, BoundaryType t) { value = v; type = t; }
    }

    private class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final List<Interval> inputList = new ArrayList<Interval>(4);
        inputList.add(solution.new Interval(1, 4));
        inputList.add(solution.new Interval(4, 5));
        for (final Interval interval : solution.merge(inputList)) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }
    }
}
