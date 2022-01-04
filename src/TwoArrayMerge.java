import java.util.Arrays;

public class TwoArrayMerge {
    /*
    Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

     */

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int idx1 = m-1;
            int idx2 = n-1;
            int currNums1Elem = Integer.MIN_VALUE;
            int currNums2Elem = Integer.MIN_VALUE;
            int i = nums1.length-1;
            for(;i >= 0; i--) {
                if ( idx1 <0) {
                    currNums1Elem = Integer.MIN_VALUE;
                } else {
                    currNums1Elem = nums1[idx1];
                }
                if (idx2 < 0) {
                    currNums2Elem = Integer.MIN_VALUE;
                } else {
                    currNums2Elem = nums2[idx2];
                }


                if (currNums2Elem >= currNums1Elem) {
                    nums1[i] = currNums2Elem;
                    idx2--;
                } else {
                    nums1[i] = currNums1Elem;
                    idx1--;
                }
            }
            System.out.println(Arrays.toString(nums1));

        }

        public static void main(String[] args) {
            TwoArrayMerge tam = new TwoArrayMerge();
            int[] nums1 = {1,2,3,0,0,0};
            int[] nums2 = {2,5,6} ;
            tam.merge(nums1,3,nums2,3);
        }

}
