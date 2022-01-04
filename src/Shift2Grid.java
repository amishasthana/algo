import java.util.ArrayList;
import java.util.List;

/*
1260. Shift 2D Grid

    User Accepted: 2600
    User Tried: 2818
    Total Accepted: 2656
    Total Submissions: 4838
    Difficulty: Easy

Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.

In one shift operation:

    Element at grid[i][j] becomes at grid[i][j + 1].
    Element at grid[i][m - 1] becomes at grid[i + 1][0].
    Element at grid[n - 1][m - 1] becomes at grid[0][0].

Return the 2D grid after applying shift operation k times.



Example 1:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]

Example 2:

Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]

Example 3:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]



Constraints:

    1 <= grid.length <= 50
    1 <= grid[i].length <= 50
    -1000 <= grid[i][j] <= 1000
    0 <= k <= 100

Java

 */
public class Shift2Grid {
    /*
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
          for(int ind = 0; ind < k;ind++) {
              int firstElem = grid[0][0];
              for (int i = 0; i < grid.length; i++) {
                  for (int j = 0; j < (grid[0].length-1); j++) {
                       grid[i][j + 1] = grid[i][j];
                  }//End of columns.
                  if (i < (grid.length-1)) {
                      grid[i + 1][0]=grid[i][grid[0].length - 1] ;
                  }
              }//End of rows
              grid[0][0] = grid[grid.length-1][grid[0].length-1];
          }//End of k.
          return listFromArray(grid);
    }


    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        for(int ind = 0; ind < k;ind++) {
            int n = grid.length; int m = grid[0].length;
            int lastElem = grid[n-1][m-1];
            for (int i = ((m*n)-1); i > 0; i--) {
                int j = i-1;
                grid[i/m][i%m] = grid[(i-1)/m][(i-1)%m];
            }//End of rows
            grid[0][0] = lastElem;
        }//End of k.
        return listFromArray(grid);
    }
    */


    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> retList = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;
        int tElem = n*m;
        k = k%tElem;
        int startIndex = tElem-k;
        for(int i = 0; i < n;i++) {
            List<Integer> colList = new ArrayList<>();
            for(int j = 0; j < m;j++) {

                startIndex =  startIndex%tElem;
                int x  = startIndex/m;
                int y = startIndex%m;
                //System.out.println("x="+x+" y = "+y+" startIndex "+startIndex);
                colList.add(grid[x][y]);
                startIndex++;

            }
            retList.add(colList);
        }
        return retList;
    }



/*
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        if ( k == 0) return listFromArray(grid);
        //for(int ind = 0; ind < k;ind++) {
            int n = grid.length; int m = grid[0].length;
            int lastElem = grid[n-1][m-1];
            for (int i = ((m*n)-1); i > 0; i--) {
                int j = i-1;
                grid[i/m][i%m] = grid[(i-1)/m][(i-1)%m];
            }//End of rows
            grid[0][0] = lastElem;
       // }//End of k.
        return listFromArray(grid);
    }


    private List<List<Integer>> listFromArray(int[][] grid) {
        List<List<Integer>> retList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            List<Integer> colList = new ArrayList<>();
            for (int j = 0; j < (grid[0].length); j++) {
                colList.add(grid[i][j]);
            }//E
            retList.add(colList);
        }
        return retList;
    }
    */

    public static void main(String[] args) {
        //[[1,2,3],[4,5,6],[7,8,9]], k = 1
        //int[][] iA = {{1,2,3},{4,5,6},{7,8,9}};
        //int[][] iA = {{3,8,1,9},{19,7,2,5},{4,6,11,10},{12,0,21,13}};
        int[][] iA = {{1},{2},{3},{4},{7},{6},{5}};
        Shift2Grid sg = new Shift2Grid();
        System.out.println(sg.shiftGrid(iA,23));
    }
}
