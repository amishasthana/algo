package tree;
/*
 Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree

          1
         / \
        2   3
       / \
      4   5

Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return postOrderTraversal(root);
    }

    private int globalMax = 0;

    private int postOrderTraversal(TreeNode root) {
        if ( root == null) return 0;
        int lMax = postOrderTraversal(root.left);
        int rMax = postOrderTraversal(root.right);
        int total = lMax+rMax;
        if (total >= globalMax) {
            globalMax = total;
        }
        return Math.max(lMax,rMax)+1;
    }

    public TreeNode init() {
        /*
        TreeNode root = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode fifteen = new TreeNode(15);
        root.left = five; root.right = fifteen;
        TreeNode six = new TreeNode(6);
        TreeNode twenty = new TreeNode(20);
        fifteen.left = six;
        fifteen.right = twenty;
        */
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        return root;
    }

    public static void main(String[] args) {
        DiameterTree vbs = new DiameterTree();
        TreeNode tn = vbs.init();
        System.out.println(vbs.diameterOfBinaryTree(tn));
    }

}
