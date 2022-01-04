package Goog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Forest {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            Set<TreeNode> forest = new HashSet<>();
            for(int i : to_delete) {
                Set<TreeNode> s = new HashSet<>();
                findChild(i,root,s);
                forest.addAll(s);
            }
            List<TreeNode> list = new ArrayList<>();
            Set<Integer> delSet = new HashSet<>();
            for(int ii : to_delete) delSet.add(ii);

            for(TreeNode t : forest) {
                if (t != null) {
                    if (delSet.contains(t.val)) continue;
                    list.add(t);
                }
            }

        return list;
    }

    private void findChild(int i,TreeNode root,Set<TreeNode> s) {
        if ( root == null) return;
        if (root.val == i) {
            s.add(root.left); s.add(root.right);
            return;
        }
        if (root.left != null) findChild(i,root.left,s);
        if (root.right != null) findChild(i,root.right,s);
    }


}
