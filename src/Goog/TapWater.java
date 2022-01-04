package Goog;
/*
42. Trapping Rain Water
Hard
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

 */
public class TapWater {
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

    public static void main(String[] args) {
        int[] B = {0,1,0,2,1,0,1,3,2,1,2,1};
        TapWater tw = new TapWater();
        System.out.println(tw.trap(B));
    }

}
