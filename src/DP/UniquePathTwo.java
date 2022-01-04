package DP;
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right


 */

public class UniquePathTwo {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] O = obstacleGrid;
        if ( (O == null) || (O.length == 0) ) return 0;
        int n = O.length; int m = O[0].length;

        int[][] NP = new int[n+1][m+1];
        NP[n][m-1] = 1; // seed.
        for( int i = n-1; i >= 0; i--) {
            for(int j = m-1; j >= 0; j--) {
                if ( O[i][j] == 1) {
                    NP[i][j] = 0;
                } else {
                    NP[i][j] = NP[i+1][j] + NP[i][j+1];
                }
            }//End of I loop
        }// End of outer loop,
        return NP[0][0];

    }

    public static void main(String[] args) {
        UniquePathTwo upt = new UniquePathTwo();
        int[][] A = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
        System.out.println(upt.uniquePathsWithObstacles(A));
    }


}
