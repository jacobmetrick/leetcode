/* Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 */

public class Solution {
    public static int findMin(int[] num) {
        int leftIndex = 0;
        int rightIndex = num.length - 1;
        int middle = ((rightIndex - leftIndex) / 2) + leftIndex;
        while (rightIndex - leftIndex > 2) {
            System.out.println("left num is " + num[leftIndex] + " and right num is " + num[rightIndex]);
            if (num[leftIndex] < num[middle]) {
                if (num[middle] < num[rightIndex]) {
                    return num[leftIndex];
                } else {
                    leftIndex = middle;
                }
            } else {
                rightIndex = middle;
            }
            middle = ((rightIndex - leftIndex) / 2) + leftIndex;
        }
        System.out.println("left num is " + num[leftIndex] + " and middle num is " + num[middle] + " and right num is " + num[rightIndex]);
        if (num[leftIndex] < num[rightIndex] && num[leftIndex] < num[middle]) {
            return num[leftIndex];
        } else if (num[middle] < num[rightIndex]) {
            return num[middle];
        } else {
            return num[rightIndex];
        }
    }

    public static void main(String[] args) {
        System.out.println("answer is " + findMin(new int[]{3, 4, 5, 1, 2}));
    }
}
