/*
Count Square Submatrices with All Ones

    User Accepted: 1442
    User Tried: 1676
    Total Accepted: 1493
    Total Submissions: 2482
    Difficulty: Medium

Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.



Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.

Example 2:

Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.

 */
public class SquareSubMatrixAllOnes {
    public int countSquares(int[][] matrix) {
          int oneM = 0;
          for(int i = 0; i < matrix.length;i++) {
              for(int j = 0; j < matrix[0].length;j++) {
                  if (matrix[i][j] == 1) {
                      oneM += findAllMatrix(matrix,i,j);
                  }
              }
          }
          return oneM;
    }

    private int findAllMatrix(int[][] matrix,int i,int j) {
        int oneM = 1;
        int maxPossibleIndex = Math.min((matrix.length-i),(matrix[0].length-j));
        for(int index = 1; index < maxPossibleIndex;index++) {
            //For row
            for(int colIndex = 0; colIndex < index;colIndex++) {
                if (matrix[i+index][j+colIndex] != 1) {
                     return oneM;
                }
            }
            //For the column
            for(int rowIndex = 0; rowIndex < index;rowIndex++) {
                if (matrix[i+rowIndex][j+index] != 1) {
                    return oneM;
                }
            }
            if(matrix[i+index][j+index] != 1) return oneM;
            oneM++;
        }
        return oneM;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,1,1},
                          {1,1,1,1},
                          {0,1,1,1}
                         };//15

        int[][] matrix1 = {{1,0,1},
                           {1,1,0},
                           {1,1,0}};//7

        int[][] matrix2 = {{0,0,0},
                           {0,1,0},
                           {0,1,0},
                           {1,1,1},
                           {1,1,0}};//8
        SquareSubMatrixAllOnes ss = new SquareSubMatrixAllOnes();
        System.out.print(ss.countSquares(matrix));
    }
}
