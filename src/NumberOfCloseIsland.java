import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
1254. Number of Closed Islands

    User Accepted: 1358
    User Tried: 1602
    Total Accepted: 1377
    Total Submissions: 2494
    Difficulty: Medium

Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.



Example 1:

Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation:
Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:

Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1

Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2



Constraints:

    1 <= grid.length, grid[0].length <= 100
    0 <= grid[i][j] <=1

Solution : Find number of island. As you consume put them as 2 to represent visited.
Also track which is the max/min x and y. If it reach the end of rectangle, its not closed.

IMP : HERE 0 is LAND. Genrally its opposite.

 */
public class NumberOfCloseIsland {
    public static int LAND = 0;
    public static int WATER = 1;
    public static int VISITED = 2;//Has to be LAND.
    private int[][] grid = null;

    public int closedIsland(int[][] grid) {
          int nCloseIsl = 0;
          if ((grid != null) && (grid.length >0)) {
              this.grid = grid;
              for(int i = 0; i < grid.length;i++) {
                  for(int j = 0; j < grid[0].length;j++) {
                      if(isCloseIsland(i,j)) {
                          nCloseIsl++;
                      }
                  }//Cols
              }//Rows
          }
          return nCloseIsl;
    }

    class Pos {
        int x;
        int y;

        public Pos(int i,int j) {
            x = i;
            y = j;
        }
    }

    private boolean isCloseIsland(int x,int y) {
        if (grid[x][y] == LAND) {
            int xMax = x; int xMin = x;
            int yMax = y; int yMin = y;
            Pos nPos = new Pos(x,y);
            Queue<Pos> qOfPos = new ArrayDeque<>();
            qOfPos.add(nPos);
            grid[x][y] = VISITED;
            while (!qOfPos.isEmpty()) {
                Pos cpos = qOfPos.poll();
                List<Pos> childL = getChild(cpos);
                for(Pos childPos : childL) {
                    grid[childPos.x][childPos.y] = VISITED;
                    if (xMax < childPos.x) {
                        xMax = childPos.x;
                    }
                    if (xMin > childPos.x) {
                        xMin = childPos.x;
                    }
                    if (yMax < childPos.y) {
                        yMax = childPos.y;
                    }
                    if (yMin > childPos.y) {
                        yMin = childPos.y;
                    }
                    qOfPos.add(childPos);
                }
            }
            if((xMax < (grid.length-1)) && (xMin > 0)
                && (yMax < (grid[0].length-1)) && (yMin > 0)) {
                return true;
            }

        }
        return false;
    }

    private List<Pos> getChild(Pos pos) {
        List<Pos> childList = new ArrayList<>();
        if (validCord(pos.x+1,pos.y) && (grid[pos.x+1][pos.y] == LAND)) {
            Pos rChild = new Pos(pos.x+1,pos.y);
            childList.add(rChild);
        }
        if (validCord(pos.x-1,pos.y) && (grid[pos.x-1][pos.y] == LAND)) {
            Pos lChild = new Pos(pos.x-1,pos.y);
            childList.add(lChild);
        }
        if (validCord(pos.x,pos.y+1) && (grid[pos.x][pos.y+1] == LAND)) {
            Pos bChild = new Pos(pos.x,pos.y+1);
            childList.add(bChild);
        }
        if (validCord(pos.x,pos.y-1) && (grid[pos.x][pos.y-1] == LAND)) {
            Pos tChild = new Pos(pos.x,pos.y-1);
            childList.add(tChild);
        }

        return childList;
    }

    private boolean validCord(int x,int y) {
        if ((x >= 0) && (x < grid.length) && (y >= 0) && (y < grid[0].length)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        NumberOfCloseIsland nci = new NumberOfCloseIsland();
        //int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        //int[][] grid = {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
        int[][] grid = {{1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1},
                {1,0,1,1,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,1,1,0,1},
                {1,0,0,0,0,0,1},
                {1,1,1,1,1,1,1}};
        System.out.println(nci.closedIsland(grid));
    }

}
