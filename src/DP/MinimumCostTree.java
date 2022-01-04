package DP;

import java.util.Stack;

/*
1130. Minimum Cost Tree From Leaf Values
Medium

Given an array arr of positive integers, consider all binary trees such that:

    Each node has either 0 or 2 children;
    The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
    The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.

Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.



Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4



Constraints:

    2 <= arr.length <= 40
    1 <= arr[i] <= 15
    It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

Accepted
22,287
Submissions

 */
public class MinimumCostTree {
    public int mctFromLeafValues(int[] arr) {
        if ( (arr == null) || ( arr.length == 0) ) return 0;
        int n = arr.length;
        int[][] mA = new int[n][n];// Maximum between i.j-1 inclusive.
        int[][] dp = new int[n][n]; // Sum of values of non leaf between ,i,j-1 inclusive.

        for(int i = 0; i < n; i++) {
            int localMax = 0;
            int j = i;
            for(; j < n; j++) {
                localMax = Math.max(localMax,arr[j]);
                mA[i][j] = localMax;
            }

        }// End of max loop.

        for(int len = 1; len < n; len ++) {
            for( int i = 0; i +len < n; i++) {
                int right = i + len;
                dp[i][right]  = Integer.MAX_VALUE;
                if ( len == 1) {
                    dp[i][right] = arr[i]*arr[right];
                } else {
                    for( int k = i ; k < right; k++) {

                        dp[i][right] = Math.min(dp[i][right] , dp[i][k] + dp[k+1][right] + mA[i][k]*mA[k+1][right]);
                    }
                }
            }
        }
        return dp[0][n-1];

    }

    public int mctFromLeafValues2(int[] A) {
        long res = 0; //The final result.
        Stack<Integer> R = new Stack<>();
        int n = A.length;
        if (n == 1) return A[0];
        //R.push(Integer.MAX_VALUE);
        R.push(0);
        for(int i = 1; i < n;) {
            while ( (i < n) && (R.isEmpty() || (A[i] <= A[R.peek()])) ) {// Stack is ever increasing.
                R.push(i++);
            }
            // Current A[i] is right highesh. Also while popping out element
            // You get the left highest.
            while ( (i < n) && !R.isEmpty() && (A[R.peek()] < A[i]) ) {
                int cIndex = R.pop();
                int cMax = Math.min(A[i],(!R.isEmpty())?A[R.peek()]:A[i]);
                res +=  cMax*A[cIndex];
            }
        }
        while (R.size() > 1) {
            res += A[R.pop()]*A[R.peek()];
        }

        return (int)res;

    }


    public static void main(String[] args) {
        MinimumCostTree mct = new MinimumCostTree();
        int[] A = {6,2,4};
        int[] A1 = {4,11};
        System.out.println(mct.mctFromLeafValues(A1));
        System.out.println(mct.mctFromLeafValues2(A1));
    }

}
