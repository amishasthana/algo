package xiblab;

import java.util.HashMap;
import java.util.Map;
/*
   Number of subarray with sum zero.
   1 element is valid array/
 */
public class SubArrayZero {
    public int solution(int[] A) {
        int I = 1_000_000_000;
        long v = 0;
        Map<Long,Long> map = new HashMap<>();
        long[] pSum = new long[A.length+1];
        map.put(0l,1l);
        for(int i = 0; i < A.length;i++) {
            pSum[i+1] = pSum[i] + A[i];
            //if (pSum[i] == 0)
            long cIndex = map.getOrDefault(pSum[i+1],0l);
            v += cIndex;
            map.put(pSum[i+1],++cIndex);
        }
        if (v > I) return -1;
        return (int)v;
    }

    public static void main(String[] args) {
        int[] A = {2,-2,3,0,4,-7};
        SubArrayZero saz = new SubArrayZero();
        System.out.println(saz.solution(A));
    }

}
