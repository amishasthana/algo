import java.util.ArrayList;
import java.util.List;

/*
1253. Reconstruct a 2-Row Binary Matrix

    User Accepted: 2029
    User Tried: 2364
    Total Accepted: 2081
    Total Submissions: 6207
    Difficulty: Medium

Given the following details of a matrix with n columns and 2 rows :

    The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
    The sum of elements of the 0-th(upper) row is given as upper.
    The sum of elements of the 1-st(lower) row is given as lower.
    The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.

Your task is to reconstruct the matrix with upper, lower and colsum.

Return it as a 2-D integer array.

If there are more than one valid solution, any of them will be accepted.

If no valid solution exists, return an empty 2-D array.



Example 1:

Input: upper = 2, lower = 1, colsum = [1,1,1]
Output: [[1,1,0],[0,0,1]]
Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.

Example 2:

Input: upper = 2, lower = 3, colsum = [2,2,1,1]
Output: []

Example 3:

Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]

 */
public class ReconstructBinMatrix {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> lRes = new ArrayList<>();
        if ((colsum == null) || (colsum.length == 0)) {
            return lRes;
        }

        int allOnes = 0;
        int singleOnes = 0;
        for(int i : colsum) {
            if ( i == 2) {
                allOnes++;
            } else if (i == 1) {
                singleOnes++;
            }
        }
        if ( (upper < allOnes) || (lower < allOnes) || ((upper+lower) != (allOnes*2+singleOnes))) {
            return lRes;
        }
        List<Integer> fRow = new ArrayList<>();
        List<Integer> sRow = new ArrayList<>();
        upper = upper-allOnes;
        for(int i : colsum) {
            if ( i == 0)  {
                fRow.add(0);
                sRow.add(0);
            } else if (i == 2) {
                fRow.add(1);
                sRow.add(1);
            } else {

                if (upper <= 0) {
                    fRow.add(0);
                    sRow.add(1);
                } else {
                    upper--;
                    fRow.add(1);
                    sRow.add(0);
                }
            }
        }
        lRes.add(fRow);
        lRes.add(sRow);
        return lRes;
    }
}
