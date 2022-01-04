/*


Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.



Example 1:

Input:
grid =
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]],
k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).



Example 2:

Input:
grid =
[[0,1,1],
 [1,1,1],
 [1,0,0]],
k = 1
Output: -1
Explanation:
We need to eliminate at least two obstacles to find such a walk.



Constraints:

    grid.length == m
    grid[0].length == n
    1 <= m, n <= 40
    1 <= k <= m*n
    grid[i][j] == 0 or 1
    grid[0][0] == grid[m-1][n-1] == 0


 */

import java.util.*;

public class GridObstacelEliminate {

    class Pos implements Comparable<Pos> {
        int x;
        int y;
        int v;
        int kSoFar;
        int shortestPath;
        boolean isVisited;

        public Pos(int i, int j, int val) {
            x = i;
            y = j;
            v = val;
        }

        @Override
        public int compareTo(Pos p) {
            return this.kSoFar - p.kSoFar;
        }

    }

    //Queue<Pos> pQueue = new PriorityQueue<>();
    Queue<Pos> pQueue = new LinkedList<>();
    Map<String, Pos> posMap = new HashMap<>();

    private void populateMap(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Pos newPos = new Pos(i, j, grid[i][j]);
                posMap.put(i + "_" + j, newPos);
            }
        }
    }

    public int shortestPath(int[][] grid, int k) {

        populateMap(grid);

        int minShortestPath = Integer.MAX_VALUE;
        Pos sElement = posMap.get(0 + "_" + 0);
        pQueue.add(sElement);
        sElement.isVisited = true;
        while (!pQueue.isEmpty()) {
            Pos cElement = pQueue.poll();
            if ((cElement.x == (grid.length - 1)) && (cElement.y == (grid[0].length - 1))) {
                System.out.println("*** Reached last");
                print(cElement);
                if (minShortestPath > cElement.shortestPath) {
                    minShortestPath = cElement.shortestPath;
                }
            }
            System.out.println("*** Current element");
            print(cElement);
            int kForChild = cElement.kSoFar + cElement.v;
            if (kForChild > k) continue;
            Queue<Pos> childQueue = getChildQueue(cElement, grid.length, grid[0].length);

            while (!childQueue.isEmpty()) {
                Pos currentChild = childQueue.poll();
                if (!currentChild.isVisited || (kForChild < currentChild.kSoFar)) {
                    pQueue.add(currentChild);
                    currentChild.isVisited = true;
                    currentChild.kSoFar = kForChild;
                    currentChild.shortestPath = cElement.shortestPath + 1;
                    print(currentChild);
                }
            }

        }

        return minShortestPath;
    }

    private void print(Pos p) {
        //System.out.println("Current element is "+p.x+","+p.y+",kso far "+p.kSoFar+",shortest path"+","+p.shortestPath);
    }

    private Queue<Pos> getChildQueue(Pos cElement, int maxX, int maxY) {
        //Queue<Pos> pQueue = new PriorityQueue<>();
        Queue<Pos> pQueue = new LinkedList<>();
        if (cElement.x > 0) {
            pQueue.add(posMap.get((cElement.x - 1) + "_" + cElement.y));
        }
        if (cElement.x < (maxX - 1)) {
            pQueue.add(posMap.get((cElement.x + 1) + "_" + cElement.y));
        }
        if (cElement.y > 0) {
            pQueue.add(posMap.get(cElement.x + "_" + (cElement.y - 1)));
        }
        if (cElement.y < (maxY - 1)) {
            pQueue.add(posMap.get(cElement.x + "_" + (cElement.y + 1)));
        }
        return pQueue;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}};
        int[][] grid1 = {{0,1,1},
                {1,1,1},
                {1,0,0}};
        GridObstacelEliminate goe = new GridObstacelEliminate();
        System.out.print(goe.shortestPath(grid,1));
    }


}
