package tree;


/*
2096. Step-By-Step Directions From a Binary Tree Node to Another
Medium

You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

    'L' means to go from a node to its left child node.
    'R' means to go from a node to its right child node.
    'U' means to go from a node to its parent node.

Return the step-by-step directions of the shortest path from node s to node t.

[0,1,2,3,4,5,6
2n+1 = 3 for 1
2n+2 = 4 for 1

3 LC 1 LC 5
6 LC 2 RC 5


Example 1:

Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:

Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.



Constraints:

    The number of nodes in the tree is n.
    2 <= n <= 105
    1 <= Node.val <= n
    All the values in the tree are unique.
    1 <= startValue, destValue <= n
    startValue != destValue

Accepted
7,032
Submissions
16,410
 */
public class BTPath {
    /*

    public String getDirections(TreeNode root, int startValue, int destValue) {
               StringBuilder s1 = new StringBuilder();
               StringBuilder d1 = new StringBuilder();
               findAnc(root,startValue,s1);
               findAnc(root,destValue,d1);
               s1.reverse();d1.reverse();
               int minL = Math.min(s1.length(),d1.length());
               int i = 0;
               for(;i < minL; i++) {
                   if (s1.charAt(i) == d1.charAt(i)) {
                       continue;
                   } else {
                       break;
                   }
               }
               String st1 = s1.toString().substring(i);
               String dt1 = d1.toString().substring(i);
               StringBuilder stb = new StringBuilder();
               for(i = 0; i < st1.length();i++) stb.append('U');
               stb.append(dt1);
                return stb.toString();
    }

    private boolean findAnc(TreeNode root, int  i,StringBuilder strB) {

        if (root.val == i) return true;
        if ((root.left != null) && findAnc(root.left,i,strB)) {
            strB.append("L"); return true;
        }
        if ((root.right != null) && findAnc(root.right,i,strB)) {
            strB.append("R"); return true;
        }
        return (strB.length() > 0);
    }



    public static void main(String[] args) {
        BTPath btPath = new BTPath();

    }
    */
}
