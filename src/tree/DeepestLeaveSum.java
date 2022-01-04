package tree;
/*
1302. Deepest Leaves Sum

    User Accepted: 1172
    User Tried: 1198
    Total Accepted: 1186
    Total Submissions: 1364
    Difficulty: Medium

Given a binary tree, return the sum of values of its deepest leaves.



Example 1:

Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15



Constraints:

    The number of nodes in the tree is between 1 and 10^4.
    The value of nodes is between 1 and 100.

Java

 */

import java.util.HashMap;
import java.util.Map;

public class DeepestLeaveSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int maxDepth = 0;
    Map<Integer,Integer> sumAtDepth = new HashMap<>();
    public int deepestLeavesSum(TreeNode root) {
        calculateDeepestSum(root,0);
        return sumAtDepth.get(maxDepth);
    }

    private void calculateDeepestSum(TreeNode root,int depth) {
        if (root == null) return;
        if ((root.left != null) || (root.right != null)) {//Not leaf
            calculateDeepestSum(root.left,depth+1);
            calculateDeepestSum(root.right,depth+1);
        } else {//leaf
            Integer cDepth = sumAtDepth.get(depth);
            if (cDepth == null) cDepth = 0;
            cDepth += root.val;
            sumAtDepth.put(depth,cDepth);
            if (depth > maxDepth) maxDepth = depth;
        }
    }


}
