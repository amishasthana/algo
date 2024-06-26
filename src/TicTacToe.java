/*
c-tac-toe is played by two players A and B on a 3 x 3 grid.

Here are the rules of Tic-Tac-Toe:

    Players take turns placing characters into empty squares (" ").
    The first player A always places "X" characters, while the second player B always places "O" characters.
    "X" and "O" characters are always placed into empty squares, never on filled ones.
    The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
    The game also ends if all squares are non-empty.
    No more moves can be played if the game is over.

Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark their respective character in the order in which A and B play.

Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".

You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.


 */
public class TicTacToe {
    public static final  String DRAW = "Draw";
    public static final  String PENDING = "Pending";
    public String tictactoe(int[][] moves) {
        char[][] tt  = new char[3][3];
        for(int i = 0; i < moves.length;i++) {
            char move = 'A';
            if (i%2 == 1) move = 'B';
            tt[moves[i][0]][moves[i][1]] = move;
        }
        String findWinner = findWinner(tt);
        if (DRAW.equals(findWinner) || "".equals(findWinner)) {
            if (moves.length < 9) return PENDING;
        }
        return findWinner;
    }

    private String findWinner(char[][] cArr) {
        //Find row winner
        for(int i = 0;i < 3;i++) {
            if ((cArr[i][0] == cArr[i][1]) && (cArr[i][1] == cArr[i][2])) {
                return cArr[i][0]+"";
            }
        }
        //Find row winner
        for(int j = 0;j < 3;j++) {
            if ((cArr[0][j] == cArr[1][j]) && (cArr[1][j] == cArr[2][j])) {
                return cArr[0][j]+"";
            }
        }
        //Diagnol
        if ((cArr[0][0] == cArr[1][1]) && (cArr[1][1] == cArr[2][2])) {
            return cArr[1][1]+"";
        }
        if ((cArr[0][2] == cArr[1][1]) && (cArr[1][1] == cArr[2][0])) {
            return cArr[1][1]+"";
        }
        return DRAW;
    }
}
