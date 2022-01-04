package algoplan;

import java.util.*;

/*
1229. Meeting Scheduler
Medium

Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]

Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []



Constraints:

    1 <= slots1.length, slots2.length <= 104
    slots1[i].length, slots2[i].length == 2
    slots1[i][0] < slots1[i][1]
    slots2[i][0] < slots2[i][1]
    0 <= slots1[i][j], slots2[i][j] <= 109
    1 <= duration <= 106


 */
public class MeetingTime {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(slots2, Comparator.comparingInt(o -> o[0]));
        List<Integer> l1 = new ArrayList<>();
        int i = 0; int j = 0;
        for(;(i < slots1.length && j < slots2.length);) {
            int s1 = slots1[i][0];
            int e1 = slots1[i][1];
            int s2 = slots2[j][0];
            int e2 = slots2[j][1];
            if (e1 <= s2) {
                i++; continue;
            } else if (e2 <= s1) {
                j++;continue;
            } else {
                if  ((Math.min(e1,e2) - Math.max(s1,s2)) >= duration) {
                    l1.add(Math.max(s1,s2));
                    l1.add(Math.max(s1,s2)+duration);
                    return l1;
                }
                if (e1 >= e2) {
                    j++;
                } else {
                    i++;
                }
            }
        }

        return l1;
    }

    public static void main(String[] args) {
        MeetingTime mt = new MeetingTime();
        int[][] A = {{10,50},{60,120},{140,210}};
        int[][] B = {{0,15},{60,70}};

        //mt.minAvailableDuration(A,B,8);
        /*
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(5);
        ts.add(3);
        ts.add(3);
        ts.add(6);
        ts.add(8);
        System.out.println(ts.ceiling(5));
        System.out.println(ts.floor(5));
        System.out.println(ts.ceiling(7));
        System.out.println(ts.floor(7));
        System.out.println(ts.first());

         */
    }
}
