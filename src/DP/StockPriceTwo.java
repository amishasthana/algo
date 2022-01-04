package DP;
/*
714. Best Time to Buy and Sell Stock with Transaction Fee
Medium

Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:

Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

Note:
0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
 */
public class StockPriceTwo {
    public int maxProfit1(int[] prices, int fee) {
        if ( (prices == null) || ( prices.length <= 1) ) return 0;
        int n = prices.length;
        int[][] dp = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {// starting dp thing.
            dp[i][0] = dp[i-1][0];
            for( int s = 1; s <=  i; s++) { // the other index, when we bought the stock which is still there.
                if ( s == i ) { // stock bought at current index. Means there was no stock left before coming,
                    dp[i][s] = dp[i-1][0];
                } else {
                    dp[i][s] = dp[i-1][s];// else the stock was bought before.
                }

                dp[i][0] = Math.max(dp[i][0], dp[s-1][0]+(prices[i-1] - (prices[s-1] + fee) ));//selling on ith day stock bought on day s.

            }// I f
        }// O F
        return dp[n][0];
    }

    public int maxProfit2(int[] prices, int fee) {
        if ( (prices == null) || ( prices.length <= 1) ) return 0;
        int n = prices.length;
        int[] dp = new int[n+1];// What is the max profit till this point, and stock i is bouht.
        for(int i = 1; i <= n; i++) {// starting dp thing.
            dp[i] = dp[i-1];
            for( int s = 1; s <=  i; s++) { // the other index, when we bought the stock which is still there.
                dp[i] = Math.max(dp[i], dp[s-1]+(prices[i-1] - (prices[s-1] + fee) ));//selling on ith day stock bought on day s.

            }// I f
        }// O F
        return dp[n];
    }

    public int maxProfit(int[] prices, int fee) {
        if ( (prices == null) || ( prices.length <= 1) ) return 0;
        int n = prices.length;
        long[][] dp = new long[2][2];
        /* What is the max profit (- (bought+2) + sold). First index is for previous/current day. That way no need to track the stock.
           Second index 0 : --> Nothing left. 1 : Stock is there.
         */
        dp[0][0] = 0; dp[0][1] = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {// starting dp thing.
            dp[1][0] = Math.max(dp[0][0], dp[0][1]+(prices[i]-fee));//Sell has fee.
            dp[1][1] = Math.max(dp[0][1], dp[0][0]-prices[i]);//Bought.
            dp[0][0] = dp[1][0];
            dp[0][1] = dp[1][1];
        }// O F
        return (int)dp[0][0];
    }

    public static void main(String[] args) {
        StockPriceTwo stp = new StockPriceTwo();
        int[] A = {1, 3, 2, 8, 4, 9};
        int[] A1 = {1, 3, 7, 5, 10, 3};
        System.out.println(stp.maxProfit(A,2));
    }

}
