package sliding.window;

import java.util.ArrayDeque;
import java.util.Deque;

/*
862. Shortest Subarray with Sum at Least K
Hard

Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.



Example 1:

Input: A = [1], K = 1
Output: 1

Example 2:

Input: A = [1,2], K = 4
Output: -1

Example 3:

Input: A = [2,-1,2], K = 3
Output: 3



Note:

    1 <= A.length <= 50000
    -10 ^ 5 <= A[i] <= 10 ^ 5
    1 <= K <= 10 ^ 9


 */
public class ShortestSubarrayNegative {
    public int shortestSubarray(int[] A, int K) {
        int len = A.length;
        Deque<Integer> q = new ArrayDeque<>();
        int[] pSum = new int[len+1];

        for(int i = 1; i < len+1;i++) pSum[i] = pSum[i-1]+A[i-1];//Prefix sum
        // So P[j] - P[i] = sum from i to j-1.
        int shLen  = len+1;
        for(int j = 0;j < len+1;j++) {
            while ( (q.size() > 0) && ( pSum[j] - pSum[q.peekFirst()]) >= K ) {//Dequeing from beginning.
                shLen = Math.min(shLen,j - q.pollFirst());//No need to add +1 . remember pSum[j] does not include j.
            }
            while ( (q.size() > 0)  && (pSum[q.peekLast()] >= pSum[j]) )  {
                // Basically saying  20...10....40. Where 40 is the sum.  So start from 10(If there was something starting with 40 before this ,
                // it would have been extracted.
                q.pollLast();
            }
            q.add(j);

        }//End of for loop for j.

        return ( shLen < len+1 ? shLen:-1);

    }

    public static void main(String[] args) {
        ShortestSubarrayNegative ssn = new ShortestSubarrayNegative();
        int[] A = {1};
        System.out.println(ssn.shortestSubarray(A,1));
    }


}
