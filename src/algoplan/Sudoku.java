package algoplan;

import java.util.*;
public class Sudoku {

    /*
    MUCH EASIER
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }
}
     */
    public void solveSudoku(char[][] board) {
        int len = board.length;
        List<BitSet> bRows = new LinkedList<>();
        List<BitSet> bCols = new LinkedList<>();
        List<BitSet> bBox = new LinkedList<>();
        for(int i = 0; i < len;i++) {
            bRows.add(new BitSet(len));
            bCols.add(new BitSet(len));
            bBox.add(new BitSet(len));
        }
        BitSet preSetValues = new BitSet(81);
        for(int i = 0; i < len;i++) {
            for(int j = 0; j < len;j++) {
                if (board[i][j] != '.' ) {
                    int v = board[i][j]-'1';//0 indexed
                    System.out.println(i+" : "+j+" val "+v);
                    preSetValues.set(i*len+j);//0 --80
                    bRows.get(i).set(v);
                    bCols.get(j).set(v);
                    bBox.get(getBox(i,j)).set(v);
                }
            }
        }
        solve(board,0,bRows,bCols,bBox,preSetValues);
    }

    //pos --> 0 -80
    private boolean solve(char[][] board,int pos,List<BitSet> bRows ,List<BitSet> bCols,List<BitSet> bBox,BitSet preSetValues ){
        if (pos > 80) return true;
        int len = board.length;
        if (!preSetValues.get(pos) ) { //Not allready set value
            int x = pos/len; int y = pos%len;
            for(int i = 0; i < len;i++) {
                //all three condition pass
                if ( !bRows.get(x).get(i) && !bCols.get(y).get(i) && !bBox.get(getBox(x,y)).get(i)) {
                    char c = (char) ('0'+(i+1));
                    board[x][y] = c;
                    bRows.get(x).set(i);
                    bCols.get(y).set(i);
                    bBox.get(getBox(x,y)).set(i);
                    if (solve(board,pos+1,bRows,bCols,bBox,preSetValues)) return true;
                    board[x][y] = '.';
                    bRows.get(x).flip(i);
                    bCols.get(y).flip(i);
                    bBox.get(getBox(x,y)).flip(i);
                }
            }
            return false;
        } else {//allready set
            if (solve(board,pos+1,bRows,bCols,bBox,preSetValues)) return true;
            return false;
        }
    }

    private int getBox(int i, int j) {
        if (i < 3) {
            if (j < 3) return 0;
            if (j < 6) return 3;
            return 6;
        } else if (i < 6) {
            if (j < 3) return 1;
            if (j < 6) return 4;
            return 7;
        } else {
            if (j < 3) return 2;
            if (j < 6) return 5;
            return 8;
        }
    }

}
