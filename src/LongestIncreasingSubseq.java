/*
https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
God read.

tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:

len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6

We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.

Each time we only do one of the two:

(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i]

Doing so will maintain the tails invariant. The the final answer is just the size.

Java

public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;
    for (int x : nums) {
        int i = 0, j = size;
        while (i != j) {
            int m = (i + j) / 2;
            if (tails[m] < x)
                i = m + 1;
            else
                j = m;
        }
        tails[i] = x;
        if (i == size) ++size;
    }
    return size;
}
// Runtime: 2 ms

 */
public class LongestIncreasingSubseq {
    public int lengthOfLIS(int[] nums) {
        int maxL = 0;
        int[] lisA = new int[nums.length];// Basically lis[i] will contain the end elemnt of a LIS of size i.
        maxL = createLis(nums,lisA);
        return maxL;
    }

    private int createLis(int[] nums,int[] lisA) {
        lisA[0] = nums[0];
        int lIndex = 1;//Always point to empty cell.

        for(int i = 1; i < nums.length;i++) {
            if (nums[i] > lisA[lIndex-1]) {//Larget
                lisA[lIndex++] = nums[i];
            } else if (nums[i] < lisA[0]) {
                lisA[0] = nums[i];
            } else {
                int cIndex = findCeiling(lisA, nums[i], lIndex);
                lisA[cIndex] = nums[i];
            }
        }
        return lIndex;
    }

    public int findCeiling(int[] lisA,int elem,int lIndex) {
        int lo = -1;
        int hi = lIndex;
        while ((hi-lo) > 1) {
            if (lisA[(lo+hi)/2] < elem) {
                lo = (lo+hi)/2;
            } else if (lisA[(lo+hi)/2] >= elem) {
                hi = (lo+hi)/2;
            }
        }
        return hi;
    }

    public static void main(String[] args) {
        LongestIncreasingSubseq lis = new LongestIncreasingSubseq();
        //int[] iA = {10,9,2,5,3,7,101,18};
        int[] iA = {2,2};
        System.out.println(lis.lengthOfLIS(iA));
    }

}
