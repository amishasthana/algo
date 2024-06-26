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
public class RangeSumOfBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int sum = 0;
    int finalSum = 0;
    boolean fMatched = false;
    boolean sMatched = false;
    int L; int R;


    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        this.L = L; this.R = R;
        partialInOrderTraversal(root);
        return sum;
    }

    private void partialInOrderTraversal(TreeNode root) {
        if ((root == null) ||( fMatched && sMatched)) return;
        partialInOrderTraversal(root.left);
        if (!fMatched) {
            fMatched = (root.val == L);
        } else {
            sMatched  = (root.val == R);
        }
        if(fMatched || sMatched) {
            sum += root.val;
        }
        partialInOrderTraversal(root.right);
    }

}
