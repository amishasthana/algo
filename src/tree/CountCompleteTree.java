package tree;
/*
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6

Obviosuly go through each node and figure out the total., one can short circuit and as soon as one find a base level missing. Figure out the total.

Above one : 3 levels.
so total possible = 2(3) -1 = 7
Now the lowest level can possibly have 2(3-1) = 4.


 */
public class CountCompleteTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int maxLevel =0;
    int tMissing = 0;
    boolean maxLevelSet = false;

    public int countNodes(TreeNode root) {
        postOrderTraversal(root,0);
        return  (int)(Math.pow(2,maxLevel) - (tMissing + 1));
    }

    /*
         L / R  and then center
     */
    public void postOrderTraversal(TreeNode root,int level) {
        if (root == null) {
            if(!maxLevelSet  ) {
                maxLevelSet = true;
                maxLevel = level;
            } else {
                if(level != maxLevel) tMissing++;
            }
            return;
        }
        postOrderTraversal(root.left,level+1);
        postOrderTraversal(root.right,level+1);

    }

    public TreeNode init() {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(1);
        //root.right.left = new TreeNode(1);
        return root;
    }

    public static void main(String[] args) {
        CountCompleteTree rbst = new CountCompleteTree();
        System.out.println(rbst.countNodes(rbst.init()));
    }
}
