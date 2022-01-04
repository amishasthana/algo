package Goog;
/*
57. Insert Interval
Hard

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 */

import java.util.ArrayList;
import java.util.List;

public class IntervalMerge {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = null;
        if ( (intervals == null) || (intervals.length == 0) )  {
            if ( (newInterval == null)  ) return null;
            res = new int[1][2];
            res[0][0] = newInterval[0];
            res[0][1] = newInterval[1];
            return res;

        }
        if ( (newInterval == null) ) return intervals;
        List<Integer> sList = new ArrayList<>();
        List<Integer> eList = new ArrayList<>();

        int cS = newInterval[0]; int cE = newInterval[1];
        boolean notMerged = true;
        for(int i = 0; i < intervals.length; i++ ) {
            if ( ( intervals[i][0] > cE ) || (intervals[i][1] < cS) ) {
                if ( notMerged &&  (intervals[i][0] > cE) ) {
                    sList.add(cS );  eList.add(cE);
                    cS = Integer.MIN_VALUE; cE = Integer.MIN_VALUE;
                    notMerged = false;
                }
                sList.add(intervals[i][0] );  eList.add(intervals[i][1] );
            } else {
                cS = Math.min(cS, intervals[i][0] );
                cE = Math.max(cE,intervals[i][1] );
            }
        }
        if (notMerged) {
            sList.add(cS );  eList.add(cE);
        }
        res = new int[sList.size()][2];
        for(int i = 0; i < sList.size(); i++ ) {
            res[i][0] = sList.get(i); res[i][1] = eList.get(i);
        }

        return res;
    }

    public static void main(String[] args) {
        IntervalMerge im = new IntervalMerge();
        //int[][] A = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[][] A = {{1,5}};
        int[] B = {2,3};
        int[][] R = im.insert(A,B);
        for(int[] RR : R) {
            System.out.println(RR[0]+","+RR[1]);
        }
    }

}
