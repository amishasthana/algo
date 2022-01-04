package algoplan;

import java.util.*;

/*
863. All Nodes Distance K in Binary Tree
Medium

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:

Input: root = [1], target = 1, k = 3
Output: []



Constraints:

    The number of nodes in the tree is in the range [1, 500].
    0 <= Node.val <= 500
    All the values Node.val are unique.
    target is the value of one of the nodes in the tree.
    0 <= k <= 1000


 */
public class KDistNodeInTree {
    public class TreeNode {
      int val;
     TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    TreeNode t2 = null;

    public TreeNode createTree() {
        //[3,5,1,6,2,0,8,null,null,7,4]
        //5
        //2
        TreeNode t1 = new TreeNode(3);
        t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(1);
        t3.left = new TreeNode(0);
        t3.right = new TreeNode(8);
        t1.left = t2; t1.right = t3;
        t2.left = new TreeNode(6);
        TreeNode t4 = new TreeNode(2);
        t2.right = t4;
        t4.left = new TreeNode(7);
        t4.right = new TreeNode(4);
        return t1;

    }

    Map<Integer, TreeNode> map = new HashMap<>();
    int tVal;
    boolean found = false;
    Set<Integer> V = new HashSet<>();

    public List<Integer> distanceK(TreeNode root,  int k) {
        return distanceK(root,t2,k);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
         tVal = target.val;
         find(null,root);//Basically get parent node from traget to root.
        //Then one can start scanning from target.

         List<Integer> ll = new ArrayList<>();
         bfs(target,ll,0,k);
        return  ll;
    }

    private void bfs(TreeNode root,List<Integer> ll,int d,int targetD) {
        if (( root == null) || (V.contains(root.val)) ) return;
        V.add(root.val);
        if (d == targetD ) {
            ll.add(root.val);
            return;
        }
        bfs(root.left,ll,d+1,targetD);
        bfs(root.right,ll,d+1,targetD);
        bfs(map.get(root.val),ll,d+1,targetD);
    }

    private void find(TreeNode parent,TreeNode root) {
        if ((root == null) || found) return;
        map.put(root.val,parent);
        if (root.val == tVal) { found = true; return; }//cut short
        find(root,root.left);
        find(root,root.right);
    }

    public static void main(String[] args) {
        KDistNodeInTree kd = new KDistNodeInTree();
        TreeNode root = kd.createTree();

        List<Integer> ll = kd.distanceK(root,2);
        System.out.println(ll.toString());
    }
}
