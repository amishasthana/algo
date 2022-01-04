package DP;
/*
980. Unique Paths III
Hard

On a 2-dimensional grid, there are 4 types of squares:

    1 represents the starting square.  There is exactly one starting square.
    2 represents the ending square.  There is exactly one ending square.
    0 represents empty squares we can walk over.
    -1 represents obstacles that we cannot walk over.

Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.


 */
public class UniquePathThree {
    public int numWays(int steps, int arrLen) {
        long[][] DP = new long[steps+1][arrLen];
        int M = 1000000007;
        DP[steps][0] = 1;
        for( int s = steps; s >= 1; s--) {
            for(int i = 0; i < arrLen && i <= s; i++) {
                long left = (i == 0)?0:DP[s][i-1];
                long right = (i == (arrLen-1))?0:DP[s][i+1];
                DP[s-1][i] = (DP[s][i] + left + right)%M;
            }
        }// end of steps.
        return (int)DP[0][0];
    }

    public int numWaysTwo(int steps, int arrLen) {
        long[] DP = new long[arrLen];
        int M = 1000000007;
        DP[0] = 1;
        for( int s = steps; s >= 1; s--) {
            long[] CAL  = new long[arrLen];
            for(int i = 0; i < arrLen && i <= s; i++) {
                long left = (i == 0)?0:DP[i-1];
                long right = (i == (arrLen-1))?0:DP[i+1];
                long current = DP[i];
                CAL[i] = (current + left + right)%M;
            }
            DP = CAL;
        }// end of steps.
        return (int)DP[0];
    }


    public static void main(String[] args) {
        UniquePathThree upt = new UniquePathThree();
        System.out.println(upt.numWaysTwo(4,2));
    }

}
