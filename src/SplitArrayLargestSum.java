import java.util.HashMap;
import java.util.Map;

/*
410. Split Array Largest Sum
Hard

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

    1 ≤ n ≤ 1000
    1 ≤ m ≤ min(50, n)

Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

 */
public class SplitArrayLargestSum {
    long maxSum = Integer.MAX_VALUE+1l;
    Map<String,Long> dpMap = new HashMap<>();

    public int splitArray(int[] nums, int m) {
        maxSum = findMaxSum(nums,0,nums.length,m);
        return (int)maxSum;
    }//End of method


    private long findMaxSum(int[] num,int sIndex,int eIndex,int m) {
        if (((eIndex - sIndex) < m) || (m == 0)) return (Integer.MAX_VALUE+1l);//Can not break into m.
        String key = getKey(sIndex,eIndex,m);
        if (dpMap.get(key) != null) return dpMap.get(key);
        long tMinMax = Integer.MAX_VALUE+1l;
        long cSum = 0;
        for (int i = sIndex; i < eIndex; i++) {
            cSum += num[i];
            long cMinMax = Math.max(cSum,findMaxSum(num, i+1, eIndex, m - 1));//Basically saying what is the minMax for starting at i with m division.
            if (cMinMax < tMinMax) {
                tMinMax = cMinMax;
            }
        }//End of for loop.
        tMinMax = Math.min(cSum,tMinMax);
        dpMap.put(key,tMinMax);
        return tMinMax;
    }// End of method.

    private String getKey(int s,int e,int m) {
        return s+"_"+e+"_"+m;
    }

    public static void main(String[] args) {
        //int[] nums ={7,2,5,10,8};
        int[] nums2 = {2,3,1,2,4,3};
        SplitArrayLargestSum sp = new SplitArrayLargestSum();
        //System.out.println(sp.splitArray(nums,2));
        System.out.println(sp.splitArray(nums2,5));
    }

}
