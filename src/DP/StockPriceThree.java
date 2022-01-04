package DP;

import java.util.Arrays;

/*
8.38
 */
public class StockPriceThree {
    public int maxProfit1(int[] prices) {
        int k = 2;
        if ( ( prices == null) || (prices.length <= 1) ) return 0;
        int n  = prices.length;
        if ( k >= n/2) {// k does not matter.
            int mP = 0; int max = Integer.MIN_VALUE;
            for( int i = n-1; i >= 1; i--) {
                max = Math.max(max,prices[i]);
                mP += Math.max(0,max - prices[i-1] );
            }
            return mP;
        }

        // One with K now.
        int[] Ti0 = new int[k+1];// No stock at end of day.
        int[] Ti1 = new int[k+1];//Stock at end of day.
        Arrays.fill(Ti1, Integer.MIN_VALUE);

        for(int i = 0 ; i < n ; i++) {
            //for(int ki = k; ki >= 1;ki--) {// 3,3,5,0,0,3,1,4
            for(int ki = 1; ki <= k;ki++) {
                Ti0[ki] = Math.max( Ti0[ki], Ti1[ki] + prices[i] );
                Ti1[ki] =  Math.max( Ti0[ki-1]- prices[i], Ti1[ki] );

            }//InnF
        }// OF
        return Ti0[k];
    }//

    public int maxProfit2( int[] prices) {
        int k = 2;
        if (k >= prices.length >>> 1) {
            int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

            for (int price : prices) {
                int T_ik0_old = T_ik0;
                T_ik0 = Math.max(T_ik0, T_ik1 + price);
                T_ik1 = Math.max(T_ik1, T_ik0_old - price);
            }

            return T_ik0;
        }

        int[] T_ik0 = new int[k + 1];
        int[] T_ik1 = new int[k + 1];
        Arrays.fill(T_ik1, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int j = k; j > 0; j--) {
                T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);
                T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
            }
        }

        return T_ik0[k];
    }

    public static void main(String[] args) {
        StockPriceThree stp = new StockPriceThree();
        int[] A = {3,3,5,0,0,3,1,4};
        System.out.println(stp.maxProfit1(A));
    }

}
