package Goog;

import java.util.HashSet;
import java.util.Set;

/*
Example 1:

Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24

Example 2:

Input: [1, 2, 1, 2]
Output: False

Note:

    The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
    Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
    You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

 */
public class Calc24 {
    public boolean judgePoint24(int[] nums) {
        if ( ( nums == null) || (nums.length == 0) ) return false;
        int n = nums.length;
        Set<Integer>[] DP = new Set[n];
        for(int i = 0; i < n; i++) DP[i] = new HashSet<Integer>();
        DP[n-1].add(nums[n-1]);
        for(int i = n-2; i >= 0; i--) {
            Set<Integer> tempSet = new HashSet<>();
            tempSet.add(nums[i]);

            Set<Integer> s1 = createNewSet(tempSet, DP[i+1] );
            DP[i].addAll(s1);
            if ( i < n-2) {
                Set<Integer> tempSet2 = new HashSet<>();
                tempSet2.add(nums[i+1]);
                Set<Integer> s2 = createNewSet(createNewSet(tempSet, tempSet2),DP[i+2]);
                DP[i].addAll(s2);

            }
        }
        return DP[0].contains(24);
    }

    private Set<Integer> createNewSet(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> R = new HashSet<>();
        for( int i : s1) {
            for( int j : s2) {
                R.add(i+j);
                R.add(i-j);
                R.add(i*j);
                if (j != 0) {
                    int res = i/j;
                    R.add(res);
                }
            }
        }
        return R;
    }

    public static void main(String[] args) {
        int[] A1 = {4, 1, 8, 7};
        Calc24 c24 = new Calc24();
        System.out.println(c24.judgePoint24(A1));
    }

}
