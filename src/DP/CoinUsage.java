package DP;

import java.util.Arrays;

/*
322. Coin Change
Medium

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1

 */
public class CoinUsage {
    public int coinChange(int[] coins, int amount) {
        int[] A = new int[amount+1];//11 = amount, 11 th index hold for 11.
        Arrays.sort(coins);
        Arrays.fill(A,amount+1);
        A[0] = 0;
        for(int i = 1; i <= amount; i ++) {
            for(int c = 0; c  < coins.length; c++) {
                int delta = ( i - coins[c]);
                if (delta < 0) break;
                A[i] = Math.min(A[i],A[delta]+1);
            }//end of inner for
        }// end of outer for.
        return (A[amount] > amount)?-1: A[amount] ;
    }


    public static void main(String[] args) {
        CoinUsage cu = new CoinUsage();
        int[] C1 = {4,5};
        System.out.println(cu.coinChange(C1,11));
    }


}
