package algoplan;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
1368. Minimum Cost to Make at Least One Valid Path in a Grid
Hard

Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:

    1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
    2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
    3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
    4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])

Notice that there could be some signs on the cells of the grid that point outside the grid.

You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.



Example 1:

Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.

Example 2:

Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).

Example 3:

Input: grid = [[1,2],[4,3]]
Output: 1



Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    1 <= grid[i][j] <= 4

1 -->
2 <---
3 LOWER
4 UPPER

 */
public class MinPathWithModification {
    public static final int L = 2;
    public static final int R = 1;
    public static final int D = 3;
    public static final int U = 4;


    public int minCost(int[][] grid) {

        int maxCost = Integer.MAX_VALUE;//grid.length + grid[0].length-2;
        int[][] cost = new int[grid.length][grid[0].length];
        for (int i = 0; i < cost.length;i++) {
            Arrays.fill(cost[i],maxCost);
        }

        cost[0][0] = 0;
        Queue<int[]> PQ = new LinkedList<>();
        int cCost = 0;

        PQ.add(new int[]{0,0});
        while (!PQ.isEmpty()) {
            int[] cElem = PQ.poll();
            int i = cElem[0];
            int j = cElem[1];
            cCost = cost[i][j];
            int sign = grid[i][j];
            boolean lE =  ((j - 1) >= 0);
            boolean rE = (j+1) < grid[0].length;
            boolean dE = (i+1) < grid.length;
            boolean uE = ((i - 1) >= 0);;
            if ((sign == L) && lE) {
                if (cCost < cost[i][j-1]) {
                    PQ.add(new int[]{i, j - 1});
                    cost[i][j - 1] = cCost;
                }
            } else if ( (sign == R) && rE) {
                if (cCost < cost[i][j+1]) {
                    PQ.add(new int[]{i, j + 1});
                    cost[i][j + 1] = cCost;
                }
            } else if ( (sign == D) && dE) {
                if (cCost < cost[i+1][j]) {
                    PQ.add(new int[]{i + 1, j});
                    cost[i + 1][j] = cCost;
                }
            } else if ( uE ) {
                if (cCost < cost[i-1][j]) {
                    PQ.add(new int[]{i - 1, j});
                    cost[i - 1][j] = cCost;
                }
            }

            if ((sign != L) && lE) {
                if (cCost < cost[i][j-1]) {
                    PQ.add(new int[]{i, j - 1});
                    cost[i][j - 1] = cCost + 1;
                }
            }
            if ( (sign != R) && rE) {
                if (cCost < cost[i][j+1]) {
                    PQ.add(new int[]{i, j + 1});
                    cost[i][j + 1] = cCost + 1;
                }
            }
            if ( (sign != D) && dE) {
                if (cCost < cost[i+1][j]) {
                    PQ.add(new int[]{i + 1, j});
                    cost[i + 1][j] = cCost + 1;
                }
            }
            if ( uE && (sign != U))  {
                if (cCost < cost[i-1][j]) {
                    PQ.add(new int[]{i - 1, j});
                    cost[i - 1][j] = cCost + 1;
                }
            }

        }


         return cost[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        MinPathWithModification mp = new MinPathWithModification();
        int[][] A = {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        //int[][] A = {{1,1},{2,2}};
        int count = mp.minCost(A);
        System.out.println(count);
    }

}
