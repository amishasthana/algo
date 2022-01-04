package DP;
/*
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.





Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.



Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.



Note:

    N will be between 1 and 25.
    K will be between 0 and 100.
    The knight always initially starts on the board.


 */

import java.util.*;

public class KnightProbability {
    private int[][]dir = new int[][]{{-2,-1},{-1,-2},{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1}};
    private Map<Integer,Set<Integer>>  populateMap(int N) {

        Map<Integer,Set<Integer>> m = new HashMap<>();
        for(int i = 0; i < N;i++) {
            for(int j = 0; j < N;j++) {
                Set<Integer> s = new HashSet<>();
                m.put(i*N+j,s);
                for(int[] rc : dir ) {
                    if ( (i + rc[0] >= 0) && ( (i + rc[0]) < N) && (j + rc[1] >= 0) && (j + rc[1] < N)) {
                        s.add((i+rc[0])*N + (j+rc[1]));
                    }
                }

            }
        }
        return m;
    }



    public double knightProbability(int N, int K, int r, int c) {
        if (K == 0) return 1;
        double[][] FC = new double[N][N];
        double[][] SC = new double[N][N];
        Map<Integer,Set<Integer>> mMap = populateMap(N);
        FC[r][c] = 1;
        int k = 1;
        double[][] fMap = null;
        double[][] sMap = null;
        while (k <= K) {

            if (k%2 == 1) {
                fMap = FC; sMap = SC;
            } else {
                fMap = SC; sMap = FC;
            }
            for(double[] A : sMap) {
                Arrays.fill(A,0.0);
            }
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if ( fMap[i][j] > 0 ) {
                        double p = fMap[i][j];
                        Set<Integer> childIndex =  mMap.get(i*N+j);
                        for(int nIndex : childIndex) {
                            sMap[nIndex/N][nIndex%N] += p*(0.125);
                        }
                    }
                }//end of inner for.
            }// End of outer for.
            k++;
        }//End of while.

        double p = 0.0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                p += sMap[i][j];

            }//end of inner for.
        }// End of outer for.

        return p;

    }

    public static void main(String[] args) {
        KnightProbability kp = new KnightProbability();
       //System.out.println(kp.knightProbability(3,2,0,0));
        System.out.println(kp.knightProbability(1,0,0,0));
    }

}
