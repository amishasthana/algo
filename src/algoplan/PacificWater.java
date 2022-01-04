package algoplan;

import java.util.*;

/*
417. Pacific Atlantic Water Flow
Medium

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.



Example 1:

Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

Example 2:

Input: heights = [[2,1],[1,2]]
Output: [[0,0],[0,1],[1,0],[1,1]]



Constraints:

    m == heights.length
    n == heights[r].length
    1 <= m, n <= 200
    0 <= heights[r][c] <= 105

One way to do it. Do a search from all pacific and Atlantic and see which can be raised by both.
 */
public class PacificWater {

    int[][] heights;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<int[]> s = new LinkedList<>();
        this.heights = heights;

        int[][] A = new int[heights.length][heights[0].length];
        for(int i = 0;i <A[0].length;i++) {
            A[0][i] = 1;
            s.add(new int[]{0,i});
        }
        for(int i = 0;i <A.length;i++) {
            A[i][0] = 1;
            s.add(new int[]{i,0});
        }
        bfs(A,s);

        int[][] B = new int[heights.length][heights[0].length];
        for(int i = 0;i <B[0].length;i++) { //bottom row
            B[B.length-1][i] = 1;
            s.add(new int[]{B.length-1,i});
        }
        for(int i = 0;i <B.length;i++) { //right most column
            B[i][B[0].length-1] = 1;
            s.add(new int[]{i,B[0].length-1});
        }
        bfs(B,s);

        for(int i = 0; i < A.length;i++) {
            for(int j = 0; j < A[0].length;j++) {
                if ((A[i][j] == 1) && (B[i][j] == 1)){
                    ans.add(List.of(i,j));
                }
            }
        }

        return ans;
    }

    private void bfs(int[][] A,Queue<int[]> s) {

        while (!s.isEmpty()) {
            int[] cElem = s.poll();

            List<int[]> ll = getChild(A,cElem);
            int cElemV = A[cElem[0]][cElem[1]];
            int cElemH = heights[cElem[0]][cElem[1]];
            for(int[] childElem : ll ) {
                int childElemV = A[childElem[0]][childElem[1]];
                int childElemH = heights[childElem[0]][childElem[1]];
                if ( (childElemH >= cElemH) && (childElemV == 0)) {//height, not final which is BOTH or current.
                    A[childElem[0]][childElem[1]] = 1;
                    //System.out.println(cElem[0]+","+cElem[1]+" h = "+A[cElem[0]][cElem[1]]);
                    s.add(childElem);
                }
            }
        }
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Set<Integer> ans = new HashSet<>();

        Map<Integer,Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < ppid.size();i++) {
            Set<Integer> s = map.getOrDefault(ppid.get(i),new HashSet<>());
            s.add(i);
            map.put(ppid.get(i),s);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(kill);
        while (!q.isEmpty()) {
            int v = q.poll();
            ans.add(v);
            if (map.containsKey(v)) {
                for(int ii : map.get(v)) {
                    q.add(ii);
                }
            }
        }

        List<Integer> targetList = new ArrayList<>(ans);
        return targetList;
}

    private List<int[]> getChild(int[][] A,int[] cElem) {
        List<int[]> ll = new ArrayList<>();
        int i = cElem[0];int j = cElem[1];
        if (i >0) ll.add(new int[]{i-1,j});
        if (j >0) ll.add(new int[]{i,j-1});
        if (i < A.length-1) ll.add(new int[]{i+1,j});
        if (j < A[0].length-1) ll.add(new int[]{i,j+1});
        return ll;
    }



    public static void main(String[] args) {
        int[][] A = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};

        PacificWater pw = new PacificWater();
        List<List<Integer>>ll = pw.pacificAtlantic(A);
        //[[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
        System.out.println(ll.toString());
        for(List<Integer> l : ll) {
            //System.out.println();System.out.println(l.toString());
        }
    }
}
