import java.util.*;

/*
1840. Maximum Building Height
Hard

You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.

However, there are city restrictions on the heights of the new buildings:

    The height of each building must be a non-negative integer.
    The height of the first building must be 0.
    The height difference between any two adjacent buildings cannot exceed 1.

Additionally, there are city restrictions on the maximum height of specific buildings.
These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti]
indicates that building idi must have a height less than or equal to maxHeighti.

It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.

Return the maximum possible height of the tallest building.



Example 1:

Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.

Example 2:

Input: n = 6, restrictions = []
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.

Example 3:

Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.



Constraints:

    2 <= n <= 109
    0 <= restrictions.length <= min(n - 1, 105)
    2 <= idi <= n
    idi is unique.
    0 <= maxHeighti <= 109


 */
public class MaxBuildingHeight {
    int maxH = 0;


    public int maxBuilding2(int n, int[][] restrictions) {
        int[] h = new int[n];
        Arrays.fill(h,n-1);
        h[0] = 0;
        for (int i = 0; i < restrictions.length; i++) {
            h[restrictions[i][0]-1] = restrictions[i][1];
        }
        int maxH = 0;
        //Left to right.
        for(int i = 1;i < n;i++) {
            h[i] = Math.min(h[i-1]+1,h[i]);
        }
        for(int i = n-2;i >0;i--) {
            h[i] = Math.min(h[i+1]+1,h[i]);
            maxH = Math.max(maxH,h[i]);

        }
        return maxH;
    }

    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);

        int pH = 0; int pI = 1;
        int maxH = 0;
        if ((restrictions == null) || (restrictions.length <= 0)) return n-1;
        for (int i = 0; i < restrictions.length; i++) {
            restrictions[i][1]  = Math.min((pH+restrictions[i][0]-pI),restrictions[i][1]);
            maxH = Math.max(maxH,Math.max(restrictions[i][1],pH+restrictions[i][0]-pI));
            pH = restrictions[i][1];
            pI = restrictions[i][0];
        }
        pH += n-pI;
        System.out.println(pH+":");
        pH = restrictions[restrictions.length-1][1];
        pI = restrictions[restrictions.length-1][0];
        for(int i = restrictions.length-2;i >0;i--) {
            restrictions[i][1]  = Math.min(pH+pI-restrictions[i][0],restrictions[i][1]);
            pI = restrictions[i][0];
            pH = restrictions[i][1];
            maxH = Math.max(maxH,pH);
            System.out.println(pH+":"+pI+":"+maxH);
        }
        return maxH;
    }



    public static void main(String[] args) {
        MaxBuildingHeight mb = new MaxBuildingHeight();
        //System.out.println(mb.maxHWhenSame(2,4));
        //System.out.println(mb.maxAlwaysIncreasing(2,4));
        //mb.test();
        //int[][] res = {{2,1},{4,1}};
        //int[][] res = {};
        //int[][] res = {{8,5},{9,0},{6,2},{4,0},{3,2},{10,0},{5,3},{7,3},{2,4}};
        int[][] res = {{5,3},{2,5},{7,4},{10,3}};

        System.out.println(mb.maxBuilding(10,res));

    }

    /*
    5
[[2,1},[4,1}}
0,1,2,3,4
0 1 1 1  1
     */
}
