package algoplan;

/*
309. Best Time to Buy and Sell Stock with Cooldown
Medium

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

    After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:

Input: prices = [1]
Output: 0



Constraints:

    1 <= prices.length <= 5000
    0 <= prices[i] <= 1000

max --> sell at i
   = max[i-1] + no sold at i-1 + there is one +Buy

max --> No activity
   = max[i-1]--> any kind.

max --> buy.
     --> max[i-1] with no activity at i-1 + if buy price less then the previous one.

 */
public class BestStockPrice1 {
    public int maxProfit(int[] prices) {
           int l = prices.length;
           int[][] P = new int[l+1][2];// start from 1. second index indicate, 0 --> no stock 1 one stock.
           P[0][1] = Integer.MIN_VALUE;
           for(int i = 1;i <= l;i++) {
               P[i][0] = Math.max(P[i-1][1]+prices[i-1]/* sold it*/, P[i-1][0]/* rest */);// no activity.
               P[i][1] = Math.max(P[i-1][1]/*rest*/,(( i == 1)?0:P[i-2][0])-prices[i-1]/*You could sell only on i-2 or before */);
           }
           return P[l][0];
    }

    public static void main(String[] args) {
        BestStockPrice1 bsp = new BestStockPrice1();
        int[] A = {1,2,3,0,2};
        bsp.maxProfit(A);
    }
}
