/* Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
] */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        final List<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        final List<Integer> sumToHere = new LinkedList<Integer>();
        final List<List<Integer>> integersToHere = new LinkedList<List<Integer>>();
        final List<List<Integer>> answer = new ArrayList<List<Integer>>();

        if (root == null) {
            return answer;
        }

        addChildToStructures(nodeQueue, sumToHere, integersToHere, 0, root, new ArrayList<Integer>());

        while(!nodeQueue.isEmpty()) {
            final TreeNode node = nodeQueue.remove(0);
            int currentSum = sumToHere.remove(0);
            final List<Integer> ints = integersToHere.remove(0);

            if (node.left == null && node.right == null) {
                if (sum == currentSum) {
                    answer.add(ints);
                }
            } else {
                if (node.left != null) {
                    addChildToStructures(nodeQueue, sumToHere, integersToHere, currentSum, node.left, ints);
                }
                if (node.right != null) {
                    addChildToStructures(nodeQueue, sumToHere, integersToHere, currentSum, node.right, ints);
                }
            }
        }

        return answer;
    }

    private static void addChildToStructures(final List<TreeNode> nodeQueue, final List<Integer> sumToHere,
            final List<List<Integer>> integersToHere, final int currentSum, final TreeNode node,
            final List<Integer> ints) {
        final int val = node.val;
        final int sum = currentSum + val;
        final List<Integer> newInts = new ArrayList<Integer>(ints);
        newInts.add(val);
        nodeQueue.add(node);
        sumToHere.add(sum);
        integersToHere.add(newInts);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(5);
        final TreeNode oneone = new TreeNode(4);
        root.left = oneone;
        final TreeNode onetwo = new TreeNode(8);
        root.right = onetwo;
        final TreeNode twoone = new TreeNode(11);
        oneone.left = twoone;
        final TreeNode twotwo = new TreeNode(13);
        onetwo.left = twotwo;
        final TreeNode twothree = new TreeNode(4);
        onetwo.right = twothree;
        final TreeNode threeone = new TreeNode(7);
        twoone.left = threeone;
        final TreeNode threetwo = new TreeNode(2);
        twoone.right = threetwo;
        final TreeNode threethree = new TreeNode(5);
        twothree.left = threethree;
        final TreeNode threefour = new TreeNode(1);
        twothree.right = threefour;
        System.out.println(Solution.pathSum(root, 22));
    }
}
