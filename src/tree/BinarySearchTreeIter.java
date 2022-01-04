package tree;
import java.util.Stack;
/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.



Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false



Note:

    next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

 */

public class BinarySearchTreeIter {
    public class TreeNode {
      int val;
     TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    Stack<TreeNode> left = new Stack<>();
    Stack<TreeNode> right = new Stack<>();

    public BinarySearchTreeIter(TreeNode root) {
        populateLeftStack(root);
    }

    private void populateLeftStack(TreeNode root) {
        if ( root == null) return;
        left.push(root);
        while ( !left.isEmpty() ) {
            TreeNode cNode = left.pop();
            if (cNode.left != null) {
                left.push(cNode.left);
            }
            right.push(cNode);
        }//End of while
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cNode = right.pop();
        populateLeftStack(cNode.right);
        return cNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (right.size() > 0);
    }
}
