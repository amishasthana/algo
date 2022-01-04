package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
199. Binary Tree Right Side View
Medium

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

 */
public class BinTreeRightSide {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> q1 = new ArrayDeque<>();
        Deque<TreeNode> q2 = new ArrayDeque<>();
        boolean fQ = true;
        List<Integer> lis = new LinkedList<>();
        if ( root == null) return lis;
        q1.add(root);

        Deque<TreeNode>  qInput = null;
        Deque<TreeNode>  qOutput = null;

        while (!q1.isEmpty() || !q2.isEmpty() ) {
            if ( fQ) {
                qInput = q1;
                qOutput = q2;
            } else {
                qInput = q2;
                qOutput = q1;
            }
            fQ = !fQ;
            if (qInput.peekLast() != null) {
                lis.add(qInput.peekLast().val);
            }
            while ( !qInput.isEmpty() ) {
                TreeNode cNode = qInput.poll();
                addNode(cNode.left,qOutput);
                addNode(cNode.right,qOutput);
            }// End of while.

        }// End of while.
        return lis;

    }//End of method

    private void addNode(TreeNode t, Deque<TreeNode> q) {
        if ( t != null) q.add(t);
    }
}
