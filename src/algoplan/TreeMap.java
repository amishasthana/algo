package algoplan;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/*
1182. Shortest Distance to Target Color
Medium

You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c,
 return the shortest distance between the given index i and the target color c. If there is no solution return -1.



Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation:
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).

Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.



Constraints:

    1 <= colors.length <= 5*10^4
    1 <= colors[i] <= 3
    1 <= queries.length <= 5*10^4
    queries[i].length == 2
    0 <= queries[i][0] < colors.length
    1 <= queries[i][1] <= 3


 */
public class TreeMap {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        TreeSet<Integer> oneS = new TreeSet<>();
        TreeSet<Integer> twoS = new TreeSet<>();
        TreeSet<Integer> threeS = new TreeSet<>();
        for(int i = 0; i < colors.length;i++) {
            if (colors[i] == 1) {
                oneS.add(i);
            } else if (colors[i] == 2) {
                twoS.add(i);
            } else {
                threeS.add(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < queries.length;i++) {
            int c = queries[i][1];
            int index = queries[i][0];
            if (c == 1) {
                if(oneS.isEmpty()) {
                    ans.add(-1);
                } else {
                    int ceiling = (oneS.ceiling(index) == null)?Integer.MAX_VALUE:oneS.ceiling(index);
                    int floor = (oneS.floor(index) == null)?Integer.MAX_VALUE:oneS.floor(index);
                    ans.add(Math.min(Math.abs(ceiling-index),Math.abs(floor-index)));
                }
            } else if (c == 2) {
                if(twoS.isEmpty()) {
                    ans.add(-1);
                } else {
                    int ceiling = (twoS.ceiling(index) == null)?Integer.MAX_VALUE:twoS.ceiling(index);
                    int floor = (twoS.floor(index) == null)?Integer.MAX_VALUE:twoS.floor(index);
                    ans.add(Math.min(Math.abs(ceiling-index),Math.abs(floor-index)));
                }
            } else {
                if(threeS.isEmpty()) {
                    ans.add(-1);
                } else {
                    int ceiling = (threeS.ceiling(index) == null)?Integer.MAX_VALUE:threeS.ceiling(index);
                    int floor = (threeS.floor(index) == null)?Integer.MAX_VALUE:threeS.floor(index);
                    ans.add(Math.min(Math.abs(ceiling-index),Math.abs(floor-index)));
                }
            }
        }
        return  ans;
    }
}
