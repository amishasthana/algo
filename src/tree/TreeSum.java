package tree;
/*
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.



Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23



Note:

    The number of nodes in the tree is at most 10000.
    The final answer is guaranteed to be less than 2^31.


 */
public class TreeSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
    int sum = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
             sum(root,L,R);
             return sum;
    }

    public void sum(TreeNode root, int L, int R) {
        if (root == null) return;
        if ((root.val >= L) && (root.val <= R)) {
            sum += root.val;
            sum(root.left,L,R);
            sum(root.right,L,R);
        } else if (root.val >= R) {
            sum(root.left,L,R);
        } else if (root.val <= L)
        {
            sum(root.right,L,R);
        }
    }

    public static void main(String[] args) {
        /*
        Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

         */
        TreeNode tn = new TreeNode(10);
        tn.left = new   TreeNode(5);
        tn.left.left = new   TreeNode(3);
        tn.left.right = new   TreeNode(7);
        tn.right = new   TreeNode(15);
        tn.right.right = new   TreeNode(18);
        System.out.println(new TreeSum().rangeSumBST(tn,7,15));
    }

}
