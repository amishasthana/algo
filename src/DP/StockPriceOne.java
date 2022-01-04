package DP;

import java.util.Stack;

public class StockPriceOne {
    public int maxProfit1(int[] prices) {
        if ( ( prices == null) || (prices.length <= 1)  ) return 0;
        Stack<Integer> st = new Stack<>();
        int n = prices.length;
        st.push(prices[n-1]);
        int maxP = 0; int i = n-2 ;
        for(; i  >= 0; i--) {
            if (!st.isEmpty() &&  ( prices[i] <= st.peek() ) ) {
                maxP = Math.max( maxP,  st.peek() - prices[i] );
            } else {
                while (!st.isEmpty()) st.pop();
                st.push(prices[i]);
            }
        }
        return maxP;
    }

    public int maxProfit(int[] prices) {
        if ( ( prices == null) || (prices.length <= 1)  ) return 0;

        int n = prices.length;
        int maxSoFar = prices[n-1];
        int maxP = 0;
        for(int i = n-2; i  >= 0; i--) {
            if ( prices[i] <= maxSoFar  ) {
                maxP = Math.max( maxP,  maxSoFar - prices[i] );
            } else {
                maxSoFar = prices[i];
            }
        }
        return maxP;
    }

    public static void main(String[] args) {
        StockPriceOne stp = new StockPriceOne();
        int[] A = {7,1,5,3,6,4};
        System.out.println(stp.maxProfit(A));
    }

}
