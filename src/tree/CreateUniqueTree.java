package tree;
/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


 */

import java.util.ArrayList;
import java.util.List;

public class CreateUniqueTree {
    class TreeNode {
        int v;
        TreeNode left;
        TreeNode right;

        public TreeNode(int vv) {
            v = vv;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<TreeNode>();
        return createTrees(0, n-1);
    }

    private List<TreeNode> createTrees(int start, int end){
        List<TreeNode> list = new ArrayList<>();
        if(start > end){
            list.add(null);
            return list;
        }
        /* 0 -5
           Say i = 2.
           0-1
           3-5
         */
        for(int i=start; i<=end; i++){
            List<TreeNode> leftTree = createTrees(start, i-1);
            List<TreeNode> rightTree = createTrees(i+1, end);
            for(int j=0; j<leftTree.size(); j++){
                for(int k=0; k<rightTree.size(); k++){
                    TreeNode root = new TreeNode(i+1);
                    root.left = leftTree.get(j);
                    root.right = rightTree.get(k);
                    list.add(root);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        CreateUniqueTree cut = new CreateUniqueTree();
        List<TreeNode>  trees = cut.generateTrees(3);
        System.out.println(trees.toArray());
    }
}
