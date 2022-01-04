package Goog;
/*
11. Container With Most Water
Medium

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.



The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.



Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49

Accepted
599,485
Submissions
1,208,446
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxA = Integer.MIN_VALUE;
        int i = 0; int j = height.length-1;
        while (i < j) {
            maxA = Math.max(maxA, (j-i)* Math.min(height[i],height[j]) );
            if ( height[i] > height[j] )  {
                j--;
            } else {
                i++;
            }
        }
        return maxA;
    }


    public static void main(String[] args) {
        int[] A = {1,8,6,2,5,4,8,3,7};
        ContainerWithMostWater cM = new ContainerWithMostWater();
        System.out.println(cM.maxArea(A));
    }

}
