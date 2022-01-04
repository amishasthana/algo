import java.util.*;

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


 */
public class NumberOfPathWithGreatestScore {
    static class Pos {
        int row;
        int col;
        char c;
        int val= 0;
        int sumSoFar;

        public Pos(int i,int j,char c) {
            row = i; col = j; this.c = c;
            if ((c != 'X') && (c != 'S') && (c != 'E')) {
                val = Integer.parseInt(c+"");
            }

        }
    }

    int maxSum = -1;
    int nPaths = 0;
    //Map<Long,Long> numberOfPaths = new HashMap<>();
    //Map<String,Pos> posMap = new HashMap<>();
    int modulo = 10^9 + 7;
    Pos[][] cArray = null;
    public int[] pathsWithMaxScore(List<String> board) {
        convertToArray(board);
        int l = board.size();
        int[] retV = {0,0};
        Stack<Pos> stackOfElem = new Stack<>();
           Pos initE = cArray[l-1][l-1];
           stackOfElem.add(initE);
           while (!stackOfElem.isEmpty()) {
              Pos cPos = stackOfElem.pop();
              System.out.println("Element pop "+cPos.row+","+cPos.col+","+cPos.c);
              if (cPos.c == 'E') {

                  if (cPos.sumSoFar > maxSum) {
                      maxSum = cPos.sumSoFar;
                      nPaths = 1;
                      System.out.println(" first path "+maxSum);
                  } else if (cPos.sumSoFar == maxSum) {
                      nPaths = (nPaths+1)%modulo;
                      System.out.println(nPaths+" nPaths, "+maxSum);
                  }
                  continue;
              }
               Set<Pos> childs = getChildren(cPos);
               for(Pos p : childs) {
                   p.sumSoFar = p.val+cPos.sumSoFar%modulo;
                   System.out.println("Element push "+p.row+","+p.col+","+p.c);
                   stackOfElem.push(p);
               }
           }
           if (nPaths != 0) {
               int[] newRetV = {maxSum,nPaths};
               retV = newRetV;
           }

          return retV;
    }

    private Set<Pos> getChildren(Pos p) {
        Set<Pos> childSet = new HashSet<>();
        if (((p.row-1) >= 0) && isNumeric(cArray[p.row-1][p.col])) {
            childSet.add(cArray[p.row-1][p.col]);
        }
        if (((p.col-1) >= 0) && isNumeric(cArray[p.row][p.col-1])) {
            childSet.add(cArray[p.row][p.col-1]);
        }
        if (((p.col-1) >= 0) && ((p.row-1) >= 0) && isNumeric(cArray[p.row-1][p.col-1])) {
            childSet.add(cArray[p.row-1][p.col-1]);
        }
        return childSet;
    }

    private boolean isNumeric(Pos p) {
        if ((p.c == 'X') ) return false;
        return true;
    }

    private void convertToArray(List<String> board) {
        cArray = new Pos[board.size()][board.size()];
        for(int i = 0; i < board.size();i++) {
             char[] row = board.get(i).toCharArray();
             for(int j = 0; j <  board.size();j++) {
                 cArray[i][j] = new Pos(i,j,row[j]);
             }
        }
    }

    public static void main(String[] args) {
        List<String> board = new ArrayList<>();
        String[] sArray = {"E12","1X1","21S"};//4,2
        /*
            E12
            1x1
            21s
         */
        for(String s : sArray) {
            board.add(s);
        }
        NumberOfPathWithGreatestScore ngs = new NumberOfPathWithGreatestScore();
        int[] res = ngs.pathsWithMaxScore(board);
        System.out.print(Arrays.toString(res));

    }
}
