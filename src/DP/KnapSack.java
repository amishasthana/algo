package DP;

public class KnapSack {
    public int knapSack(int W, int wt[], int val[], int n) {
        int[][] sol = new int[n+1][W+1];
        for(int i = 1;i <= n; i++) {
            for(int w = 1; w <= W; w++) {
                sol[i][w] = sol[i-1][w];
                if (wt[i-1] <= w) {
                    sol[i][w] = Math.max(sol[i][w],val[i-1]+sol[i-1][w-wt[i-1]]);//i-1 etc is only for indexing case.
                }
            }
        }
        return sol[n][W];
    }

    int knapsackBottom(int W, int wt[], int val[], int n)
    {
        int[] sol = new int[W+1];
        for(int i=0; i < n; i++)
            for(int j=W; j>=wt[i]; j--)
                sol[j] = Math.max(sol[j] , val[i] + sol[j-wt[i]]);
        return sol[W];
    }

    public int knapsackBottomSecond(int W, int wt[], int val[], int n) {
        int[] S = new int[W+1];// 0 discard. Index W solution for W.
        // It mean for weight W, with n = i what is the maximum.
        for(int i = 1; i < n; i++) {// Outer loop for each item
            for( int w = W; w >= wt[i]; w--) {// For each weight bottom down.
                /*
                    If Ith element weight is less then w (guranteed by loop).
                    Then one can add ith element.
                    The max will be value of Ith + whatever max one has calculated for Subtracted weight say "SW"
                    Note for subtracted weight we have calculated the max for (i-1) so far and its allready stored in S[SW]....
                    However you will update
                 */
                S[w] = Math.max(S[w], val[i]+S[w-wt[i]]) ;
            }
        }
        return S[W];

    }


    public static void main(String args[])
    {
        KnapSack ks = new KnapSack();
        int val[] = new int[]{6, 10, 12};
        int wt[] = new int[]{1, 2, 3};
        int  W = 5;
        int n = val.length;
        System.out.println(ks.knapSack(W, wt, val, n));
        System.out.println(ks.knapsackBottom(W, wt, val, n));
        System.out.println(ks.knapsackBottomSecond(W, wt, val, n));
    }
}
