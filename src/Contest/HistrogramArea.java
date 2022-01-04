package Contest;

import java.util.ArrayDeque;
import java.util.Deque;

/*
84. Largest Rectangle in Histogram
Hard

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.




Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].




The largest rectangle is shown in the shaded area, which has area = 10 unit.



Example:

Input: [2,1,5,6,2,3]
Output: 10



Example 1:

Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

 */
public class HistrogramArea {
    class P {
        int p;
        int h;
        public P(int pp,int hh) {
            p = pp; h = hh;
        }
    }
    public int largestRectangleArea(int[] heights) {
         Deque<P> dq = new ArrayDeque<>();
         // Has the height which can be sustained so far.
        int[] A = new int[heights.length];

         int maxArea = Integer.MIN_VALUE;
         for(int i = 0; i < heights.length;i++) {
             P pe = new P(i, heights[i]);
             int l = 1;
             if (dq.isEmpty()) {
                 dq.add(pe);
             } else {
                 P last = null;
                 while (!dq.isEmpty() && (dq.getLast().h >= pe.h)) {
                     last = dq.pollLast();
                 }
                 if (last != null) {
                     l  = Math.abs(last.p - i)+1;
                     pe.p = last.p;
                 }

                 dq.add(pe);
             }
             A[i] = pe.h*l;
         }
        while (!dq.isEmpty()) dq.poll();
        for(int i = heights.length-1; i >=0 ;i--) {
            P pe = new P(i, heights[i]);
            int l = 1;
            if (dq.isEmpty()) {
                dq.add(pe);
            } else {
                P last = null;
                while (!dq.isEmpty() && (dq.getLast().h >= pe.h)) {
                    last = dq.pollLast();
                }
                if (last != null) {
                    l  = Math.abs(last.p - i)+1;
                    pe.p = last.p;
                }

                dq.add(pe);
            }
            A[i] += pe.h*l-pe.h;
            maxArea = Math.max(A[i],maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        HistrogramArea ha = new HistrogramArea();
        int[] A = {999,999,999,999};
        System.out.println(ha.largestRectangleArea(A));
    }
}
