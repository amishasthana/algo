package sliding.window;
/*
930. Binary Subarrays With Sum
Medium

In an array A of 0s and 1s, how many non-empty subarrays have sum S?



Example 1:

Input: A = [1,0,1,0,1], S = 2
Output: 4
Explanation:
The 4 subarrays are bolded below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]



Note:

    A.length <= 30000
    0 <= S <= A.length
    A[i] is either 0 or 1.

 */
public class BinaryArrayWithSum {
    public int numSubarraysWithSum(int[] A, int S) {
        if (S == 0) return atMost(A,S);
        return atMost(A,S) - atMost(A,S-1);
    }

    private int atMost(int[] A,int S) {
        int i = 0,j = 0;
        int f = 0;
        for(;j < A.length;j++) {
            S -= A[j];
            while ( (i < A.length) && (S < 0) ) {
                S += A[i++];
            }
            f += (j-i)+1;
        }//End of for.
        return f;
    }
}
