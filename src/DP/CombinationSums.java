package DP;

/*
377. Combination Sum IV
Medium

Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.



Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.

 */
public class CombinationSums {
    /*
   1,2,3 --> 4
   1111
   112
   13
   22

   However 121
   211 31 are consider different and thats why its 7.
   In that case we can use the below method. For each target consider, each num.
   Now there will be double counting(equivalent to same set of number but different seq.)
   So for example : on 1 --> 1 , 2 you have 2 and 11
   Sor for three from 1:
   1,2
   From 2
   2,1(duplicate)
   111
  and from 3 --> 4.
  So 3 be

    If we had done opposite, then we would not count duplicate.
    So 3 --3 and 4 --> 4.

     */
    public int combinationSum4(int[] nums, int target) {
        if ( (nums == null) || (nums.length == 0)) return 0;

        int[] S = new int[target+1];


        int[] N = nums;
        int n = N.length;

                S[0] = 1;

           /*
               For  basically how many different ways to come to t.
           */
        for(int j = 1; j <=target ; j++) {
            for(int i = 0; i < n; i++) {

                if (j >= N[i]) S[j] = S[j]+S[j-N[i]] ;
                System.out.println("j "+j+" i "+i+" N[i] "+N[i]+" SI "+S[j]);
            }
        }// End of outer for
        return S[target];
    }

    public int combinationSum4T(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int j = 0; j < nums.length; j++) {
        for (int i = 1; i < comb.length; i++) {
                if (i - nums[j] >= 0) {
                    comb[i] += comb[i - nums[j]];
                }
            }
        }
        return comb[target];
    }


    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        int[] A1 = {3,4,5,6,7,8,9,10};
        CombinationSums cs = new CombinationSums();
        //System.out.println(cs.combinationSum4(A,4));
        System.out.println(cs.combinationSum4T(A,4));
    }

}
