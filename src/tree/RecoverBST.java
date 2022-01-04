package tree;

/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2

Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3

Follow up:

    A solution using O(n) space is pretty straight forward.
    Could you devise a constant space solution?

   9.10 PM
   a) O(n):
      From a array figure out the tree. Go through array and check if its valid.
      Figure out which element, and then replace it with each and ensure its valid.
      the question I have is it seem validation has to be done for the whole tree.


 */
public class RecoverBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private TreeNode failedNode = null;
    private TreeNode replacedNode = null;
    private TreeNode prevNode = null;



    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);
        if (failedNode != null) {
            int temp = failedNode.val;
            failedNode.val = replacedNode.val;
            replacedNode.val = temp;
        }

        return;
    }

    public void inOrderTraverse(TreeNode root) {
        if (root == null)return;
        inOrderTraverse(root.left);
        // Do the business.
        if ((failedNode == null) && (prevNode != null) && (prevNode.val >= root.val)) {//Prev node has issue.
            failedNode = prevNode;
        }

        if ((failedNode != null) && (prevNode != null) && (prevNode.val >= root.val)) {//Root has potentially issue.
            replacedNode = root;
        }
        prevNode = root;
        //End of business
        inOrderTraverse(root.right);
    }


    public TreeNode init() {
        /*
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        */
        /*
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        return root;
        */
        //[3,null,2,null,1]
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(1);
        return root;
    }

    public static void main(String[] args) {
        RecoverBST rbst = new RecoverBST();
        rbst.recoverTree(rbst.init());
    }


}
