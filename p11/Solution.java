/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

import java.util.*;

public class Solution {
    public static int maxArea(int[] height) {
        final List<ValueIndexPair> increasingHeight = new ArrayList<ValueIndexPair>();
        final SortedMap<Integer, Integer> remainingToCompare = new TreeMap<Integer, Integer>();

        // Populate data structures
        for (int i = 0; i < height.length; i++) {
            final ValueIndexPair pair = new ValueIndexPair(i, height[i]);
            increasingHeight.add(pair);
            remainingToCompare.put(i, height[i]);
        }
        Collections.sort(increasingHeight, new ValueIndexPairComparator());

        // Do comparisons between the lowest element and the left and rightmost remaining heights. If there's a tie
        // for lowest element, then use the lowest leftmost (vs. rightmost remaining) and lowest rightmost
        // (vs. leftmost remaining)
        int bestArea = 0;
        for (int i = 0; i < increasingHeight.size(); i++) {
            final List<Integer> usedIndicies = new ArrayList<Integer>();
            final ValueIndexPair firstPair = increasingHeight.get(i);
            int leftIndex = firstPair.index;
            int rightIndex = firstPair.index;
            final int comparedHeight = firstPair.value;
            //System.out.println("Lowest remaining... index: " + firstPair.index + " height: " + firstPair.value);
            usedIndicies.add(firstPair.index);
            while (i + 1 < increasingHeight.size()) {
                ValueIndexPair nextPair = increasingHeight.get(i + 1);
                if (nextPair.value != comparedHeight) {
                    break;
                }
                //System.out.println("Also lowest remaining... index: " + nextPair.index + " height: " + nextPair.value);
                final int index = nextPair.index;
                usedIndicies.add(index);
                if (index < leftIndex) {
                    leftIndex = index;
                } if (rightIndex < index) {
                    rightIndex = index;
                }
                i++;
            }

            // area calculations and comparisons
            final int leftmostRemainingIndex = remainingToCompare.firstKey();
            final int rightmostRemainingIndex = remainingToCompare.lastKey();

            final int leftmostRemainingComparisonDistance = rightIndex - leftmostRemainingIndex;
            final int rightmostRemainingComparisonDistance = rightmostRemainingIndex - leftIndex;

            final int leftmostRemainingArea = leftmostRemainingComparisonDistance * comparedHeight;
            final int rightmostRemainingArea = rightmostRemainingComparisonDistance * comparedHeight;

            //System.out.println("Comparing index " + rightIndex + " at height " + comparedHeight + " to leftmost remaining index " + leftmostRemainingIndex + " (height is " + remainingToCompare.get(leftmostRemainingIndex) + ") and the area calculated was " + leftmostRemainingArea);
            //System.out.println("Comparing index " + leftIndex + " at height " + comparedHeight + " to rightmost remaining index " + rightmostRemainingIndex + " (height is " + remainingToCompare.get(rightmostRemainingIndex) + ") and the area calculated was " + rightmostRemainingArea);

            if (leftmostRemainingArea > bestArea) {
                bestArea = leftmostRemainingArea;
            } if (rightmostRemainingArea > bestArea) {
                bestArea = rightmostRemainingArea;
            }

            // Remove relevant indicies from the remainingToCompare map
            for (final Integer indexToRemove : usedIndicies) {
                remainingToCompare.remove(indexToRemove);
            }
        }
        return bestArea;
    }

    private static class ValueIndexPair {
        int index;
        int value;

        ValueIndexPair(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
    }

    private static class ValueIndexPairComparator implements Comparator<ValueIndexPair> {
        @Override
        public int compare(ValueIndexPair o1, ValueIndexPair o2) {
            return Integer.compare(o1.value, o2.value);
        }
    }

    public static void main(String[] args) {
        maxArea(new int[] {3, 4, 5, 2, 3, 6});
    }
}
