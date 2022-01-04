package graph;

import java.util.*;

/*
2097. Valid Arrangement of Pairs

    User Accepted: 94
    User Tried: 805
    Total Accepted: 109
    Total Submissions: 1687
    Difficulty: Hard

You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.

Return any valid arrangement of pairs.

Note: The inputs will be generated such that there exists a valid arrangement of pairs.



Example 1:

Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3

Example 2:

Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.

Example 3:

Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2



Constraints:

    1 <= pairs.length <= 105
    pairs[i].length == 2
    0 <= starti, endi <= 109
    starti != endi
    No two pairs are exactly the same.
    There exists a valid arrangement of pairs.


 */
public class EulireanPath {
    Map<Integer, Stack<Integer>> edges = new HashMap<>();
    Map<Integer,Integer> outEdgeC = new HashMap<>();


    public int[][] validArrangement(int[][] pairs) {

        for(int i = 0; i < pairs.length;i++) {
            int in = pairs[i][0];
            int out = pairs[i][1];
            Stack<Integer> iStack = edges.getOrDefault(in,new Stack<Integer>());
            iStack.push(out);
            edges.put(in,iStack);
            outEdgeC.put(in,outEdgeC.getOrDefault(in,0)+1);
            outEdgeC.put(out,outEdgeC.getOrDefault(out,0)-1);
        }
        int start = 0;
        boolean startFound = false;
        for(Map.Entry<Integer, Integer> entry : outEdgeC.entrySet()) {
            start = entry.getKey();
            if ( outEdgeC.getOrDefault(start,0)   == 1) {
                break;
            }
        }

        List<int[][]> ans = new ArrayList<>();
        euler(start,ans);
        int[][] A = new int[pairs.length][2];
        for (int i = 0;i < pairs.length; i++) {
            int[][] map = ans.get(pairs.length - i-1);
            A[i][0] = map[0][0];
            A[i][1] = map[0][1];
        }


        return A;
    }

    private void euler(int i,List<int[][]> ans) {
        Stack<Integer> stack = edges.get(i);
        while ((stack != null) && !stack.empty()) {
            int edge = stack.peek();
            stack.pop();
            euler(edge, ans);
            int[][] aa = {{i, edge}};
            ans.add(aa);
        }
    }

    public static void main(String[] args) {
        int[][] A = {{5,1},{4,5},{11,9},{9,4}};
        EulireanPath ep = new EulireanPath();
        int[][] AA = ep.validArrangement(A);
        System.out.println(Arrays.toString(AA[0]));
    }
}
