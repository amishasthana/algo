/*

174th contest

Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.

A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j,
 or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.



Example 1:

Input: mat =
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]],
k = 3
Output: [2,0,3]
Explanation:
The number of soldiers for each row is:
row 0 -> 2
row 1 -> 4
row 2 -> 1
row 3 -> 2
row 4 -> 5
Rows ordered from the weakest to the strongest are [2,0,3,1,4]

Example 2:

Input: mat =
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]],
k = 2
Output: [0,2]
Explanation:
The number of soldiers for each row is:
row 0 -> 1
row 1 -> 4
row 2 -> 1
row 3 -> 1
Rows ordered from the weakest to the strongest are [0,2,3,1]


 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class KWeakest {
    class Weakest {
        int row;
        int nSol;
    }
    PriorityQueue<Weakest> maxHeap = new PriorityQueue<Weakest>(new Comparator<Weakest>() {
        @Override
        public int compare(Weakest o1, Weakest o2) {
            if (o1.nSol == o2.nSol) return (o1.row - o2.row);
            return (o1.nSol-o2.nSol);
        }
    });

    public int[] kWeakestRows(int[][] mat, int k) {
        for(int i = 0; i < mat.length;i++) {
            Weakest cWeak = new Weakest();
            cWeak.row = i;
            for(int j = 0;j < mat[0].length;j++){
                if (mat[i][j] == 0) {
                    break;
                }
                cWeak.nSol++;
            }
            maxHeap.add(cWeak);
        }
        int[] lowest = new int[k];
        int index = 0;
        while (index < k) {
            lowest[index] = maxHeap.poll().row;
            index++;
        }
        return lowest;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
        KWeakest kw = new KWeakest();
        kw.kWeakestRows(mat,3);
    }




}
