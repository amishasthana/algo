import java.util.Arrays;

/*
Maximal Rectangle
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6


The Idea:
Calculate two values for each square
1) Sum of all continous 1 starting(including) itself going right.
2) Sum of all continous 1 starting(including) itself going down.
3) So now if one go diagonally from right hand side,
    X1,X2 and before one Y1,Y2.
    So what is maximum possible rectangle from X1,X2
    So what are the cases
    If X1 = Y1-2 and X2 = Y2 -2 and For (X-X1+1) have also right side X2, Y1 and (Y-X2+1)  have down side X1
     Complete envelop case.
     Else Min (X1-1,Y1) and Min(X2-1,Y2) is a square.

     X1 X2
     X3



 */
public class MaximalRectangle {


    public int maximalRectangle(char[][] matrix) {
        if ((matrix == null) || (matrix.length == 0) || (matrix[0].length == 0)) {
            return 0;
        }
        int m = matrix.length; int n = matrix[0].length;
        int[] h = new int[n];
        int[] l = new int[n];
        int[] r = new int[n];
        Arrays.fill(r,n-1);
        int mA = 0;

        for(int  i = 0; i < m; i ++ ) {
            int curL = 0; int curR = n-1;
            for(int j = 0; j < n ; j++) {
                if ( matrix[i][j] == '1') {
                    h[j]++;
                    l[j] = Math.max(l[j],curL);
                } else {
                    h[j] = 0;
                    l[j] = 0;
                    curL = j+1;
                }
            }// End of for
            for(int j = n-1; j >= 0; j--) {
                if ( matrix[i][j] == '1') {
                    r[j] = Math.min(r[j],curR);
                } else {
                    r[j] = n-1;
                    curR = j-1;
                }
            }

            for(int j = 0; j < n ; j++) {
                mA = Math.max(mA, h[j]*(r[j] - l[j] +1));
            }



        }//End of rows
        return mA;
    }

    public static void main(String[] args) {
        char[][] iA = {{1,1,1,1}};
    char[][] iA1 =
            {{'1','1','1','1','1','1','1','1'},
             {'1','1','1','1','1','1','1','0'},
             {'1','1','1','1','1','1','1','0'},
                    {'1','1','1','1','1','0','0','0'},
             {'0','1','1','1','1','0','0','0'}};

    char[][] iA2 = {{ 0, 0, 0,1, 0, 0, 0},
        { 0, 0, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 0}};




    MaximalRectangle maxRect = new MaximalRectangle();

   System.out.println(maxRect.maximalRectangle(iA1));
}
/*

[["1","1","1","1","1","1","1","1"],
["1","1","1","1","1","1","1","0"],
["1","1","1","1","1","1","1","0"],
["1","1","1","1","1","0","0","0"],
["0","1","1","1","1","0","0","0"]]
My output 30, answer is 21.
 */


}
