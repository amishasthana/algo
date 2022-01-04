/*
he n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 */

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class NQueen {
    BitSet rSet = null;
    BitSet cSet = null;
    int n;
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];//0 -> Free, 1 -> attacked for diagonal
        reset(chess);
        int[] queen = new int[n];
        this.n = n;
        rSet = new BitSet(n);
        cSet = new BitSet(n);
        for(int i = 0; i < queen.length;i++) {
            queen[i] = -1;
        }
        int qN = 0;
        int i = 0;
        List<List<String>> listOfQueeens = new LinkedList<>();
        while (queen[0] < n) {
            while (qN < n) {
                int nextPosAvail = findNextPos(i, chess);//Inclusive I.
                if (nextPosAvail == -1) {//Does not exist so backtrack.
                    qN--;//Go to previous queen.
                    if (qN == -1) break;
                    i = queen[qN];//Start finding next pos from i+1. Loop will increment it.
                    queen[qN] = -1;
                    markRowsAndCols(i, false);
                    markDiagonal(chess, i, false);
                } else {
                    i = nextPosAvail;
                    markRowsAndCols(i, true);
                    markDiagonal(chess, i, true);
                    queen[qN] = i;
                    qN++;
                }
                i++;
            }//one solution found
            if (queen[0] == -1) break;
            List<String> solList = createList(queen);
            listOfQueeens.add(solList);
            resetQueen(queen,chess);
            i = queen[0];
            qN = 0;
        }

        return listOfQueeens;
    }

    private void resetQueen(int[] queen,char[][] chess) {

        int cZeroQueenPos = queen[0];
        for(int i = 0;i < queen.length;i++) {
            markRowsAndCols(queen[i], false);
            markDiagonal(chess, queen[i], false);
            queen[i] = -1;
        }
        queen[0] = cZeroQueenPos+1;
    }

    private void reset(char[][] chess) {
        for(int j = 0;j < n;j++) {
            for(int k = 0;k < n;k++) {
                chess[j][k] = '0';
            }
        }
    }

    private List<String> createList(int[] q) {
        List<String> cSol = new LinkedList<>();
        int qIndex = 0;
        for(int j = 0;j < n;j++) {
            StringBuilder strB = new StringBuilder();
            for(int k = 0;k < n;k++) {
                int index = (j*n)+k;
                if (qIndex < n) {
                    if (q[qIndex] == index) {
                        strB.append('Q');
                        qIndex++;
                        continue;
                    }
                }
                strB.append('.');

            }
            cSol.add(strB.toString());
        }
        return cSol;
    }

    //From current pos.
    private int findNextPos(int i,char[][] chess) {

        int pos = -1;
        while (i < (n*n)) {
            int row = i/n;
            int col = i%n;
            if (rSet.get(row)) {
                i = (row+1)*n;
                //row++;
            } else if (cSet.get(col)) {
                i++;
            } else if (chess[row][col] == '1') {
                i++;
            } else {
                return i;
            }
        }
        return pos;
    }

    private void markDiagonal(char[][] chess,int i, boolean markAsAttacked) {
        int row = i/n;
        int col = i%n;
        //Two diagonals.
       /* Going from left to right */
        int difL = Math.min(row,col);
        for(int j = 0;j < n;j++ ) {
            int lRow = row-difL+j;
            int lCol = col-difL+j;
            if (!valid(lRow) || !valid(lCol)) break;
            if (markAsAttacked) {
                chess[lRow][lCol] = '1';
            } else {
                chess[lRow][lCol] = '0';
            }
        }

        /* Going from right to left */
        int diffR = Math.min(row,(n-1-col));
        for(int j = 0;j < n;j++ ) {
            int lRow = row-diffR+j;
            int lCol = col+diffR-j;
            if (!valid(lRow) || !valid(lCol)) break;
            if (markAsAttacked) {
                chess[lRow][lCol] = '1';
            } else {
                chess[lRow][lCol] = '0';
            }
        }
    }

    private boolean valid(int i) {
        return (i >=0) && (i < (n));
    }


    private void markRowsAndCols(int i, boolean markAsAttacked) {
        int row = i/n;
        int col = i%n;
        if (markAsAttacked) {
            rSet.set(row);
            cSet.set(col);
        } else {
            rSet.clear(row);
            cSet.clear(col);
        }
    }

}
