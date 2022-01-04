package Contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
1761. Minimum Degree of a Connected Trio in a Graph

    User Accepted: 839
    User Tried: 1507
    Total Accepted: 908
    Total Submissions: 4860
    Difficulty: Hard

You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.

A connected trio is a set of three nodes where there is an edge between every pair of them.

The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.

Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.



Example 1:

Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
Output: 3
Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.

Example 2:

Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
Output: 0
Explanation: There are exactly three trios:
1) [1,4,3] with degree 0.
2) [2,5,6] with degree 2.
3) [5,6,7] with degree 2.



Constraints:

    2 <= n <= 400
    edges[i].length == 2
    1 <= edges.length <= n * (n-1) / 2
    1 <= ui, vi <= n
    ui != vi
    There are no repeated edges.


 */
public class Triad {
    public int minTrioDegree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> M = new HashMap<>();
        for (int i = 0; i < edges.length;i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            Set<Integer> S = M.getOrDefault(x, new HashSet<>());
            S.add(y);
            M.put(x,S);
            S = M.getOrDefault(y, new HashSet<>());
            S.add(x);
            M.put(y,S);
        }
        int min = 4001;
        for(Map.Entry<Integer,Set<Integer>> E : M.entrySet()   ) {
            Set<Integer> cXSet = E.getValue();
            int x = E.getKey();
            for(int y : cXSet) {
                if (y < x) continue;
                Set<Integer> cYSet = M.get(y);
                for( int z : cYSet) {
                    if (z < y ) continue;
                    if (M.get(z).contains(x)) {//triad.
                        int size = M.get(x).size()+M.get(y).size()+M.get(z).size() -6;
                        min = Math.min(min,size);
                        if (min == 0) return min;
                    }
                }// End of z
            }// End of y
        }// End of for
        if (min == 4001) return -1;
        return min;

    }// end of class

    public static void main(String[] args) {
        Triad t = new Triad();
        int[][] A = {{1,3},{4,1},{4,3},{2,5},{5,6},{6,7},{7,5},{2,6}};
        Triad T = new Triad();
        System.out.println(T.minTrioDegree(7,A));
    }

}
