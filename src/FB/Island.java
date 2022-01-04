package FB;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


 */
public class Island {

    class Solution {
        public int numIslands(char[][] grid) {
            int nIS = 0;
            for(int i = 0; i < grid.length;i++) {
                for(int j = 0; j < grid[0].length;j++) {
                    if (grid[i][j] == '1') {
                        //if (markIsland(grid,i,j)) nIS++;
                        nIS++;
                        markIsland(grid,i,j);
                    }
                }
            }
            return nIS;
        }
        public class Pos {
            int i; int j;
            public Pos(int ii, int jj) {
                i = ii;
                j = jj;
            }
        }
        private boolean markIsland(char[][] grid,int r,int c) {
            int R = grid.length; int C = grid[0].length;
            boolean isIsland = (r > 0) && ( r < R) && (c > 0) && (c < C);
            Queue<Pos> qC = new LinkedList<>();
            final boolean add = qC.add(new Pos(r, c));
            while(!qC.isEmpty()) {
                Pos p = qC.poll();
                if (grid[p.i][p.j] == '1') {
                    grid[p.i][p.j] = '2';
                    for(Pos pp : getNext(p.i,p.j,R,C)) {
                        qC.add(pp);
                    }
                    if (isIsland) {
                        isIsland = (p.i > 0) && ( p.i < R) && (p.j > 0) && (p.j < C);
                    }
                }
            }
            return isIsland;
        }
        private Set<Pos> getNext(int i, int j,int R,int C ) {
            Set<Pos> ns = new HashSet<>();
            if ( i > 0) ns.add(new Pos(i-1,j));
            if ((i+1) < R) ns.add(new Pos(i+1,j));
            if ( j > 0) ns.add(new Pos(i,j-1));
            if ((j+1) < C) ns.add(new Pos(i,j+1));
            return ns;
        }

    }
}
