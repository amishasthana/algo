package xiblab;

import java.util.HashMap;
import java.util.Map;
/*
basically two arrays A and B
Find K
Such that A0+....A[k] = A[k+1]....A[end] = B[0] +...+B[K] = B[K+1]+ ... +B[end]

 */
public class EqualSplitArray {
    //Basically in two arrays.
    // so prefix sum --> A[i] --> including i.
    // So two sum is
    public int solution(int[] A, int[] B) {
        int index = 0;
       long[] pSumA = new long[A.length];
        long[] pSumB = new long[B.length];
        pSumA[0] = A[0]; pSumB[0] = B[0];
        prefixSum(A,pSumA);
        prefixSum(B,pSumB);
        Map<Integer,Long> sMapA =  getMapOfInterest(pSumA);
        for(Map.Entry<Integer,Long> E : sMapA.entrySet()) {
            int ind = E.getKey();
            if (ind >= (B.length-1)) break;
            if ((pSumB[ind] == E.getValue()) && (pSumB[ind] == (pSumB[B.length-1]-pSumB[ind]))) index++;
        }

       return index;
    }

    private void prefixSum(int[] A, long[] pSum) {
        for(int i = 1; i < A.length;i++) {
            pSum[i] += pSum[i-1]+A[i];
        }
    }

    private Map<Integer,Long> getMapOfInterest(long[] pSum) {
        Map<Integer,Long> S = new HashMap<>();
        for(int i = 0; i < pSum.length-1;i++) {
            if (pSum[i] == (pSum[pSum.length-1] - pSum[i])) {
                S.put(i, pSum[i]);
            }
        }
        return S;
    }

    public static void main(String[] args) {
        EqualSplitArray eq = new EqualSplitArray();
        int[] A = {2,-2,-3,3};
        int[] B = {0,0,4,-4};
        System.out.println(eq.solution(A,B));
    }
}
