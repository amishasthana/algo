import java.util.*;
public class TTT {

    public static String HUMAN = "X";
    public static String AI = "0";
    public int[][] board = new int[3][3];
    public boolean isWinner = false;
    public boolean hMove = true;
    public String winner = null;
    static  Scanner sc = new Scanner(System.in);


    private boolean validToken(String token) {
        return true;
    }

    private boolean validPos(int row,int col) {
        return true;
    }

    /*
    Assume not null token and valid token
     */
    public void addToken(String token,int row,int col) {
        if (validToken(token) && validPos(row,col) ) {
            if (token.equals(HUMAN)) {
                board[row][col] = 1;
                hMove = false;
            } else {
                board[row][col] = 0;
                hMove = true;
            }
        }
    }

    public boolean legalMoveAvail() {
        for(int i = 0;i< 3;i++) {
            for(int j = 0;j< 3;j++) {
                if (board[i][j] == -1) return true;
            }
        }
        return false;
    }

    public void makeAIMove() {
        for(int i = 0;i< 3;i++) {
            for(int j = 0;j< 3;j++) {
                if (board[i][j] == -1) {
                    addToken(AI,i,j);
                    return;
                }
            }
        }
        throw new RuntimeException("No legal move for AI");
    }

    public void init() {
        for(int i = 0;i< 3;i++) {
            for(int j = 0;j< 3;j++) {
                board[i][j] = -1;
            }
        }
    }

    public void print() {
        for(int i = 0;i< 3;i++) {
            for(int j = 0;j< 3;j++) {
                if (j != 0) {
                    System.out.print("| ");
                }
               if (board[i][j] == -1) {
                   System.out.print("- ");
               } else if(board[i][j] == 1) {
                   System.out.print("X ");
               } else {
                   System.out.print("0 ");
               }
            }
            System.out.println();
        }
    }

    public void playGame() {

         while(legalMoveAvail() && !isWinner) {
             if (hMove ) {
                 int row = sc.nextInt();
                 int col = sc.nextInt();
                 addToken(HUMAN, row, col);
             } else {
                 makeAIMove();
             }
             print();

         }
    }

    public static void main(String[] args) throws RuntimeException {
        TTT tt = new TTT();
        tt.init();
        tt.playGame();

        //tt.addToken("X",0,1);
        //tt.print();
        //System.out.println(tt.legalMoveAvail());
       // tt.makeAIMove();

        //tt.print();

    }

}
