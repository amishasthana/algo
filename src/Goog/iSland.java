package Goog;

public class iSland {
    public int numIslands(char[][] grid) {
        if ( (grid == null) || (grid.length == 0)) return 0;
        int num = 0;
        int n = grid.length; int m = grid[0].length;
        int[][] dir = { {-1,0}, {1,0}, {0,-1}, {0,1} };
        for(int i = 0; i < n;i++) {
            for(int j = 0; j < m;j++) {
                if ( grid[i][j] == '1') {
                    boolean isIsland = true;
                    grid[i][j] = '2';
                    //populate(grid,i,j);
                    for(int[] d : dir) {
                        int x  = i + d[0]; int y = j + d[1];
                        if ( (x < 0) || ( y < 0) || ( x >= grid.length) || (y >= grid[0].length) ) continue;
                        if (grid[x][y] == '2') {
                            isIsland = false; break;
                        }
                    }
                    if (isIsland) num++;

                }
            }

        }// end of OL
        return num;
    }

   public static void main(String[] args) {
       iSland is = new iSland();
       char[][] A = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};

       int n = is.numIslands(A);
       System.out.println(n);
   }

}
