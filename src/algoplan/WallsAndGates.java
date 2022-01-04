package algoplan;

import java.util.*;

/*
286. Walls and Gates
Medium

You are given an m x n grid rooms initialized with these three possible values.

    -1 A wall or an obstacle.
    0 A gate.
    INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.



Example 1:

Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

Example 2:

Input: rooms = [[-1]]
Output: [[-1]]

Example 3:

Input: rooms = [[2147483647]]
Output: [[2147483647]]

Example 4:

Input: rooms = [[0]]
Output: [[0]]



Constraints:

    m == rooms.length
    n == rooms[i].length
    1 <= m, n <= 250
    rooms[i][j] is -1, 0, or 231 - 1.

Accepted
189,891
Submissions
325,826
 */
public class WallsAndGates {
    int INF = 2147483647;
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> Q = new LinkedList<>();
        populate(Q,rooms);
        while (!Q.isEmpty()) {
            int[] cElem = Q.poll();
            if (rooms[cElem[0]][cElem[1]] == -1) continue;
            List<int[]> list  = findChild(rooms,cElem[0],cElem[1]);
            for (int[] A : list) {
                if (rooms[A[0]][A[1]] == INF) {
                    rooms[A[0]][A[1]] = rooms[cElem[0]][cElem[1]] + 1;
                    Q.add(new int[] {A[0],A[1]});
                }
            }
        }
    }

    private List<int[]> findChild(int[][] rooms,int i,int j) {
        List<int[]> Q = new LinkedList<>();
        if ((i > 0) && rooms[i-1][j] == INF) Q.add(new int[]{i-1,j});
        if ((j > 0) && rooms[i][j-1] == INF) Q.add(new int[]{i,j-1});
        if ((i < rooms.length-1) && rooms[i+1][j] == INF) Q.add(new int[]{i+1,j});
        if ((j < rooms[0].length-1) && rooms[i][j+1] == INF) Q.add(new int[]{i,j+1});
        return Q;
    }

    private void populate(Queue<int[]> Q,int[][] rooms) {
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length;j++) {
                if ((i > 0) && rooms[i-1][j] == 0) Q.add(new int[]{i-1,j});
                if ((j > 0) && rooms[i][j-1] == 0) Q.add(new int[]{i,j-1});
                if ((i < rooms.length-1) && rooms[i+1][j] == 0) Q.add(new int[]{i+1,j});
                if ((j < rooms[0].length-1) && rooms[i][j+1] == 0) Q.add(new int[]{i,j+1});
            }
        }
    }

    public static void main(String[] args) {
        WallsAndGates wag = new WallsAndGates();
        int[][] A = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}};
        wag.wallsAndGates(A);
        System.out.println(Arrays.toString(A));
    }
}
