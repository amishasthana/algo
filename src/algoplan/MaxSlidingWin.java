package algoplan;

import java.util.ArrayDeque;
import java.util.Arrays;

/*
239. Sliding Window Maximum
Hard

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.



Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:

Input: nums = [1], k = 1
Output: [1]



Constraints:

    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    1 <= k <= nums.length

Accepted
496,381
Submissions
1,080,984
The idea is like this -->
Queue --> with the highest at start.
Any element push to tail, discard any element smaller then it. So queue is always decreasing from start.
Highest top of queue if in ranger. Otherwise discard and get next.
 */
public class MaxSlidingWin {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> mQ = new ArrayDeque<>();
        // 0,1,2,3 --> 4 , k = 2
        int[] A = new int[nums.length-k+1];
        for(int i = 0; i < nums.length;i++) {
            while ((mQ.peekLast() != null) && (nums[mQ.peekLast()] <= nums[i])) mQ.pollLast();
            mQ.addLast(i);
            if (i >= (k-1)) {
                while (mQ.peekFirst() < (i-k+1) ) {
                    mQ.pollFirst();
                }
                A[i-k+1] = nums[mQ.peekFirst()];
            }
        }
        return A;
    }

    public static void main(String[] args) {
        MaxSlidingWin maxSlidingWindow = new MaxSlidingWin();
        int[] B = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow.maxSlidingWindow(B,3)));
    }
}
