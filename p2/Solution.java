// There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
public class Solution {

    public double findMedianSortedArrays(int A[], int B[]) {
        final int totalLength = A.length + B.length;
        final int median = totalLength / 2 - 1;
        if ((totalLength % 2) == 0) {
            final int median2 = totalLength / 2;
            return ((double)guess(A, 0, A.length, B, 0, B.length, median) +
                    (double)guess(A, 0, A.length, B, 0, B.length, median2)) / 2;
        } else {
            return (double)guess(A, 0, A.length, B, 0, B.length, median);
        }
    }

    private int guess(final int A[], final int aBegin, final int aEnd, final int B[], final int bBegin, final int bEnd,
            final int goal) {
        System.out.println("A is " + A + " starting at " + aBegin + " and ending at " + aEnd);
        System.out.println("B is " + B + " starting at " + bBegin + " and ending at " + bEnd);
        final int guessPos = midpoint(aBegin, aEnd - 1);
        final int guessVal = A[guessPos];
        System.out.println("guessPos " + guessPos + " guessVal " + guessVal);
        final int otherArrayGuessPos = findValInArray(guessVal, B, bBegin, bEnd - 1);
        final int totalGuessPos = otherArrayGuessPos + guessPos - 1;
        System.out.println("totalGuessPos " + totalGuessPos + " goal " + goal);
        // throw away everything in the array that was less than the goal, including the guess. Also take next guess
        // from the other array
        if (totalGuessPos < goal) {
            return guess(B, bBegin, bEnd, A, guessPos, aEnd, goal);
        } else if (totalGuessPos > goal) {
            return guess(B, bBegin, bEnd, A, aBegin, guessPos, goal);
        } else { // found it, return the value!
            return guessVal;
        }
    }

    private int findValInArray(final int val, final int arrayAgainst[], int begin, int end) {
        while (arrayAgainst[begin] != arrayAgainst[end]) {
            System.out.println("begin: " + arrayAgainst[begin] + " at " + begin + " and end: " + arrayAgainst[end] + " at " + end);
            final int locInArrayAgainst = midpoint(begin, end);
            final int valInArrayAgainst = arrayAgainst[locInArrayAgainst];
            System.out.println("trying to place " + val + " and looking at " + valInArrayAgainst);
            if (val < valInArrayAgainst) {
                end = locInArrayAgainst;
            } else {
                begin = locInArrayAgainst + 1;
            }
        }
        final int againstPos = (val < arrayAgainst[begin]) ? begin : end;
        System.out.println("returning position " + againstPos);
        return againstPos;
    }

    private int midpoint(final int begin, final int end) {
        return (end + begin) / 2;
    }

    public static void main(String[] args) {
        System.out.println("median: " + new Solution().findMedianSortedArrays(new int[] {3, 4, 9, 12}, new int[] {6, 14, 21, 22, 23}));
    }
}
