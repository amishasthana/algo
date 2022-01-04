package algoplan;

public class UnionFind {
    int[] P;
    private int getP(int i) {
         while (i != P[i]) {
             P[i] = P[P[i]];
             i = P[i];
         }
         return i;
    }

    // when there is connection. Always set the least.
    /*
         if without RANK then do a loop . Basically say 5 and 8 both have parent 5.
         Now one merge 1 and 8 --> 8 paremt become 1. However we need to do so for 5 too.

     */
    private void merge(int i, int j,int[] S) {
        i = getP(i);
        j = getP(j);
        if (S[i] <= S[j]) {
            P[j] = P[i] ;
            S[j] += S[i];
        } else {
            P[i] = P[j];
            S[i] += S[j];
        }
    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < (n-1)) return -1;

        P = new int[n];//Parent Array
        int[] S = new int[n];//Use to capture size. Lower get added to bigger one.
        for(int i = 0; i < n;i++) P[i] = i;
        for(int[] c : connections) {
            merge(c[0],c[1],S);
            //System.out.println("Merge "+c[0]+" "+c[1]);
            //System.out.println(Arrays.toString(P));
        }

        int conn = 0;
        for(int i = 0; i < n;i++) {
            if (P[i] == i) conn++;
        }
        return conn -1;
    }

    public static void main(String[] args) {
        UnionFind nw = new UnionFind();
        int[][] A = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        int[][] B = {{0,1},{0,2},{1,2}};
        int[][] C = {{0,1},{0,2},{3,4},{2,3}};
        int[][] D = {{6,8},{0,4},{1,2},{5,8},{0,9},{1,8},{1,4},{4,9},{4,6},{3,7},{2,4},{3,5},{6,7},{4,5}};


        System.out.println(nw.makeConnected(6,A));
        System.out.println(nw.makeConnected(4,B));
        System.out.println(nw.makeConnected(5,C));
        System.out.println(nw.makeConnected(10,D));

    }
}
