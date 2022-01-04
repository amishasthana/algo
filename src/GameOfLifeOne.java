/*
289. Game of Life
Medium

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Solution in place.
If old 1 and new 1 leave as 1.
If old 0 and new 0 leave as 0.
If old 1 and new 0 leave as -1
if old 0 and new 1 leave as 2
 */
public class GameOfLifeOne {

    public void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length;i++) {
            for(int j = 0; j < board[0].length;j++) {
                convert(board,i,j);
            }
        }
        for(int i = 0; i < board.length;i++) {
            for(int j = 0; j < board[0].length;j++) {
                if (board[i][j] == -1) board[i][j] = 0;
                if (board[i][j] == 2) board[i][j] = 1;
            }
        }

    }

    private void convert(int[][] board,int i, int j) {
        int cStatus = board[i][j];
        int nLiveNeighbourCells = getLiveNCells(board,i,j);
        if (cStatus == 1) {
            if ((nLiveNeighbourCells >= 2) && (nLiveNeighbourCells <= 3)) {
                return;
            } else {
                board[i][j] = -1;
            }
        } else {
            if (nLiveNeighbourCells == 3) {
                board[i][j] = 2;
            }
        }
    }

    /*

     */
    private int getLiveNCells(int[][] board,int r, int c) {
        int nLives = 0;
        for(int i = r-1; i < r+2;i++) {
            for(int j = c-1; j < c+2;j++) {
                if ((i == r) && (c == j)) continue;
                int cVal = getHelper(board,i,j);
                if ((cVal == 1) || (cVal == -1)) {
                    nLives++;
                }
            }
        }
        return nLives;
    }

    private int getHelper(int[][] board,int r,int c) {
        if ((r < 0) || (c < 0) || (r >= board.length) || (c >= board[0].length)) {
            return -3;
        }
        return board[r][c];
    }

}
