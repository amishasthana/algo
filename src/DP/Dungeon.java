package DP;

import java.util.Arrays;

/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.



Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
-2 (K) 	-3 	3
-5 	-10 	1
10 	30 	-5 (P)
 */
public class Dungeon {
    /*
    public int calculateMinimumHP(int[][] dungeon) {
        if ( (dungeon == null) || (dungeon.length == 0)) return 0;
        int N = dungeon.length; int M = dungeon[0].length;
        int[][] need = new int[N][M];

        for(int i = N;i >= 1;i-- ) {
            for(int j = M;j >= 1;j-- ) {
                if ((i == N) && (j == M) ) {
                    need[i][j] = (dungeon[N-1][M-1] <= 0)?(dungeon[N-1][M-1]*-1 + 1):0;
                    continue;
                }
                need[i][j] =
            }
        }
    }
*/

public int calculateMinimumHP(int[][] dungeon) {
        if ( (dungeon == null) || (dungeon.length == 0) ) return 1;
        int N  = dungeon.length; int M = dungeon[0].length;
        int[][] H = new int[N+1][M+1];
        for(int[] rA : H) {
            Arrays.fill(rA,Integer.MAX_VALUE);
        }

        H[N-1][M] = 1;
        H[N][M-1] = 1;
        for(int i = N-1; i >= 0; i--) {
           for(int j = M-1; j >= 0; j--) {
              int need = Math.min( H[i][j+1],H[i+1][j] ) - dungeon[i][j];
              H[i][j] = (need <= 0) ? 1: need;
         }
       }//End of rows
    return H[0][0];
}



    public static void main(String[] args) {
        Dungeon dg = new Dungeon();
        int[][] A = {
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}
        };
        int[][] A1 = { {1,-2,3},
                        {2,-2,-2}};
        System.out.println(dg.calculateMinimumHP(A1));
    }


}
