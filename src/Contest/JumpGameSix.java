package Contest;

import java.util.ArrayDeque;
import java.util.Deque;

/*
1696. Jump Game VI

    User Accepted: 986
    User Tried: 2513
    Total Accepted: 1027
    Total Submissions: 5585
    Difficulty: Medium

You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.



Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.

Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.

Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0



Constraints:

     1 <= nums.length, k <= 105
    -104 <= nums[i] <= 104
IMP
int[] A = {0,-1,-2,-3,1}; --> an example of why needed to look at evry element multiple times

 */
public class JumpGameSix {
    /*
    public int maxResult(int[] nums, int k) {
        if (nums.length == 1) return  nums[0];
        int s = 0;
        int e = Math.min(nums.length-1, s + k);
        int sum = nums[0];
        boolean lastElemUsed = false;
        while (s < (nums.length-1)) {
            int maxN = Integer.MIN_VALUE;
            int nextElem = 0;
            boolean anyP = false;
            for (int i = s+1; i <= e; i++) {
                if (nums[i] >= 0) {
                    anyP = true;
                    sum += nums[i];
                    nextElem = i;
                } else if (!anyP) {
                    maxN = Math.max(nums[i], maxN);
                    if (maxN == nums[i]) nextElem = i;
                }
            }
            if (nextElem == (nums.length-1)) lastElemUsed = true;
            if (!anyP) {
                sum += maxN;
            }
            s = e;
            e = Math.min(nums.length-1, nextElem + k);
        }
        if (!lastElemUsed) sum += nums[nums.length-1];
        return sum;
    }

     */

    /*
    public int maxResult(int[] nums, int k) {
        int[] res = new int[nums.length];
        Arrays.fill(res,Integer.MIN_VALUE);
        res[0] = nums[0];
        for(int i = 0;i < (nums.length-1);i++) {
            for(int j = 1;( j <= k) && ((i+j) <= (nums.length-1));j++) {
                res[i+j] = Math.max(res[i+j],res[i]+nums[i+j]);
            }
        }
        return res[nums.length-1];
    }
    */

    public int maxResult(int[] nums, int k) {
        Deque<Integer> DQ = new ArrayDeque<>();
        for (int i = 0; i < nums.length;i++) {
            int cElem = (DQ.isEmpty()?0:nums[DQ.getFirst()])+nums[i];
            while (!DQ.isEmpty() && (nums[DQ.getLast()] <= cElem)) DQ.pollLast();
            DQ.addLast(i);
            while(!DQ.isEmpty() && (i >= (DQ.getFirst()+k))) DQ.pollFirst();
            nums[i] = cElem;
        }
        return nums[nums.length-1];
    }


    public static void main(String[] args) {
        JumpGameSix jgs = new JumpGameSix();
        int[] A = {10,-5,-2,4,0,3};
        System.out.println(jgs.maxResult(A,3));
    }
}
