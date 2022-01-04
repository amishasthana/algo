import java.util.Arrays;

/*
1326. Minimum Number of Taps to Open to Water a Garden
Hard

There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).

There are n + 1 taps located at points [0, 1, ..., n] in the garden.

Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed)
means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.

Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.

Input: n = 5, ranges = [3,4,1,1,0,0]
Output: 1
Explanation: The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]


We actually need to water the whole segment, instead of n + 1 point.
The taps with value 0 can water nothing.

Solution 1: Brute Force DP

dp[i] is the minimum number of taps to water [0, i].
Initialize dp[i] with max = n + 2
dp[0] = [0] as we need no tap to water nothing.

Find the leftmost point of garden to water with tap i.
Find the rightmost point of garden to water with tap i.
We can water [left, right] with onr tap,
and water [0, left - 1] with dp[left - 1] taps.

ELEGENT >>>
public int minTaps(int n, int[] A) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 2);
        dp[0] = 0;
        for (int i = 0; i <= n; ++i)
            for (int j = Math.max(i - A[i] + 1, 0); j <= Math.min(i + A[i], n); ++j)
                dp[j] = Math.min(dp[j], dp[Math.max(0, i - A[i])] + 1);
        return dp[n]  < n + 2 ? dp[n] : -1;
    }

 */
public class MinTapForWater {
    public int minTaps(int n, int[] A) {
        int[] dp = new int[n + 1];//It basically indicate that till ith what is the least amount of taps.
        Arrays.fill(dp,n+2);
        dp[0] = 0;//Till zero no tap.
        for(int i = 0; i < A.length;i++) {
            // [i - ranges[i], i + ranges[i]] if it was open.
            int start = Math.max(0,(i-A[i]+1));// for Ith tap it can start from.
            // Note we dont care below zero, Also we start on 1. as we will have till that point.
            //Basically left boundary = (i-A[i]) so +1 is what we calculate.
            int end =  Math.min(n,(i+A[i]));
            int usingThisTap = dp[Math.max(0,i-A[i])]+1;// Previous tap + 1.
            for(int j = start;j <= end; ++j) {
                dp[j] = Math.min(usingThisTap,dp[j]);//Basically each tap tries to update to latest min.
            }
        }
        return (dp[n] == (n+2))?-1:dp[n];//Did not got updated.
    }
}
