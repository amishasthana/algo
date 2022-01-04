package tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
515. Find Largest Value in Each Tree Row
Medium

You need to find the largest value in each row of a binary tree.

Example:

Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]

 */
public class LargestValueInRow {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> lis = new LinkedList<>();
        if ( ( root == null) ) return lis;
        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new  ArrayDeque<>();
        boolean fq1Used = true;
        q1.add(root);
        Queue<TreeNode> qInp = q1;
        Queue<TreeNode> qOut = q2;

        while ( !qInp.isEmpty() ) {
            int max = Integer.MIN_VALUE;
            while ( !qInp.isEmpty() ) {
                TreeNode cNode = qInp.poll();
                if (cNode.left != null) qOut.add(cNode.left);
                if (cNode.right != null) qOut.add(cNode.right);
                max = Math.max(max,cNode.val);
            }//inner while
            lis.add(max);
            if ( fq1Used ) {
                qInp = q1;
                qOut = q2;
            } else {
                qInp = q2;
                qOut = q1;
            }
            fq1Used = !fq1Used;

        }//end of while

        return lis;

    }
}
