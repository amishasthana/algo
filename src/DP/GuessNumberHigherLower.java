package DP;
/*
375. Guess Number Higher or Lower II
Medium

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.

Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
Accepted
55,919
Submissions
140,756
Seen this question in a real interview before?

   if i guess n --> 0 or pay n
         if higher < n+1 to N>   n + P<n+1,N>
         if lower    n + P<n-1,1>



 */

import java.util.Arrays;

public class GuessNumberHigherLower {
    int[][] DP = null;
    public int getMoneyAmount(int n) {
        if ( n == 1) return 0;
        DP = new int[n+1][n+1];
        for(int[] a : DP ) {
            Arrays.fill(a,-1);
        }
        return guess(1,n);
        //return DP[0][n-1];

    }

    private int guess (int start,int end) {//both inclusive.
        if ( start >= end)  {
            //DP[start][end] = 0;
            return 0;
        }

        if (DP[start][end] != -1) return DP[start][end];
        int cPay = Integer.MAX_VALUE;
        for(int i = start; i <= end;i++) {
            cPay = Math.min(cPay, i+ Math.max(guess(start,i-1),guess(i+1,end)) );
        }
        DP[start][end] = cPay;
        return cPay;
    }// end of method.

    public static void main(String[] args) {
        GuessNumberHigherLower gnh = new GuessNumberHigherLower();
        System.out.println(gnh.getMoneyAmount(10));
    }

}
