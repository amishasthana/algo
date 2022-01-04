package Goog;

import java.util.Arrays;
import java.util.Comparator;

/*
253. Meeting Rooms II
Medium

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Example 2:

Input: [[7,10],[2,4]]
Output: 1

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 */
public class MeetingRoomTwo {
    public int minMeetingRooms(int[][] intervals) {
             if ((intervals == null) || (intervals.length == 0)) return 0;
             int minRoom = intervals.length;
             int[] E = new int[minRoom];
             int maxOccupied = 0;
              Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
              for(int i = 0; i < intervals.length;i++) {
                  boolean assigned = false;
                  for (int j = 0; j  < maxOccupied;j++) {
                      if (E[j] <= intervals[i][0]) {
                          E[j] = intervals[i][1];
                          assigned = true;
                          break;
                      }
                  }
                  if (!assigned) {
                      E[maxOccupied] = intervals[i][1];
                      maxOccupied++;
                  }

              }
              return maxOccupied;
    }

    public static void main(String[] args) {
        MeetingRoomTwo mrt = new MeetingRoomTwo();
        //int[][] meet = {{0,30},{6,10},{15,20}};
        int[][] meet = {{7,10},{2,4}};
        System.out.println(mrt.minMeetingRooms(meet));
    }

}
