package Contest;

import java.util.HashMap;
import java.util.Map;

/*
1530. Number of Good Leaf Nodes Pairs

    User Accepted: 2410
    User Tried: 3383
    Total Accepted: 2492
    Total Submissions: 6456
    Difficulty: Medium

Given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.



Example 1:

Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:

Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:

Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].

Example 4:

Input: root = [100], distance = 1
Output: 0

Example 5:

Input: root = [1,1,1], distance = 2
Output: 1



Constraints:

    The number of nodes in the tree is in the range [1, 2^10].
    Each node's value is between [1, 100].
    1 <= distance <= 10


 */
public class GoodPair {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    int gPair = 0; int dis = 0;
    Map<Integer,Integer> EMPTY_MAP = new HashMap<>();
    int[] rA = null;
    public int countPairs(TreeNode root, int distance) {
         dis = distance;
         rA = new int[dis+1];
         getPotLeaf(root);
         return gPair;
    }

    Map<Integer,Integer> getPotLeaf(TreeNode cNode) {
        if (cNode == null) return EMPTY_MAP;
        Map<Integer,Integer> lNodes = getPotLeaf(cNode.left);
        Map<Integer,Integer> rNodes = getPotLeaf(cNode.right);
        if ((lNodes.size() == 0) && (rNodes.size() == 0) ) {
            Map<Integer,Integer> pMap = new HashMap<>();
            pMap.put(1, 1);
            return pMap;
        }

        popArray(rA,rNodes);
        for(Integer lKey : lNodes.keySet()) {
            gPair += lNodes.getOrDefault(lKey,0)*rA[dis-lKey];
        }
        Map<Integer,Integer> nMap = new HashMap<>();
        nMap.put(0,0);
        for(int i = 1; i < dis-1;i++) {
            int total = lNodes.getOrDefault(i,0) + rNodes.getOrDefault(i,0);
            nMap.put(i+1,total);
        }

        return nMap;
    }

    private void popArray(int[] a,Map<Integer,Integer> mm ) {
        for(int i = 1; i <= dis; i++) {
            a[i] = mm.getOrDefault(i,0)+a[i-1];
        }
    }

    public TreeNode createTree() {
        TreeNode six = new GoodPair.TreeNode(6,null,null);
        TreeNode seven = new GoodPair.TreeNode(7,null,null);
        TreeNode five = new GoodPair.TreeNode(5,null,null);
        TreeNode four = new GoodPair.TreeNode(4,null,null);
        TreeNode two = new GoodPair.TreeNode(2,four,five);
        TreeNode three = new GoodPair.TreeNode(3,six,seven);
        TreeNode one = new GoodPair.TreeNode(1,two,three);
        return one;
    }

    public static void main(String[] args) {
        GoodPair gp = new GoodPair();
        TreeNode root = gp.createTree();
        System.out.println(gp.countPairs(root,3));
        /* 1,2,3,4,5,6,7 */



    }

}
