package Goog;

import java.util.HashMap;
import java.util.Map;

/*
1074. Number of Submatrices That Sum to Target
Hard

Given a matrix and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.



Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.

Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.

Example 3:

Input: matrix = [[904]], target = 0
Output: 0



Constraints:

    1 <= matrix.length <= 100
    1 <= matrix[0].length <= 100
    -1000 <= matrix[i] <= 1000
    -10^8 <= target <= 10^8


 */
public class SubMatrix {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int l1 = matrix.length;
        int l2 = matrix[0].length;
        int[][] sum = new int[l1+1][l2+1];
        for(int i = 0 ; i < l1 ; i++) {
            for(int j = 0; j < l2; j++) {
                sum[i+1][j+1] = sum[i+1][j]+matrix[i][j];
            }
        }// end of outer for.

        int nT = 0;
        for(int c1 = 0 ; c1 < l2 ; c1++) {
            for (int c2 = c1+1; c2 <= l2; c2++) {
                int sSoFar = 0; //int rStart = 0;
                Map<Integer,Integer> map = new HashMap<>();
                map.put(0,1);
                for(int r = 1 ; r <= l1 ; r++) {
                  sSoFar += sum[r][c2] - sum[r][c1];
                  nT += map.getOrDefault(sSoFar-target,0);
                  map.put(sSoFar,map.getOrDefault(sSoFar,0)+1);
                }
            }//inner for column right.
        }//end of outer column left
        return nT;
    }

    public static void main(String[] args) {
        SubMatrix sm = new SubMatrix();
        int[][] A = {{1,-1},{-1,1}};
        System.out.println(sm.numSubmatrixSumTarget(A,0));
    }


}
