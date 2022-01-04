package algoplan;

import java.util.*;

/*
42. Trapping Rain Water
Hard

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9



Constraints:

    n == height.length
    1 <= n <= 2 * 104
    0 <= height[i] <= 105

Accepted
963,592
Submissions
1,756,698
 */
public class RainWater {

    public int trap(int[] height) {
         //int[] H = new int[height.length];
         int M = (int)1e9+7;
         Map<Integer, Queue<Integer>> map = new HashMap<>();//Height to Index.
         for(int i = 0; i < height.length;i++) {
             //H[i] = height[i];
             Queue<Integer> Q = map.getOrDefault(height[i],new LinkedList<>());
             Q.add(i);
             map.put(height[i],Q);
         }
         //Arrays.sort(H);0;
         Arrays.sort(height);
         TreeSet<Integer> indexS = new TreeSet<>();// Indexes . All indexes will be higher or equal
         BitSet bitSet = new BitSet(height.length);
         long W = 0;
         for(int i = height.length-1;i >= 0;i--) {
             int cH  = height[i];
             int index = map.get(cH).remove();
             if (bitSet.get(index)) {
                 W -= cH;
                 continue;
             }
             bitSet.set(index);
             Integer ceiling = indexS.ceiling(index);
             Integer floor = indexS.floor(index);
             if (ceiling != null) {
                 W += cH*(ceiling-index-1);
                 bitSet.set(index,ceiling+1);
             }
             if (floor != null) {
                 W += cH*(index-floor-1);
                 bitSet.set(floor,index);
             }
             indexS.add(index);
         }

         return (int) (W%M);

    }
    /*
    Much Superior --> basically for each small one which will be submerged --> Min (LH,RH) - cH = is the water above it.

    public int trap(int[] height) {
        if ( ( height == null) || (height.length < 2) ) return 0;
        int len  = height.length;
        int[] mLeft = new int[len];
        int[] mRight = new int[len];
        int A = 0;
        mLeft[0] = height[0];
        for(int i = 1; i < len; i++) {
            mLeft[i] = Math.max(mLeft[i-1],height[i]);
        }
        mRight[len-1] = height[len-1];
        for(int i = len-2; i >= 0; i--) {
            mRight[i] = Math.max( mRight[i+1],height[i] );
            if ( (mRight[i] > height[i]) && (mLeft[i] >  height[i]) ) {
                A +=(  Math.min(mRight[i] , mLeft[i] ) - height[i] );
            }
        }
        return A;
    }

     */

    public static void main(String[] args) {
        RainWater rw = new RainWater();
        int[] W = {4,2,0,3,2,5};
        System.out.println(rw.trap(W));
    }
}
