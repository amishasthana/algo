package DP;

import java.util.Stack;

public class AllPathsInRectangle {
    int M; int N; // M is col
    public int uniquePathsF(int m, int n) {
        int p = 0;
        N = n; M = m;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty() ) {
            Integer cIndex = stack.pop();
            int[] cElem = getElem(cIndex);
            if ( (cElem[0] == (n-1)) && (cElem[1] == (m-1)) ) {
                p++; continue;
            }
            int[] rElem = null;  int[] dElem = null;
            if ( cIndex %  M != (M-1) ) {
                stack.push(cIndex+1);
            }
            if ( cIndex/M != (N-1) )  {
                stack.push(cIndex+M);
            }
        }
        return p;
    }

    private int[] getElem(int n) {
        int[] S = new int[2];
        S[0] = n/M;
        S[1] = n%M;
        return S;
    }

    public int uniquePaths(int m, int n) {
        int[][] P = new int[n+1][m+1];// tells how many ways it can reach the end point. P[n-1][m-1]
        for(int i = n-1; i >= 0; i--) {
            for(int j = m-1; j >= 0; j--) {
                if ( (i == (n-1)) && (j == (m-1))) { P[i][j] =1; continue; }//seed
                P[i][j] = P[i+1][j] + P[i][j+1];
            }
        }// end of rows.
        return P[0][0];
    }


    public static void main(String[] args) {
        AllPathsInRectangle ap = new AllPathsInRectangle();
        System.out.println(ap.uniquePaths(7,3));
    }

}
