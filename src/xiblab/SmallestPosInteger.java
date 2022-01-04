package xiblab;

import java.util.BitSet;

/*


This is a demo task.

Write a function:

    class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

        N is an integer within the range [1..100,000];
        each element of array A is an integer within the range [−1,000,000..1,000,000].

Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class SmallestPosInteger {
    public int solution(int[] A) {
        int l = A.length;
        BitSet bSet = new BitSet(l+1);
        for(int i : A) {
            if ((i > 0) && (i <= l)) {
                if (!bSet.get(i)) bSet.flip(i);
            }
        }
        for(int i = 1; i <=l;i++) {
            if (!bSet.get(i)) return i;
        }
        return l+1;
    }
}
