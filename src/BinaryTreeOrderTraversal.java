import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
Binary Tree Level Order Traversal
Medium

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

 */
public class BinaryTreeOrderTraversal {

    /*
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> listLevelTraversal = new ArrayList<>();
        Queue<TreeNode> fQue = new ArrayDeque<>();
        int cLevel = 0; int sLevel = 0;
        fQue.add(root);
        cLevel++;
        List<Integer> cList = new ArrayList<>();
        listLevelTraversal.add(cList);
        while(!fQue.isEmpty()) {
            if(cLevel == 0) {
                cLevel = sLevel;
                sLevel = 0;
                cList = new ArrayList<>();
                listLevelTraversal.add(cList);
            }
            TreeNode cNode = fQue.poll();
            if (cNode != null) {
                cList.add(cNode.v);
                cLevel--;
                fQue.add(cNode.left);
                sLevel++;
                fQue.add(cNode.right);
                sLevel++;
            }
        }
        return listLevelTraversal;
    }
    */

    class TreeNode {
        int v;
        TreeNode left;
        TreeNode right;
        public TreeNode(int v) {
            this.v = v;
        }
    }

    private static TreeNode NULL_NODE = null;

    /*
    Each level has 2^n elements. n = 0 etc....
    Now one does need to capture if there are any elements in next layer. If all nulls, one discard that layer and get out.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        NULL_NODE  = new TreeNode(Integer.MIN_VALUE);
        List<List<Integer>> listLevelTraversal = new ArrayList<>();
        Queue<TreeNode> fQue = new ArrayDeque<>();
        int layer = 0;
        double cLevel = Math.pow(2,layer);
        fQue.add(root);

        boolean allNullInNextLevel = true;
        List<Integer> cList = new ArrayList<>();
        listLevelTraversal.add(cList);
        while ( !fQue.isEmpty()) {
            TreeNode cNode = fQue.poll();
            cLevel--;
            if (cNode != null) {
                if(cNode == NULL_NODE) {
                    cList.add(null);
                } else {
                    cList.add(cNode.v);
                }

                if(cNode.left != null) {
                    fQue.add(cNode.left);
                } else {
                    fQue.add(NULL_NODE);
                }
                if(cNode.right != null) {
                    fQue.add(cNode.right );
                } else {
                    fQue.add(NULL_NODE);
                }

                if ((cNode.left != null) || (cNode.right != null)) {
                    allNullInNextLevel = false;
                }

            } else {
                cList.add(null);
            }
            if (cLevel == 0) {
                if (allNullInNextLevel) break;
                cLevel = Math.pow(2,++layer);
                cList = new ArrayList<>();
                listLevelTraversal.add(cList);
                allNullInNextLevel = true;
            }


        }
        return listLevelTraversal;
    }

    public TreeNode createNode(int v) {
        return new TreeNode(v);
    }

    public static void main(String[] args) {
        BinaryTreeOrderTraversal btt = new BinaryTreeOrderTraversal();
        TreeNode root =  btt.createNode(3);
        root.left = btt.createNode(9);
        root.right =  btt.createNode(20);
        root.right.left = btt.createNode(15);
        root.right.right = btt.createNode(7);
        List<List<Integer>> listTraversal = btt.levelOrder(root);
        for(List<Integer> list : listTraversal ) {
            list.forEach(System.out::print);
            System.out.println();
        }
    }



}
