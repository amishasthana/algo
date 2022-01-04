import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
/*
935. Knight Dialer
Medium

A chess knight can move as indicated in the chess diagram below:

 .



This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.



Example 1:

Input: 1
Output: 10

Example 2:

Input: 2
Output: 20

Example 3:

Input: 3
Output: 46

 */

public class KnighDialer {
    int mod = (int) 1000000007;
    Map<Integer,Set<Integer>> map = new HashMap<>();

    public int knightDialer(int N) {
        popMap();

        long[][] iA = new long[10][N];
        for(int i = 0; i < 10; i++) {
            iA[i][0] = 1;
        }
        if (N == 1) return 10;
        long retV = 0;
        for(int i = 1; i < N;i++) {
            for(int j = 0; j < 10;j++) {
                for(Integer iV : map.get(j)) {
                    iA[j][i]  = (iA[j][i] +iA[iV][i-1])%mod;
                    //iA[j][i] = (iA[j][i] % mod);
                }
                iA[j][i]  = iA[j][i];
                if (i == (N-1)) {
                    retV = (retV + iA[j][i])%mod;
                }

            }//End of inner loop
        }//End of outer loop
        return (int)(retV-1);//Apparently we are saying we can place it on 5 only if N == 1.

    }

    private void popMap() {
        Set<Integer> s0 = new HashSet<>();s0.add(4);s0.add(6);
        map.put(0,s0);
        Set<Integer> s1 = new HashSet<>();s1.add(8);s1.add(6);
        map.put(1,s1);
        Set<Integer> s2 = new HashSet<>();s2.add(7);s2.add(9);
        map.put(2,s2);
        Set<Integer> s3 = new HashSet<>();s3.add(4);s3.add(8);
        map.put(3,s3);
        Set<Integer> s4 = new HashSet<>();s4.add(3);s4.add(9);s4.add(0);
        map.put(4,s4);
        Set<Integer> s5 = new HashSet<>();s5.add(5);
        map.put(5,s5);
        Set<Integer> s6 = new HashSet<>();s6.add(1);s6.add(7);s6.add(0);
        map.put(6,s6);
        Set<Integer> s7 = new HashSet<>();s7.add(2);s7.add(6);
        map.put(7,s7);
        Set<Integer> s8 = new HashSet<>();s8.add(1);s8.add(3);
        map.put(8,s8);
        Set<Integer> s9 = new HashSet<>();s9.add(4);s9.add(2);
        map.put(9,s9);

    }

    public static void main(String[] args) {
        KnighDialer kd = new KnighDialer();
        System.out.println(kd.knightDialer(381));
    }

}
