/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent
 lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
  A cell is part of an island.

  Input:
11000
11000
00100
00011

Output: 3

Looked at solution. Basically first 1 is an island. Convert around it to zero, and continue if its 1.
Also use 2 to mark visited.
 */

public class NumberOfIsland {
    int l = 0;
    int w = 0;

    public int numIslands(int[][] grid) {
        if ((grid == null) || (grid.length == 0)) return 0;
       int nIsland = 0;
       l = grid.length;
       w = grid[0].length;
        for(int i = 0; i < grid.length;i++) {
            for(int j = 0; j < grid[0].length;j++) {
                if (grid[i][j] == 1) {
                    nIsland++;
                    cleanUp(grid,i,j);
                }
            }
        }
        return nIsland;
    }

    private void cleanUp(int[][] g,int i, int j) {
        if((i <0) || (i >= l) || (j < 0) || (j >= w) || (g[i][j] != 1)) return;
        g[i][j] = 0;
        cleanUp(g,i+1,j);
        cleanUp(g,i-1,j);
        cleanUp(g,i,j+1);
        cleanUp(g,i,j-1);
    }

    public static void main(String[] args) {
        int[][] iA = {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};
        NumberOfIsland nIsd = new NumberOfIsland();
        System.out.println(nIsd.numIslands(iA));
    }
}
