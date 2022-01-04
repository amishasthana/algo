package Contest;
/*
1775. Equal Sum Arrays With Minimum Number of Operations

    User Accepted: 1507
    User Tried: 2019
    Total Accepted: 1564
    Total Submissions: 3824
    Difficulty: Medium

You are given two arrays of integers nums1 and nums2, possibly of different lengths. The values in the arrays are between 1 and 6, inclusive.

In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.

Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of values in nums2. Return -1​​​​​ if it is not possible to make the sum of the two arrays equal.



Example 1:

Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
Output: 3
Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
- Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
- Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
- Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].

Example 2:

Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
Output: -1
Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.

Example 3:

Input: nums1 = [6,6], nums2 = [1]
Output: 3
Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
- Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
- Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
- Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].



Constraints:

    1 <= nums1.length, nums2.length <= 105
    1 <= nums1[i], nums2[i] <= 6


 */
public class EqualSumSmallestMovement {
    public int minOperations(int[] nums1, int[] nums2) {
        boolean fSmallest = (nums1.length <= nums2.length)?true:false;
        int[] n1M = new int[6];
        int[] n2M = new int[6];
        int sum1 = fill(nums1,n1M); int sum2 = fill(nums2,n2M);
        if (fSmallest) {
            if (nums1.length*6 < nums2.length) return -1;
        } else {
            if (nums2.length*6 < nums1.length) return -1;
        }
        if (sum1 == sum2) return 0;
        int diff = Math.abs(sum1 - sum2);
        if (sum1 <  sum2) return findMinOps(n1M,n2M,diff);
        return findMinOps(n2M,n1M,diff);
    }

    private int findMinOps(int[] n1,int[] n2,int diff) {
        int ops = 0;
        while (diff > 0) {
            int D = 1;
            for(int i = 0;i < 5;i++) {
                int total =   n1[i] + n2[5-i];
                int div = diff/(5-i);
                if (div > total) {
                    ops += total;
                    diff -= (total*(5-i));
                } else {
                    ops += div+ ((diff%(5-i) > 0)?1:0);
                    div = 0;
                    return ops;
                }
            }

        }
        return ops;
    }

    private int fill(int[] n, int[] nA) {
        int sum = 0;
        for(int i = 0; i < n.length;i++) {
            sum += n[i];
            nA[n[i]-1]++;
        }
        return sum;
    }

    public static void main(String[] args) {
        EqualSumSmallestMovement essm = new EqualSumSmallestMovement();
        int[] n1 = {6,6};
        int[] n2 = {1};
        System.out.println(essm.minOperations(n1,n2));
    }

}
