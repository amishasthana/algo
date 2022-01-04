package Contest;

import java.util.*;

/*
1591. Strange Printer II

    User Accepted: 210
    User Tried: 396
    Total Accepted: 212
    Total Submissions: 631
    Difficulty: Hard

There is a strange printer with the following two special requirements:

    On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover up the existing colors in the rectangle.
    Once the printer has used a color for the above operation, the same color cannot be used again.

You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.

Return true if it is possible to print the matrix targetGrid, otherwise, return false.



Example 1:

Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
Output: true

Example 2:

Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
Output: true

Example 3:

Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
Output: false
Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different turns.

Example 4:

Input: targetGrid = [[1,1,1],[3,1,3]]
Output: false



Constraints:

    m == targetGrid.length
    n == targetGrid[i].length
    1 <= m, n <= 60
    1 <= targetGrid[row][col] <= 60

Java

 */
public class StrangePrinter {
    int[][] X = new int[0][61];
    int[][] Y = new int[0][61];
    Map<Integer, Set<Integer>> M = new HashMap<>();
    Map<Integer,Integer> DEP = new HashMap<>();// This key can be reached from Y values.
    public boolean isPrintable(int[][] targetGrid) {
        populateMinMax(targetGrid);
        for(int n = 1; n <= 60;n++ ) {
            if (X[0][n] == 61) continue;// not exist.
            Set<Integer> cSet = M.getOrDefault(n,new HashSet<>());
            for(int i = X[0][n]; i <= X[1][n];i++) {
                for (int j = Y[0][n]; j < Y[1][n]; j++) {
                      if (targetGrid[i][j]  != n) {
                          int val = targetGrid[i][j];
                          cSet.add(val);
                          DEP.put(val,DEP.getOrDefault(val,0)+1);
                      }
                }
                M.put(n,cSet);
                if (!DEP.containsKey(n)) DEP.put(n,0);
            }
        }
        if(isCyclic()) return false;
        return true;
    }

    private boolean isCyclic() {

        return true;
    }

    private void populateMinMax(int[][] targetGrid) {

        Arrays.fill(X[0],61);Arrays.fill(X[1],-1);
        Arrays.fill(Y[0],61);Arrays.fill(Y[1],-1);

        for(int i = 0; i < targetGrid.length;i++) {
            for(int j = 0; j < targetGrid[0].length;j++) {
                int v = targetGrid[i][j];
                X[0][v] = Math.min(X[0][v],j);
                X[1][v] = Math.min(X[1][v],j);
                Y[0][v] = Math.min(Y[0][v],i);
                Y[1][v] = Math.min(Y[1][v],i);
            }
        }
    }
}
