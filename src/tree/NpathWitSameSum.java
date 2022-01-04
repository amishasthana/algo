package tree;
/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

 */

import java.util.ArrayList;
import java.util.List;

public class NpathWitSameSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode() {};
    }

    int nPaths = 0;
    int sumToF;
    List<List<Integer>> listOfPath = null;
    public int pathSum(TreeNode root, int sum) {
        sumToF = sum;
        listOfPath = new ArrayList<>();
        List<Integer> cList = new ArrayList<>();
        listOfPath.add(cList);
        findAllList(root,cList);
       calculatePaths(listOfPath);
       return nPaths;

    }

    private void findAllList(TreeNode root,List<Integer> cList ) {
        if (root == null) return;
        cList.add(root.val);
        List<Integer> cloned_list = null;
        if ((root.right != null)  && (root.left != null)) {
            cloned_list
                    = new ArrayList<Integer>(cList);
            listOfPath.add(cloned_list);
            findAllList(root.right,cloned_list);
            findAllList(root.left, cList);
        } else {
            if (root.right != null) {
                findAllList(root.right, cList);
            } else {
                findAllList(root.left, cList);
            }
        }

    }

    private void calculatePaths(List<List<Integer>> listOfPath) {
          for(List<Integer> li : listOfPath) {
              nPaths += getPaths(li);
          }
    }

    private int getPaths(List<Integer> list) {
        int paths = 0; int sum = 0;
        for(int i = 0; i < list.size();i++) {
            sum = 0;
            for(int j = i; j < list.size();j++) {
                  sum += list.get(j);
                  if (sum == sumToF) paths++;
            }
        }
        return paths;
    }



    public static void main(String[] args) {
        int[] t = {10,5,-3,3,2,Integer.MIN_VALUE,11,3,-2,Integer.MIN_VALUE,1};
        NpathWitSameSum np = new NpathWitSameSum();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        System.out.print(np.pathSum(root,8));
    }

}
