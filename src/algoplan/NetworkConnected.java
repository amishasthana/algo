package algoplan;

import java.util.*;

/*
1319. Number of Operations to Make Network Connected
Medium

There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.



Example 1:

Input: n = 4, connections = [[0,1],[0,2],[1,2}
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

Example 2:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3}
Output: 2

Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2}
Output: -1
Explanation: There are not enough cables.



Constraints:

    1 <= n <= 105
    1 <= connections.length <= min(n * (n - 1) / 2, 105)
    connections[i].length == 2
    0 <= ai, bi < n
    ai != bi
    There are no repeated connections.
    No two computers are connected by more than one cable.


 */
public class NetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < (n - 1)) return -1;
        Map<Integer, Set<Integer>> M = new HashMap<>();
        for (int[] C : connections) {
            int s1 = Math.min(C[0],C[1]);
            int s2 = Math.max(C[0],C[1]);
            Set<Integer> set1 = M.getOrDefault(s1, new HashSet<>());
            set1.add(s2);
            M.put(s1, set1);
            Set<Integer> set2 = M.getOrDefault(s2, new HashSet<>());
            set2.add(s1);
            M.put(s2, set2);
        }


        BitSet bSet = new BitSet(n);
        int nConn = 0;
        int i = 0;
        Queue<Integer> Q = new LinkedList<>();
        for (; i < n;) {
            while (bSet.get(i)) i++;
            if (i < n) {
                nConn++;
                bSet.set(i);
                Q.add(i);
                while (!Q.isEmpty()) {
                    int cElem = Q.poll();
                    Set<Integer> cElemSet = M.get(cElem);
                    if (cElemSet != null) {
                        for (int j : cElemSet) {
                            if (bSet.get(j)) continue;
                            Q.add(j);
                            bSet.set(j);
                        }
                    }
                }
            }
        }
        return nConn-1;
    }

    public static void main(String[] args) {
        NetworkConnected nw = new NetworkConnected();
        int[][] A = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        int[][] B = {{0,1},{0,2},{1,2}};
        int[][] C = {{0,1},{0,2},{3,4},{2,3}};

        System.out.println(nw.makeConnected(6,A));
        System.out.println(nw.makeConnected(4,B));
        System.out.println(nw.makeConnected(5,C));
        
    }
}