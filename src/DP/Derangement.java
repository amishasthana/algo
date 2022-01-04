package DP;

import java.util.BitSet;

/*
N hats. Distribute hats so that no one get the same hat.
 */
public class Derangement {
    int N;
    int total = 0;
    public int findNumberWays(int N) {
        this.N = N;
        findWays(0,new BitSet(N));
        return total;
    }

    private void findWays(int h,BitSet bSet) {
        if (h == N) {
            total++;
            return;
        }
        for(int i = 0; i < N;i++) {// for each hat.
            if ((i == h) || bSet.get(i)) continue;
            bSet.set(i);
            findWays(h+1,bSet);
            bSet.flip(i);
        }
    }

    /*
       N --> (N-1) * ( Solve(N-2) + Solve(N-1) )
       Reason --> First place --> N-1 hat.
                  say first place got I.
                  so two condition -->
                    I --> 1. Then N-2
                    If I should not get 1 then N-1
            T[0] -> 1
            T[1] --> 0;

     */
    public int betterWay(int N) {
        int[] T = new int[N+1];
        T[0] = 1; T[1] = 0;
        for(int i = 2;i <= N;i++) {
            T[i] = (i-1)* (T[i-1]+T[i-2]);
        }
        return T[N];
    }

    public static void main(String[] args) {
        Derangement D = new Derangement();
        //System.out.println(D.findNumberWays(4));
        System.out.println(D.betterWay(5));
    }
}
