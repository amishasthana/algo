/*
Apparently there is ref to https://www.geeksforgeeks.org/min-max-range-queries-array/

Given an array A that is a permutation of n numbers [1-n]. Find the number of subarrarys  S
 that meets the following condition max(S) - min(S) = length(S) - 1.

Example 1:

Input: [4, 3, 1, 2, 5]
Output: 10
Explanation:
subarrays that meets the condition are
[4]
[3]
[1]
[2]
[5]
[4 3]
[1 2]
[3 1 2]
[4 3 1 2]
[4 3 1 2 5]
 */
public class SegmentTree {

    public static final String  MIN = "MIN";
    public static final String  MAX = "MAX";

    class Node {
        int i;//Start inclusive
        int j;//End inclusive.
        int v;//Some computation, can me min max etc.
        String type;//Optional
        Node left;
        Node right;
    }



    public Node createSegmentTree(int[] iA,int s,int e,String type) {//s and e inclusive.
        if ((e-s) < 0) return null;
        Node left = null;Node right = null;
        if (e != s) {
            left = createSegmentTree(iA, s, s+(e - s) / 2, type);
            right = createSegmentTree(iA, s+(e - s) / 2 + 1, e, type);
        }
        Node root = new Node();

        root.type = type; root.i = s; root.j = e;
        root.left = left; root.right = right;
        if (e == s) {
            root.v = iA[s];
        } else {
            if (MIN.equals(type)) {
                computeMin(root);//For a particular type.
            } else {
                computeMax(root);//For a particular type.
            }
        }
        return root;
    }

    private void computeMin(Node root) {
        root.v = Math.min(((root.right != null)?root.right.v:0), ((root.left != null)?root.left.v:0));
    }

    private void computeMax(Node root) {
        root.v = Math.max(((root.right != null)?root.right.v:0), ((root.left != null)?root.left.v:0));
    }

    public int findValueInRange(Node root,int start, int end) {
        if ( (root == null)  ||
                ((root.i > end) || (root.j < start))  ) { //Outside
            if (MIN.equals(root.type)) {
                return Integer.MAX_VALUE;
            }else {
                return Integer.MIN_VALUE;
            }
        } else if ((root.i >= start) && (root.j <= end)) {
            return root.v;
        } else {
            if (MIN.equals(root.type)) {
                return Math.min(findValueInRange(root.left, start, end), findValueInRange(root.right, start, end));
            } else {
                return Math.max(findValueInRange(root.left, start, end), findValueInRange(root.right, start, end));
            }

        }
    }

    public void printArray(int[] iA) {
        Node nmin = createSegmentTree(iA,0,4,MIN);
        Node nmax = createSegmentTree(iA,0,4,MAX);
        for(int i = 0; i < iA.length;i++) {
            for(int j = i; j < iA.length;j++) {
                int minInRange = findValueInRange(nmin,i,j);
                int maxInRange = findValueInRange(nmax,i,j);
                if((j-i) == (maxInRange - minInRange)) {
                    print(iA,i,j);
                }
            }
        }
    }

    private void print(int[] iA,int i,int j) {
        if ((j < i) && (j >= iA.length)) return;
        for(int index = i ; index <= j;index++) {
            System.out.print(iA[index]);
            System.out.print(" ");
        }
        System.out.println(" ");
    }

   public static void main(String[] args) {
       SegmentTree sgt = new SegmentTree();
       //int[] iA = {4,3,1,2,5};
       int[] iA = {4,6,5,1,3,2};
       sgt.printArray(iA);
   }

}
