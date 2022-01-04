package Goog;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
Set<Integer> to_delete_set;
    List<TreeNode> res;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        to_delete_set = new HashSet<>();
        res = new ArrayList<>();
        for (int i : to_delete)
            to_delete_set.add(i);
        helper(root, true);
        return res;
    }

    private TreeNode helper(TreeNode node, boolean is_root) {
        if (node == null) return null;
        boolean deleted = to_delete_set.contains(node.val);
        if (is_root && !deleted) res.add(node);
        node.left = helper(node.left, deleted);
        node.right =  helper(node.right, deleted);
        return deleted ? null : node;
    }
 */
public class Forest2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> R = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for(int i : to_delete) s.add(i);
        fillTreeNode(R,root,s,null,true);
        return R;
    }

    private void fillTreeNode(List<TreeNode> R,TreeNode root,Set<Integer> s,TreeNode pRoot,boolean left) {
        if (root == null) return;
        if ( s.contains(root.val) ) {
            fillTreeNode(R,root.left,s,null,true);
            fillTreeNode(R,root.right,s,null,false);
            if (pRoot != null) {
                if (left){
                    pRoot.left = null;
                } else {
                    pRoot.right = null;
                }
            }
        } else {
            if (pRoot == null) R.add(root);
            fillTreeNode(R,root.left,s,root,true);
            fillTreeNode(R,root.right,s,root,false);

        }

    }//end of method.
}
