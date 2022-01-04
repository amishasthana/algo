package Contest;
/*
410. Split Array Largest Sum
Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.


 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int tSum = 0;int maxN = Integer.MIN_VALUE;
        for(int i : nums) {
            tSum += i;
            maxN = Math.max(i,maxN);
        };
        maxN = Math.max(maxN,tSum/m);
        long low = maxN; long high = tSum;
        while (low <= high) {
            long  mid = low + (high-low)/2;
            int p = 1; long csum = 0; //long maxSumForLoop = Integer.MIN_VALUE;
            for(int i = 0; i < nums.length;i++) {
                if ((csum+nums[i]) > mid) {
                    p++;
                    //maxSumForLoop = Math.max(maxSumForLoop,csum);
                    csum = nums[i];
                    if (p > m) {
                        break;
                    }
                } else {
                    csum += nums[i];
                }
            }//end of for
            if (p <= m) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return  (int)low;
    }

    public void pint()
    {
        int c = 0;
        while (c < 10) {
            System.out.println(++c);
        }
    }

    public static void main(String[] args) {
        SplitArrayLargestSum sal = new SplitArrayLargestSum();
        int[] A = {1,5,5};
        System.out.println(sal.splitArray(A,3));
    }
}
