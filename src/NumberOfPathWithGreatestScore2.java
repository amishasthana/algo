import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1301. Number of Paths with Max Score

    User Accepted: 353
    User Tried: 638
    Total Accepted: 372
    Total Submissions: 1546
    Difficulty: Hard

You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.

You need to reach the top left square marked with the character 'E'.
The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'.
 In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.

Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect,
 and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.

In case there is no path, return [0, 0].



Example 1:

Input: board = ["E23","2X2","12S"]
Output: [7,1]

Example 2:

Input: board = ["E12","1X1","21S"]
Output: [4,2]

Example 3:

Input: board = ["E11","XXX","11S"]
Output: [0,0]



Constraints:

    2 <= board.length == board[i].length <= 100

The idea is that at each square you calculate the following:
a) Max possible till this square.
b) For that max what is the number of ways.

So at end you get the max. and number of ways both.
 */
public class NumberOfPathWithGreatestScore2 {

    static char X = 'X';
    static char S = 'S';
    static char E = 'E';
    static int MODULO = 10^9 + 7;

    int[][] maxValue = null;
    int[][] nWays = null;
    int size = 0;
    List<String> bList = null;
    public int[] pathsWithMaxScore(List<String> board) {
        int[] res = new int[2];
        size = board.size();
        maxValue = new int[size][size];
        nWays = new int[size][size];
        maxValue[0][0] = 0;
        nWays[0][0] = 1;
        bList = board;
        int i = 1;
        int tSquares = size*size;
        while (i < tSquares) {
            findAndPopulateValueAndWays(i++);
        }
        if (maxValue[size-1][size-1] == -1) {
                res[0] = 0;
                res[1] = 0;
        } else {
                res[0] = maxValue[size-1][size-1];
                res[1] = nWays[size-1][size-1];
        }
        debug();
        return res;
    }
    /*
          c3   c2
          c1   c0

     */
    private void findAndPopulateValueAndWays(int i) {
        int x = getX(i);
        int y = getY(i);
        maxValue[x][y] = getValue(getCharacter(x,y));
        if (maxValue[x][y] == -1) {
            maxValue[x][y] = -1;
            nWays[x][y] = 0;
            return;
        }
            int c0 = getValueFromMax(x-1,y-1);
            int c1 = getValueFromMax(x,y-1);
            int c2 = getValueFromMax(x-1,y);
            int max = Math.max(Math.max(c0,c1),c2);
            if ((max == -1) ) {
                maxValue[x][y] = -1;
                nWays[x][y] = 0;
                return;
            } else  {
                int nCWay = 0;
                if (c0 == max)  nCWay = nCWay+nWays[x-1][y-1];
                if (c1 == max)  nCWay = nCWay+nWays[x][y-1];
                if (c2 == max) nCWay = nCWay+nWays[x-1][y];
                maxValue[x][y] = (max+ maxValue[x][y])%MODULO;
                nWays[x][y] = nCWay%MODULO;;
            }
    }

    private int getValueFromMax(int x,int y) {
        if (   (x <0) || (x >= size)
                || (y <0) || (y >= size)) {
            return -1;
        }
        return maxValue[x][y];
    }

    private int getValue(char c) {
        if ((c == X) ) {
            return -1;
        }
        if ((c == E) || (c == S)) {
            return 0;
        }
        return (c - '0');
    }

    private char getCharacter(int x, int y) {
        if (   (x <0) || (x >= size)
             || (y <0) || (y >= size)) {
            return 'X';
        }
        String s = bList.get(size-y-1);
        return s.charAt(size-x-1);
    }

    private int getX(int i) {
        return i%size;
    }

    private int getY(int i) {
        return i/size;
    }

    private void debug() {
        for(int j = size-1; j >= 0;j--) {
        for(int i = size-1; i >= 0;i--) {
                System.out.print(maxValue[i][j]+","+nWays[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<String> board = new ArrayList<>();
        //String[] sArray = {"E12","1X1","21S"};//4,2
        //String[] sArray = {"E5","XS"};//5,1
        //String[] sArray = {"E11345","X452XX","3X43X4","422812","284522","13422S"};//34,3
        String[] sArray = {"E11345","X452XX","3X43X4","44X312","23452X","1342XS"};// [27,1]
        /*
            E12
            1x1
            21s

            E11345
            X452XX
            3X43X4
            422812
            284522
            13422S

            "E11345",
            "X452XX",
            "3X43X4",
            "44X312",
            "23452X",
            "1342XS"
         */
        for(String s : sArray) {
            board.add(s);
        }
        NumberOfPathWithGreatestScore2 ngs = new NumberOfPathWithGreatestScore2();
        int[] res = ngs.pathsWithMaxScore(board);
        System.out.print(Arrays.toString(res));

    }
}
