package Goog;

public class SquareArea {
    public int maximalSquare(char[][] matrix) {
        if ( ( matrix == null) || (matrix.length == 0) || (matrix[0].length == 0) ) return 0;

        int n = matrix.length; int m = matrix[0].length;
        int[][] DP = new int[n+1][m+1];// length of square.
        int maxA = 0;
        for( int i = 0; i < n; i++) {
            for( int j = 0; j < m; j++) {
                if (matrix[i][j] == '0') continue;
                if ((i == 0) || (j == 0)) {
                    DP[i+1][j+1] = 1;
                } else {
                    DP[i+1][j+1] =  Math.min( DP[i][j], Math.min(DP[i][j+1], DP[i+1][j] ) )+1;
                }
                maxA = Math.max(maxA,DP[i+1][j+1]);
            }
        }
        return maxA*maxA;

    }

    public static void main(String[] args) {
        SquareArea ss = new SquareArea();
        char[][] A = {{'1','1'},{'1','1'}};
        System.out.println(ss.maximalSquare(A));
    }

}
