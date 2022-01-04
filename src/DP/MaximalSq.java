package DP;

import java.util.Arrays;

/*
221. Maximal Square
Medium

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

 */
public class MaximalSq {
    /*


     */
    public int maximalSquareDP(char[][] matrix) {
        if ( (matrix == null) || (matrix.length == 0) || (matrix[0].length == 0) ) return 0;
        int N = matrix.length; int M = matrix[0].length;
        int[][] S = new int[N][M]; //Square  side ending at i,j
        int mArea = 0;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean isOne = (matrix[i][j] == '1');
                if ((i == 0) || (j == 0)) {
                      if (isOne) S[i][j] = 1;
                      mArea = Math.max(mArea,S[i][j]*S[i][j]);
                      continue;
                }
                if (!isOne)  {
                    S[i][j] = 0; continue;
                }
                S[i][j] =  Math.min(Math.min(S[i][j-1],S[i-1][j]),S[i-1][j-1])+1;
                mArea = Math.max(mArea,S[i][j]*S[i][j]);
            }
        }//End of outer for
        return mArea;
    }

    public int maximalSquare(char[][] matrix) {
        if ( (matrix == null) || (matrix.length == 0) || (matrix[0].length == 0) ) return 0;
        int mArea = 0;
        int R = matrix.length; int C = matrix[0].length;
        int[] h = new int[C];
        int[] left = new int[C];
        int[] right = new int[C];
        Arrays.fill(right,C-1);

        for(int i = 0 ; i < R; i++) {
            int cL = 0; int cR = C-1;

            for(int j = 0; j < C; j++) {//Calculate h and l.
                boolean isOne = (matrix[i][j] == '1');
                if ( isOne) {
                    h[j]++;
                    left[j] = Math.max( left[j],cL);
                } else {
                    h[j] = 0; cL = j+1;
                    left[j] = 0;
                }
            }

            for(int j = C-1; j >= 0; j--) {//Calculate right.
                boolean isOne = (matrix[i][j] == '1');
                if ( isOne) {
                    right[j] = Math.min(right[j],cR);
                } else {
                    cR = j-1;
                    right[j] = C-1;
                }
                if ( ( left[j] <= right[j] )  && (h[j] <= ((right[j] - left[j])+1 ))) {
                    mArea = Math.max(mArea,h[j]*h[j] );
                }
            }


        }
        return mArea;
    }

    public static void main(String[] args) {
        MaximalSq ms = new MaximalSq();


        char[][] A = {
                {'1','0', '1','0','0'},
                {'1','0', '1','1','1'},
                {'1','1', '1','1','1'},
                {'1','0', '0','1','0'},
               };
        char[][] A1 = {{'1','0'}};
        System.out.println(ms.maximalSquare(A));
        System.out.println(ms.maximalSquareDP(A));
    }

}
