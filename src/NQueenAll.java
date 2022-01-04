import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueenAll {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> resList = new LinkedList<>();
        int[] q = new int[n];
        Arrays.fill(q,-1);
        //q[0] = 0;
        for (int i = 0;i < n;) {
            if (i < 0) break; //all sol exhausted.
            int sPos = 0;
            if ( i != 0) {// No backtrack for first queen.
                sPos = ( q[i] ==  n )?0:(q[i]+1);
            } else {
                sPos = q[i] + 1;
            }
            q[i] = -1;// ready to get new value.
            for(int j = sPos; j < n; j++) {
                if ( isValid(q,i,j) ) {
                    q[i] = j;
                    break;
                }
            }//End of col
            if (q[i] == -1) {
                i--; //backtrack.
                continue;
            } else  if ( ( i == (n-1) ) && (q[i] != -1)  ) {//last one.
                resList.add( createList(q) );
                q[i] = -1; //Set it up for next
                i--;// back tracking
                continue;
            }
            i++;
        }//End of row for queens.
        return resList;
    }

    private List<String> createList(int[] q) {
        List<String> cSol = new LinkedList<>();
        //int qIndex = 0;
        for(int j = 0;j < q.length;j++) {
            StringBuilder strB = new StringBuilder();
            for(int k = 0;k < q.length;k++) {
                if (q[j] == k) {
                    strB.append('Q');
                    continue;
                }
                strB.append('.');
            }
            cSol.add(strB.toString());
        }
        return cSol;
    }

    private boolean isValid(int[] q,int r,int c) {
        for(int i = 0; i < r;i++) {// column check.
            if (q[i] == c) return false;
            if ( Math.abs((q[i]-c)) == Math.abs(i - r)) return false;// Diagonal
        }
        return true;
    }




    public static void main(String[] args) {
        NQueenAll nQ = new NQueenAll();
        List<List<String>> res = nQ.solveNQueens(5);
        for(List<String> l : res) {
            l.forEach(x->System.out.println(x));
            System.out.println();
            System.out.println();
        }

    }
}
