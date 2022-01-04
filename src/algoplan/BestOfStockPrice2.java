package algoplan;
/*
714. Best Time to Buy and Sell Stock with Transaction Fee
Medium

You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6



Constraints:

    1 <= prices.length <= 5 * 104
    1 <= prices[i] < 5 * 104
    0 <= fee < 5 * 104


 */
public class BestOfStockPrice2 {
    public int maxProfit(int[] prices, int fee) {
        int l = prices.length;
        long[][] P = new long[l+1][2];// start from 1. second index indicate, 0 --> no stock 1 one stock.
        P[0][1] = Integer.MIN_VALUE;
        for(int i = 1;i <= l;i++) {
            P[i][0] = Math.max(P[i-1][1]+prices[i-1]-fee/* sold it*/, P[i-1][0]/* rest */);// no activity.
            P[i][1] = Math.max(P[i-1][1]/*rest*/,(( i == 1)?0:P[i-1][0])-prices[i-1]/*You could sell only on i-2 or before */);
        }
        return (int)P[l][0];
    }
    public static void main(String[] args) {
        BestOfStockPrice2 bs = new BestOfStockPrice2();
        int[] A = {1,3,2,8,4,9};
        bs.maxProfit(A,2);
    }
}
