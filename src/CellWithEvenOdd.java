/*
1252. Cells with Odd Values in a Matrix

    User Accepted: 2823
    User Tried: 2910
    Total Accepted: 2876
    Total Submissions: 3592
    Difficulty: Easy

Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.

Return the number of cells with odd values in the matrix after applying the increment to all indices.



Example 1:

Input: n = 2, m = 3, indices = [[0,1],[1,1]]
Output: 6
Explanation: Initial matrix = [[0,0,0],[0,0,0]].
After applying first increment it becomes [[1,2,1],[0,1,0]].
The final matrix will be [[1,3,1],[1,3,1]] which contains 6 odd numbers.

Example 2:

Input: n = 2, m = 2, indices = [[1,1],[0,0]]
Output: 0
Explanation: Final matrix = [[2,2],[2,2]]. There is no odd number in the final matrix.

 */
public class CellWithEvenOdd {

    public int oddCells(int n, int m, int[][] indices) {
        if((indices == null) || (indices.length == 0)) return 0;
        boolean[] oRows = new boolean[n];
        boolean[] oCols = new boolean[m];
        int crossCasesEven = 0;
        for(int i = 0; i < indices.length;i++) {
            int rInd = indices[i][0];
            int cInd = indices[i][1];
            oRows[rInd] = !oRows[rInd];
            oCols[cInd] = !oCols[rInd];

        }
        int tOdd = 0; int oddRow = 0; int oddCol = 0;
        for(int i = 0; i < oRows.length;i++) {
            if (oRows[i]) {
                tOdd += m;
                oddRow++;
            }
        }
        for(int i = 0; i < oRows.length;i++) {
            if (oCols[i]) {
                tOdd += n;
                oddCol++;
            }
        }
        return (tOdd-2*(oddCol*oddRow));

    }

    public static void main(String[] args) {
        CellWithEvenOdd cwEO = new CellWithEvenOdd();
        int[][] iA = {{0,1},{1,1}};
        //int[][] iA = {{1,1},{0,0}};
        System.out.print(cwEO.oddCells(2,3,iA));
    }




}
