package DP;

public class MinGrid {
    public int minPathSum(int[][] grid) {
        if ( (grid == null) || ( grid.length == 0) ) return 0;
        int sum = 0;
        for ( int i = 0; i < (grid.length) ; i++) {
            for( int j = 0; j < grid[0].length; j++) {
                if ((i == 0) && (j == 0)) continue;
                int mT = ( (i -1) >= 0)?grid[i-1][j]:Integer.MAX_VALUE;
                int mL  = ( (j -1) >= 0)?grid[i][j-1]:Integer.MAX_VALUE;
                grid[i][j] += Math.min(mT,mL);
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        int[][] A = {{1,3,1},{1,5,1},{4,2,1}};
        MinGrid mg = new MinGrid();
        System.out.println(mg.minPathSum(A));
    }

}
