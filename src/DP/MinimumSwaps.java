package DP;

import java.util.Arrays;

/*
801. Minimum Swaps To Make Sequences Increasing
Medium

We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation:
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.

Note:

    A, B are arrays with the same length, and that length will be in the range [1, 1000].
    A[i], B[i] are integer values in the range [0, 2000].

 public int minSwap(int[] A, int[] B) {
        int N = A.length;
        int[] swap = new int[1000];
        int[] not_swap = new int[1000];
        swap[0] = 1;
        for (int i = 1; i < N; ++i) {
            not_swap[i] = swap[i] = N;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                not_swap[i] = not_swap[i - 1];
                swap[i] = swap[i - 1] + 1;
            }
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                not_swap[i] = Math.min(not_swap[i], swap[i - 1]);
                swap[i] = Math.min(swap[i], not_swap[i - 1] + 1);
            }
        }
        return Math.min(swap[N - 1], not_swap[N - 1]);
    }


class Solution {
    public int minSwap(int[] A, int[] B) {
        int swapRecord = 1, fixRecord = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
		// In this case, the ith manipulation should be same as the i-1th manipulation
                // fixRecord = fixRecord;
                swapRecord++;
            } else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
		// In this case, the ith manipulation should be the opposite of the i-1th manipulation
                int temp = swapRecord;
                swapRecord = fixRecord + 1;
                fixRecord = temp;
            } else {
                // Either swap or fix is OK. Let's keep the minimum one
                int min = Math.min(swapRecord, fixRecord);
                swapRecord = min + 1;
                fixRecord = min;
            }
        }
        return Math.min(swapRecord, fixRecord);
    }
}


swap[n] means the minimum swaps to make the A[i] and B[i] sequences increasing for 0 <= i <= n in condition that we swap A[n] and B[n]
not_swap[n] is the same with A[n] and B[n] not swapped.

 */
public class MinimumSwaps {
    /*
swap[i] --> What is the number of swap so far
 if A and B are swapped at i and they are equal till i.
noswap[i[ -->  What is the number of swap so far if A and B are not swapped at i.

As there is a solution
So the situation A[i] < A[i-1] as well and A[i] < B[i-1] can not exist as there is no way to solve the array.

*/
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n];
        int[] no_swap = new int[n];
        swap[0] = 1; // if it was swapped then it has to start at 1.
        no_swap[0] = 0;
        for(int i = 1; i < A.length;i++) {
            if ( ( A[i] < A[i-1]) || ( B[i] < B[i-1] ) ) {// Basically Ai has to go with B [i-1]
                swap[i] = no_swap[i-1]+1;//swapping i means no swap on i-1.
                no_swap[i] = swap[i-1]; //  not swapping at i, so need to swap at i-1.
            }
            else if ( ( A[i] < B[i-1]) || ( B[i] < A[i-1] ) ) {// this and above has to be mutually exclusive.
                swap[i] = swap[i-1]+1;//swapping i means have to swap i -1
                no_swap[i] = no_swap[i-1]; //  not swapping at i, so need to swap at i-1.
            } else {// We have calculated so far. Now at this point, whatever is the min swap, can be used.
                no_swap[i] = Math.min(swap[i],no_swap[i]);
                swap[i] = no_swap[i]+1;// This I do not understand completely.
            }
        }
        return Math.min(no_swap[n-1],swap[n-1]);

    }

    public int minSwapT(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n];
        int[] no_swap = new int[n];

        Arrays.fill(swap,n);
        Arrays.fill(no_swap,n);
        swap[0] = 1; // if it was swapped then it has to start at 1.
        no_swap[0] = 0;
        for(int i = 1; i < A.length;i++) {
            if ( ( A[i] > A[i-1]) && ( B[i] > B[i-1] ) ) {// Basically no need to swap.
                swap[i] = swap[i-1]+1;//swapping i means so swap at i-1. This is more for future....
                no_swap[i] = no_swap[i-1]; //  not swapping at i, so no need to swap at i-1.
            }
           if ( ( A[i] < B[i-1]) && ( B[i] < A[i-1] ) ) {// Not mutually exclusive from above.
                swap[i] = Math.min(swap[i],swap[i-1]+1);//swapping i means have to swap i -1
                no_swap[i] = Math.min(no_swap[i],swap[i-1]); //  not swapping at i, so need to swap at i-1.
            }
        }
        return Math.min(no_swap[n-1],swap[n-1]);

    }

    public static void main(String[] args) {
        MinimumSwaps ms = new MinimumSwaps();
        int[] A = {1,3,5,4};
        int[] B = {1,2,3,7};
        System.out.println(ms.minSwap(A,B));
    }

}
