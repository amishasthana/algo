package DP;
public class SplitArryLargestSumAnother {

        public int splitArray(int[] nums, int m) {
            if ( ( nums == null) || (nums.length == 0 ) ) return 0;
            if ( nums.length < m)  return -1;// Can not break.
            int n = nums.length;
            int max = Integer.MIN_VALUE; int sumA = 0;
            for(int e : nums) {
                max = Math.max(max,e);
                sumA += e;
            }
            long lo = max; long hi  = sumA;

            while ( lo <= hi) {
                long mid = (lo+hi)/2;
                long sumL = 0;
                int par = 1;
                for( int i = 0 ; i < n; i++) {
                    sumL += nums[i];
                    if (sumL > mid) {
                        if ( ++par > m) {
                            break;
                        }
                        sumL = nums[i];
                    }
                }
                if ( par > m) {// increase period
                    lo = mid+1;
                } else {
                    hi = mid-1;
                }

            }
            return (int)lo;

        }



    public static void main(String[] args) {
        SplitArryLargestSumAnother ss = new SplitArryLargestSumAnother();
        //int[] A = {7,2,5,10,8};
        int[] A = {1,Integer.MAX_VALUE};
        System.out.println(ss.splitArray(A,2));
    }


}
