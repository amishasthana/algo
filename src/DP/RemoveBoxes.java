package DP;
/*
546. Remove Boxes
Hard

Given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]

Output:

23

Explanation:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
----> [1, 3, 3, 3, 1] (1*1=1 points)
----> [1, 1] (3*3=9 points)
----> [] (2*2=4 points)

Note: The number of boxes n would not exceed 100.

 */
public class RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        if ( ( boxes == null) || (boxes.length == 0) ) return 0;
        int n = boxes.length;
        int[][] dp = new int[n][n];

        for( int len = 1 ; len< n; len++) {

            for( int left = 0; left + len < n ; left++)  {
                int right = left + len;
                for(int m = left; m < right ;) {
                    int mLen = 1;
                    while ( ((m+mLen) < n) && (boxes[m] == boxes[m+mLen])) mLen++;
                    dp[left][right] = Math.max(dp[left][right], dp[left][m] + dp[m+mLen][right] +  (mLen+1)*(mLen+1));
                    m = m+mLen;
                }
            }
        }// end of outermost loop.
        return dp[0][n];
    }

    public static void main(String[] args) {
        //int[] A = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        int[] A1 = {1, 1};
        RemoveBoxes rb = new RemoveBoxes();
        System.out.println(rb.removeBoxes(A1));
    }

}
