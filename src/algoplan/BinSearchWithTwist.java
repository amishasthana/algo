package algoplan;

/*
1060. Missing Element in Sorted Array
Medium

Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k,
 return the kth missing number starting from the leftmost number of the array.



Example 1:

Input: nums = [4,7,9,10], k = 1
Output: 5
Explanation: The first missing number is 5.

Example 2:

Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.

Example 3:

Input: nums = [1,2,4], k = 3
Output: 6
Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.



Constraints:

    1 <= nums.length <= 5 * 104
    1 <= nums[i] <= 107
    nums is sorted in ascending order, and all the elements are unique.
    1 <= k <= 108


Follow up: Can you find a logarithmic time complexity (i.e., O(log(n))) solution?
Accepted
79,633
Submissions
143,974
Seen this question in a real interview before?

What is the idea -->
I want to have Start element +K.
At any other position there can be 0 --> k+ element missing. We dont care beyond K.

< The answer is alywas beteen no+k to nend+k
say 2,3,4,7
    0 0 0 2(5,6 is missing)

    You can go to any point in array.
     if ( Nth - n0 = th- 0 --> No element missing. )
     Otherwise  --> (Nth - n0) - (th -0)
     So if basically get to a position where there is JUST less then K, then the ans is
     that elem + K- whatever missing at that elem.

     So -->
     2,3,4,7
     0 0 0 2(5,6 is missing)
      k = 3 --> 7 + 3 - 2 = 8.
      k = 2 --> 4 +  2 - 0= 6

     Input: nums = [4,7,9,10], k = 3
                    0 2 3  3
                    7 + (3-2) = 8
Output: 8


 */
public class BinSearchWithTwist {
    public int missingElement(int[] nums, int k) {

         int L = 0; int R = nums.length-1;//L inclusive, R inclusive
         int nLMiss = 0;
         int indexJustLess = L;
         int indexJustLessMiss = 0;
         while(L <= R) {
             int M = L + (R-L)/2;
             int mMiss = (nums[M] - nums[L] -(M-L)) + nLMiss;
             if (mMiss >= k) {//even for equal wLe need to find just less. so move left
                 R = M-1;
             } else { //less misses move right, is there something better
                 indexJustLess = M;
                 indexJustLessMiss = mMiss;
                 nLMiss = mMiss+ ( ((M+1) < nums.length)? ( nums[M+1] - nums[M] -1):0);
                 L = M+1;
             }
         }


        return nums[indexJustLess] + (k - indexJustLessMiss);
    }




    //return index such that nums[i] <= searched num.
    public static void main(String[] args) {
        BinSearchWithTwist bsw = new BinSearchWithTwist();
        int[] A = {4,7,9,10};
        System.out.println(bsw.missingElement(A,10));
    }

}
