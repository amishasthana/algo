package algoplan;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Nqueen {


        int[] pos;
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> aList = new ArrayList<>();
            BitSet R = new BitSet(n);
            BitSet C = new BitSet(n);
            int[][] chess = new int[n][n];
            pos = new int[n];//just y

            pop(R,C,chess,0,aList);

            return aList;
        }

        private boolean isDiagonalAllowed(int x,int y) {
            for(int i = 0; i < x;i++) {
                if (Math.abs(i - x) == Math.abs(pos[i] -y)) return false;
            }
            return true;
        }

        private boolean pop(BitSet R,BitSet C,int[][] chess,int i,List<List<String>> aList) {
            int len = chess.length;
            if (i >= len) return true;
            for (int y = 0; y < len; y++) {
                if (!R.get(i) && !C.get(y) && isDiagonalAllowed(i,y) /*(chess[i][y] == 0)*/) {
                    chess[i][y] = 1;
                    R.set(i);
                    C.set(y);//like rook.
                    pos[i] = y;
                    //diagonal(i,y,chess,2);
                    if (pop(R, C, chess, i + 1,aList) && (i == (len-1))) {
                        createList(chess,aList);
                    }
                    chess[i][y] = 0;
                    R.flip(i);
                    C.flip(y);
                    pos[i] = 0;
                    //diagonal(i,y,chess,0);
                }

            }
            return false;
        }


   /* not needed other method easier
    private void diagonal(int i, int j, int[][] chess, int v) {
        int l = chess.length;
        for (int x = 1; x < chess.length; x++) {
            if (((x + i) < l) && ((x + j) < l)) {
                chess[x + i][x + j] = v;
            }
            if (((x + i) < l) && ((j- x) >= 0)) {
                chess[x + i][j-x] = v;
            }
            if (((i-x) >= 0) && ((j- x) >= 0)) {
                chess[i-x][j-x] = v;
            }
            if (((i-x) >= 0) && ((x + j) < l)) {
                chess[i-x][x + j] = v;
            }

        }
    }*/

        private void createList(int[][] chess,List<List<String>> aList ) {
            List<String> list = new ArrayList<>();

            for(int x = 0; x < chess.length;x++) {
                StringBuilder strB = new StringBuilder();
                for(int y = 0; y < chess[0].length;y++) {
                    if (chess[x][y] == 1) {
                        strB.append('Q');
                    }else {
                        strB.append('.');
                    }
                }
                list.add(strB.toString());
                //if (x != (chess.length-1)) list.add(",");
            }
            aList.add(list);
        }

        public static void main(String[] args) {
            Nqueen n = new Nqueen();
            List<List<String>> list = n.solveNQueens(4);
            for(List<String> l : list) {
                System.out.println(l.toString());
            }
        }


}
