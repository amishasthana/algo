/*
Sudoku

*/


import java.util.HashSet;
import java.util.Set;

public class Sudoku {

    class Cell {
        int r;
        int c;
        int v;
        boolean isConst;
    }

    private Cell[][] sud = new Cell[9][9];

    public void initSudoku(int[][][] values) {
        for(int i = 0;i < sud.length;i++) {
            for(int j = 0;j < sud.length;j++) {
                sud[i][j] = new Cell();
            }
        }
        //Initialize the values.
    }

    /*
      For a cell -->
        a) What is the next valid value.
        b) Go to next cell.
        c) If no valid value, put it 0/-1 and go back one cell.
    */
    public void solveSudoku() {
        boolean isBackTrack = false;
        for(int i = 0; i < sud.length*sud[0].length;) {
            Cell cCell = getNextCell(i);
            if(isBackTrack) {//Need to go further back.
                if (cCell.isConst) {
                    i--;
                    continue;
                }
                isBackTrack = false;
            }

            if (cCell.isConst) {
                i++;
                continue;
            }
            if (!popNextValidValue(cCell)) {//Revert
                cCell.v = 0;
                isBackTrack = true;
                if (i == 0) {
                    System.out.println("No result possible");
                    return;
                }
                i--;
            }
        }//End of for
        printSudoku();
    }//End of solveSudoku

    private void printSudoku() {
        for(int i = 0; i < sud.length;i++) {
            for(int j = 0; j< sud[0].length;j++) {
                System.out.print(sud[i][j]+ " ");
            }
            System.out.println();
        }
    }

    private Cell getNextCell(int i) {
        int c = i%9;
        int r = i/9;
        return sud[r][c];
    }

    private boolean popNextValidValue(Cell c) {
        c.v = getNextValidValue(c);
        if (c.v == 0) {
            return false;
        }
        return true;
    }

    private int getNextValidValue(Cell c) {
        Set<Integer> valuesinSquare = getSetOfValue(c);
        for(int i = (c.v ==0)?1:c.v;i <= 9;i++) {
            if (valuesinSquare.contains(i)) continue;
            return i;
        }
        return 0;
    }

    private Set<Integer> getSetOfValue(Cell c) {
        int rowStart = (c.r / 3) *3;
        int colStart = (c.c /3 ) * 3;
        Set<Integer> setOfValues = new HashSet<>();
        for(int i = rowStart; i < (rowStart+3);i++) {
            for(int j = colStart; j < (colStart+3);j++) {
                setOfValues.add(sud[i][j].v);
            }
        }
        return setOfValues;
    }

    public static void main(String[] args) {
        Sudoku sud = new Sudoku();
        sud.initSudoku(null);
        sud.solveSudoku();
    }





}//End of class.

