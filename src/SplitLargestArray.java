/*
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

The above example seem to be wrong :
       10,5,2 = 17
        7,8 + 15

        So 17 is smallest.

        I am thinking have m buckets in MIN heap. Start filling from biggest to smallest . Always put in smallest bucket first.
 */
public class SplitLargestArray {

        public int splitArray(int[] nums, int m) {
            int max = 0; long sum = 0;
            for (int num : nums) {
                max = Math.max(num, max);
                sum += num;
            }
            if (m == 1) return (int)sum;
            //binary search
            long l = max; long r = sum;
            while (l <= r) {
                long mid = (l + r)/ 2;
                if (valid(mid, nums, m)) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            System.out.println("Final solution "+l);
            return (int)l;

        }
        public boolean valid(long target, int[] nums, int m) {
            int count = 1;
            long total = 0;
            for(int num : nums) {
                total += num;
                if (total > target) {
                    total = num;
                    count++;
                    if (count > m) {
                        return false;
                    }
                }
            }
            return true;
        }

        public static void main(String[] args) {
            int[] A = {4,3,4,3,3};
            SplitLargestArray sla = new SplitLargestArray();
            sla.splitArray(A,2);

        }


}
