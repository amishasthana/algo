package DP;
/*
474. Ones and Zeroes
Medium

In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:

    The given numbers of 0s and 1s will both not exceed 100
    The size of given string array won't exceed 600.



Example 1:

Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”



Example 2:

Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".



 */
public class OnesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] S = new int[m+1][n+1];//Discard zero.
        int MW = (m+1)*(n+1);
        for(String s : strs) {
            int[] ZO = getZO(s);

            for(int wz = m; wz >= ZO[0];wz-- ) {
                for(int w1 = n; w1 >= ZO[1];w1-- ) {
                    S[wz][w1] = Math.max( S[wz][w1] , 1 + S[wz - ZO[0]][w1 - ZO[1]]);
                }
            }

        }// For each string.
        return S[m][n];
    }

    private int[] getZO(String s) {
        int[] A = new int[2];
        for(char c : s.toCharArray()) {
            if (c == '0') {
                A[0]++;
            } else {
                A[1]++;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        String[] A = {"10", "0001", "111001", "1", "0"};
        String[] A1 = {"10", "0", "1"};
        OnesAndZeros oz = new OnesAndZeros();
        System.out.println(oz.findMaxForm(A,5,3));
        System.out.println(oz.findMaxForm(A1,1,1));
    }

}
