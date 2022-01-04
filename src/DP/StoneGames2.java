package DP;

import java.util.HashMap;
import java.util.Map;

/*
1140. Stone Game II
Medium

Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.



Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.



Constraints:

    1 <= piles.length <= 100
    1 <= piles[i] <= 10 ^ 4

 */
public class StoneGames2 {
    int[] sums = null;
    Map<String,Integer> dp = null;// Maximum going fwd if I pick ith stone.
    public int stoneGameII(int[] piles) {
        if ( ( piles == null) || (piles.length == 0) ) return 0;
        int n = piles.length;
        sums = new int[n];
        dp = new HashMap<>();
        sums[n-1] = piles[n-1];
        for(int i = n-2; i >= 0; i--) sums[i] = sums[i+1]+piles[i];
        return cal(piles,0,1);
    }

    private int cal(int[] piles, int s, int M) {
        int sum = 0;
        if ( (s + 2*M -1) >= (piles.length - 1) ) {
            for(int i = s; i < piles.length; i++) sum += piles[i];
            return sum;
        }
        String key = s+"_"+M;
        if ( dp.get(key) != null) return dp.get(key);
        int minOpp = Integer.MAX_VALUE;
        for(int x = 1; x <= 2*M; x++) {// M =2 , 1 = 0,1,2,3
            //Basically I will decide based on whatever is min for opponent.
            minOpp = Math.min(minOpp,cal(piles,s+x, Math.max(M,x) ));
        }
        int myV = sums[s] - minOpp;
        dp.put(key,myV);
        return myV;


    }



    public  static void main(String[] args) {
        StoneGames2 sg = new StoneGames2();
        int[] A = {2,7,9,4,4};
        System.out.println(sg.stoneGameII(A));
    }

}
