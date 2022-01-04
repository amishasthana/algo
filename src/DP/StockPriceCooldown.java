package DP;

public class StockPriceCooldown {
    public int maxProfit(int[] prices) {
        long[][][] T = new long[2][2][2];
        // First txn and next txn. second 0 no stock 1 stock , 0 : Not sold anything today, 1 sold today.
        for( int i = 0; i < 2; i++) {
            T[i][1][i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < prices.length; i++) {
            T[1][0][0] = Math.max(T[0][0][0] , T[0][0][1] );
            T[1][0][1] =  T[0][1][0]+prices[i];
            T[1][1][0] = Math.max( T[0][1][0],T[0][0][0]-prices[i]);
            //T[i][1][1] = not needed.

            T[0][0][0] = T[1][0][0];
            T[0][0][1] = T[1][0][1];
            T[0][1][0] = T[1][1][0];
        }
        return (int)Math.max(T[0][0][0],T[0][0][1]);
    }

    public static void main(String[] args) {
        StockPriceCooldown spc = new StockPriceCooldown();
        int[] A = {1,2,3,0,2};
        System.out.println(spc.maxProfit(A));
    }

}
