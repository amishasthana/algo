import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/*
56. Merge Intervals
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
Accepted
506,582
Submissions
1,333,055
 */
public class MergeInterval {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(final int[] entry1, final int[] entry2) {
                return entry1[0] - entry2[0];
            }
        });
        int eIndex = 0;
        Set<Integer> ignoreSet = new HashSet<>();
        for(int i = 1;i < intervals.length;i++) {
            if (intervals[i][0] <= intervals[eIndex][1]) {
                intervals[eIndex][1] = Math.max(intervals[i][1],intervals[eIndex][1]);
                ignoreSet.add(i);
            } else {
                eIndex = i;
            }
        }
        int[][] retV = new int[intervals.length-ignoreSet.size()][2];
        for(int i = 0;i < intervals.length;i++) {
            if (ignoreSet.contains(i)) continue;
            retV[i][0] = intervals[i][0];
            retV[i][1] = intervals[i][1];
        }
        return retV;
    }
}
