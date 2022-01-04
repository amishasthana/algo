package Contest;

import java.util.*;

/*
1536. Minimum Swaps to Arrange a Binary Grid

    User Accepted: 1553
    User Tried: 2645
    Total Accepted: 1601
    Total Submissions: 5417
    Difficulty: Medium

Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.

A grid is said to be valid if all the cells above the main diagonal are zeros.

Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.

The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).



Example 1:

Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
Output: 3

Example 2:

Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
Output: -1
Explanation: All rows are similar, swaps have no effect on the grid.

Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
Output: 0



Constraints:

    n == grid.length
    n == grid[i].length
    1 <= n <= 200
    grid[i][j] is 0 or 1


 */
public class MinSwapGrid {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();// find nearest row.
    TreeSet<Integer> usedSet = new TreeSet<>();// used Allow to calculate the compensation due to row change.
    Map<Integer, List<Integer>> mValue = new HashMap<>();// list of rows with key number of trailing zeros
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int move = 0;
        for(int i =0; i < n;i++) {
            int key = 0;
            for(int j = n-1;j >=0;j--) {
                if (grid[i][j] != 0) break;
                key++;//Number of zeros
            }//
            List<Integer> lVal = mValue.getOrDefault(key,new LinkedList<Integer>());
            lVal.add(i);
            mValue.put(key,lVal);
        }
        int rr =0;
        for(int i =n; i >= 1;i--) {
            List<Integer> nList = mValue.get(i);
            if (nList != null) {
                for(int jj : nList) minHeap.add(jj);
            }
            if (i == n) continue;
            if (minHeap.isEmpty()) return  -1;
            int row = minHeap.poll();
            Set<Integer> ss = usedSet.tailSet(row);
            move += (row+ ((ss == null)?0:ss.size()))-rr;
            usedSet.add(row);
            rr++;
        }

        return move;
    }

    public static void main(String[] args) {
        MinSwapGrid msg = new MinSwapGrid();
        //int[][] grid = {{1,0,0},{1,1,0},{1,1,1}};
        //int[][] grid = {{0,0,1},{1,1,0},{1,0,0}};
        //int[][] grid = {{0,1,1,0},{0,1,1,0},{0,1,1,0},{0,1,1,0}};
        int[][] grid = {{0,0},{0,1}};
                System.out.println(msg.minSwaps(grid));
    }
}
