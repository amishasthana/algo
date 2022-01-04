/*
Given a sorted Matrix,
Each row is sorted and
 each column is sorted.

given a number find the index of it.
 */
public class SortedMatrixSerach {

    /*
        Find the index for number V.
        null for case not found
     */
    public int[] getPos(int[][] grid,int v) {

        if ((grid == null) || (grid[0].length == 0)) {
            return null;
        }
        return getPosition(grid,v,0,0,grid.length-1,grid[0].length-1);

    }

    private int[] getPosition(int[][] grid,int v,int x1,int y1,int x2,int y2) {// All index inclusive.
        int[] pos = new int[2];
        if ((x1 > x2) || (y1> y2)) {
            return null;
        }
        if ((x1 <0) || (y1 < 0) || (x2 >= grid.length) || (y2 >= grid[0].length)) {
            return null;
        }

        if ((grid[x1][y1] == v)) {
            pos[0] = x1; pos[1] = y1;
            return pos;
        } else if (grid[x2][y2] == v) {
            pos[0] = x2; pos[1] = y2;
            return pos;
        } else if((grid[x1][y1] > v) || (grid[x2][y2] < v)) {
            return null;//Not in this matrix.
        }
        int midX = x1+(x2-x1)/2;//1,3 --> 2; 1/2 --> 1
        int midY = y1+(y2-y1)/2;
        pos = getPosition(grid,v,midX+1,y1,x2,midY);//Left bottom
        if (pos == null) {
            pos = getPosition(grid,v,x1,midY+1,midX,y2);//right  top
        }
        if (pos == null) {
            if (grid[midX][midY] >= v) {
                pos = getPosition(grid,v,x1,y1,midX,midY);//Left top
            } else {
                pos = getPosition(grid,v,midX+1,midY+1,x2,y2);//Right bottom
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        int[][] grid = {
                        {1,2,4,5,9,13},
                        {7,9,9,9,9,15},
                {8,10,24,27,27,30}
                       };
        SortedMatrixSerach sms = new SortedMatrixSerach();
        int[] pos = sms.getPos(grid,13);
        if (pos == null) {
            System.out.println("Element does not exist");
        } else {
            System.out.println(pos[0]+","+pos[1]+" and number" +grid[pos[0]][pos[1]]);
        }

    }

}
