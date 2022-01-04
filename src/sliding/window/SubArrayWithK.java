package sliding.window;

import java.util.HashMap;
import java.util.Map;

/*
992. Subarrays with K Different Integers
Hard

Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.



Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].



Note:

    1 <= A.length <= 20000
    1 <= A[i] <= A.length
    1 <= K <= A.length

 */
public class SubArrayWithK {
    public int subarraysWithKDistinct(int[] A, int K) {
        if (K == 0) return K;
        return atMost(A, K) - atMost(A, K - 1);
    }


    private int atMost(int[] A, int K) {
        Map<Integer,Integer> ind = new HashMap<>();
        int i = 0, j = 0;
        int f = 0;
        for (; j < A.length; j++) {
            int v = ind.getOrDefault(A[j],0);
            if (v == 0) K--;
            ind.put(A[j],++v);

            while ((i < A.length) && (K < 0)) {
                int vv = ind.get(A[i])-1;
                if (vv == 0) K++;
                ind.put(A[i],vv);
                i++;
            }
            f += (j - i) + 1;

        }// End of for
        return f;

    }// End of method


    public static void main(String[] args) {
        SubArrayWithK sbk = new SubArrayWithK();
        int[] iA = {1,2,1,2,3};
        System.out.println(sbk.subarraysWithKDistinct(iA, 2));
    }

}