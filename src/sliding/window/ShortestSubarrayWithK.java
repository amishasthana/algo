package sliding.window;
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
public class ShortestSubarrayWithK {

    public int shortestSubarray(int[] A, int K) {
        int i =0, j = 0;
        int slen = Integer.MAX_VALUE;int neg = 0;
        boolean found = false;
        for(; j < A.length;j++) {
            K -= A[j];

            while ( ( i < A.length) && ( K <= 0) ) {
                slen  =  Math.min((j-i)+1,slen);
                found = true;
                if (A[i] < 0) {
                    i++;
                    continue;
                }
                K += A[i++];
            }

        }//End of for
        if (found)  return slen;
        return -1;
    }

    public static void main(String[] args ) {
        ShortestSubarrayWithK ss = new ShortestSubarrayWithK();
        int[] A = {2,-1,3,1};
        System.out.println(ss.shortestSubarray(A,3));
    }


}
