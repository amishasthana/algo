package Goog;

import java.util.Arrays;
import java.util.Comparator;

/*
452. Minimum Number of Arrows to Burst Balloons
Medium

There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).


 */
public class MinimumArrow {
    class Pair {
        int x1; int x2;
        public Pair(int x1,int x2) {
            this.x1 = x1; this.x2 = x2;
        }
    }
    public int findMinArrowShots(int[][] points) {
        if ((points == null) || (points.length == 0)) return 0;
        int minArrow = 0;
        Arrays.sort(points, Comparator.comparingInt(a->a[0]));
        int len = points.length;
        //int cIndex = 0; int cEndIndex = 0;
        for(int i = 0; i < len;) {
            Pair cPair = new Pair(points[i][0],points[i][1]);
            boolean iSect = true;
            while (iSect) {
                int nIndex = ++i;
                if (nIndex == len) {
                    break;
                }
                Pair nPair = new Pair(points[nIndex][0],points[nIndex][1]);
                Pair intPair = isInter(cPair,nPair);
                if (intPair == null) break;
                cPair = intPair;
            }
            minArrow++;
        }
        return minArrow;
    }

    private Pair isInter(Pair p1,Pair  p2) {
        Pair p = null;
        if ( p2.x1 > p1.x2 ) return p;
        return new Pair(Math.max(p1.x1,p2.x1),Math.min(p1.x2,p2.x2));
    }

    public static void main(String[] args) {
        MinimumArrow ma = new MinimumArrow();
        //int[][] p = {{10,16}, {2,8}, {1,6}, {7,12}};
        int[][] p = {{1,2}, {3,4}, {5,6}, {7,8}};
        System.out.println(ma.findMinArrowShots(p));
    }
    /*
    A simpler one.
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int arrowPos = points[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][1];
        }
        return arrowCnt;
    }
     */

}
