package DP;

/*
Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.



Example 1:

Input: n = 2, m = 3
Output: 3
Explanation: 3 squares are necessary to cover the rectangle.
2 (squares of 1x1)
1 (square of 2x2)

Example 2:

Input: n = 5, m = 8
Output: 5

Example 3:

Input: n = 11, m = 13
Output: 6



Constraints:

    1 <= n <= 13
    1 <= m <= 13

https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/discuss/414260/8ms-Memorized-Backtrack-Solution-without-special-case!


 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TilingSquare {
    int[][] S =  null;
    public int tilingRectangleOne(int n, int m) {
        if ( ( n == 0) && (m == 0)) return 0;
        S = new int[n+1][m+1];
        //Arrays.fill(S,-1);
        return findS(n,m);
    }

    private int findS(int n,int m) {
        if ( S[n][m] != 0) return S[n][m];
        if ( n == m) { S[n][m] = 1; return S[n][m]; }
        if ( n == 1) { S[n][m] = m; return S[n][m]; }
        if ( m == 1) { S[n][m] = n; return S[n][m]; }
        int min = n*m;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                min = Math.min( (findS(i,j) + findS(n-i,m-j)), min);
            }
        }
        S[n][m] = min;
        return S[n][m] ;
    }


        Map<Long, Integer> set = new HashMap<>();
        int res = Integer.MAX_VALUE;
        public int tilingRectangle(int n, int m) {
            if (n == m) return 1;
            if (n > m) {
                int temp = n;
                n = m;
                m = temp;
            }
            dfs(n, m, new int[n + 1], 0);
            return res;
        }

        private void dfs(int n, int m, int[] h, int cnt) {
            if (cnt >= res) return;
            boolean isFull = true;
            int pos = -1, minH = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                if (h[i] < m) isFull = false;
                if (h[i] < minH) {//basically the one with least hight.
                    pos = i;
                    minH = h[i];
                }
            }
            if (isFull) {
                res = Math.min(cnt, res);
                return;
            }

            long key = 0, base = m + 1;
            for (int i = 1; i <= n; i++) {
                key += h[i] * base;
                base *= m + 1;
            }
            if (set.containsKey(key) && set.get(key) <= cnt) return;
            set.put(key, cnt);

            int end = pos;
            while (end + 1 <= n && h[end + 1] == h[pos] && (end + 1 - pos + 1 + minH) <= m) end++;
            for (int j = end; j >= pos; j--) {
                int curH = j - pos + 1;

                int[] next  = Arrays.copyOf(h, n + 1);
                for (int k = pos; k <= j; k++) {
                    next[k] += curH;
                }
                dfs(n, m, next, cnt + 1);
            }

        }


    public static void main(String[] args) {
        TilingSquare ts = new TilingSquare();
        System.out.println("ts "+ts.tilingRectangle(2,3));
    }



}
