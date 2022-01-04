package tree;

import java.util.LinkedList;
import java.util.List;

/*
1305. All Elements in Two Binary Search Trees

    User Accepted: 2813
    User Tried: 2880
    Total Accepted: 2933
    Total Submissions: 3812
    Difficulty: Medium

Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.



Example 1:

Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]

Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]

Example 3:

Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]

Example 4:

Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]

Example 5:

Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]


 */
public class AllElementOfTwoBinTree {
    public class TreeNode {
    int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
              List<Integer> list1 = new LinkedList<>();
              List<Integer> list2 = new LinkedList<>();
              getSortedArrayFromTree(root1,list1);
              getSortedArrayFromTree(root2,list2);
              int i1 = 0; int i2 = 0;
              List<Integer> result = new LinkedList<>();
              while((i1 < list1.size()) || (i2 < list2.size())) {
                  int fValue = (i1 >= list1.size())?Integer.MAX_VALUE:list1.get(i1);
                  int sValue = (i2 >= list2.size())?Integer.MAX_VALUE:list2.get(i2);
                  if (fValue <= sValue) {
                      result.add(fValue);
                      i1++;
                  } else {
                      result.add(sValue);
                      i2++;
                  }
              }
                    return    result;

    }

    public void getSortedArrayFromTree(TreeNode root,List<Integer> sList) {
        if (root == null) return;
        getSortedArrayFromTree(root.left,sList);
        sList.add(root.val);
        getSortedArrayFromTree(root.right,sList);
    }
}
