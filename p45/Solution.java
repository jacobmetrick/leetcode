// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Your goal is to reach the last index in the minimum number of jumps.
//
// For example:
// Given array A = [2,3,1,1,4]
//
// The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
//
public class Solution {
    public static int jump(int[] A) {
        if (A == null || A.length == 0) {
            return Integer.MAX_VALUE;
        }
        // The numbers represent the fewest number of jumps to the end. The array is in reverse order of A.
        final int[] memos = new int[A.length];

        for (int i = 0; i < memos.length; i++) {
            final int jumpValue = internalMemo(memos, A[A.length - i - 1], i);
            memos[i] = jumpValue;
        }

        return memos[memos.length - 1];
    }

    private static int internalMemo(final int[] memos, final int jumpValue, final int absoluteDistance) {
        if (absoluteDistance == 0) {
            return 0;
        }

        long bestJumpValue = Integer.MAX_VALUE;
        for (int i = 1; i <= jumpValue; i++) {
            final int jumpingTo = absoluteDistance - i;
            if (absoluteDistance - i < 0) {
                break;
            }

            int testValue = 1 + memos[jumpingTo];
            if (testValue < 0) {
                testValue = Integer.MAX_VALUE;
            }
            if (testValue < bestJumpValue) {
                bestJumpValue = testValue;
            }
        }

        return (int)bestJumpValue;
    }

    public static void main(String[] args) {
        System.out.println("Answer is " + jump(new int[]{1, 10, 1, 1, 1, 1}));
    }
}
