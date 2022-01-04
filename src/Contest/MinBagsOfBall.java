package Contest;

import java.util.Arrays;

/*
1760. Minimum Limit of Balls in a Bag
Medium

You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.

You can perform the following operation at most maxOperations times:

    Take any bag of balls and divide it into two new bags with a positive number of balls.
        For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.

Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.

Return the minimum possible penalty after performing the operations.



Example 1:

Input: nums = [9], maxOperations = 2
Output: 3
Explanation:
- Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
- Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.

Example 2:

Input: nums = [2,4,8,2], maxOperations = 4
Output: 2
Explanation:
- Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
The bag with the most number of balls has 2 balls, so your penalty is 2 an you should return 2.

Example 3:

Input: nums = [7,17], maxOperations = 2
Output: 7



Constraints:

    1 <= nums.length <= 105
    1 <= maxOperations, nums[i] <= 109

Accepted
3,349
Submissions
7,103
 */
public class MinBagsOfBall {
    public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int mo = maxOperations;
        int l = nums.length;
        int max = nums[l - 1];
        if (max == 1) return 1;
        int min = ( ( max % (mo+1)) == 0)? (( max /(mo+1))-1): ( max /(mo+1));
        int cOps = mo;
        //int ans = max;
        while (min <= max) {
            int mid = min + (max - min)/2;
            for(int i = l -1;i >= 0; i--) {
                if (nums[i] <= mid) {
                    break;
                }
                int div = (nums[i]/mid) ;
                int pOps = ( ( nums[i] % mid) == 0)? (div-1): div;
                cOps -= pOps;
                if (cOps < 0) break;
            }// end of for
            if (cOps < 0) {
                min = mid+1;
            } else {
                max = mid-1;
                //ans = Math.min(ans,mid);
            }
            cOps = mo;
        } // end of while
        return min;
    }

    public int LminimumSize(int[] nums, int maxOperations) {
        int k  = maxOperations;
        int[] A = nums;
        int left = 1;
        int right = Integer.MIN_VALUE;//1_000_000_000;
        for(int i : A) {
            right = Math.max(right,i);
        }
        while (left < right) {
            int mid = (left + right) / 2, count = 0;
            for (int a : A)
                count += (a - 1) / mid;
            if (count > k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] A = {1,1,1};
        MinBagsOfBall mbb = new MinBagsOfBall();
        System.out.println(mbb.minimumSize(A,1));
    }

}
