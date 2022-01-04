package algoplan;

import java.util.HashMap;
import java.util.Map;

/*
340. Longest Substring with At Most K Distinct Characters
Medium

Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.



Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.

Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.



Constraints:

    1 <= s.length <= 5 * 104
    0 <= k <= 50


 */
public class LSubStringV1 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character,Integer> A = new HashMap<>();
        int ret = Math.min(s.length(),k);
        int i = 0, j = 0;
        int D = 0;
        for(;i < s.length();i++) {
                char C = s.charAt(i);
                int cValue = A.getOrDefault(C,0);
                if (cValue == 0) {
                    D++;
                }
                cValue++;
                A.put(C,cValue);
                if (D <= k) ret = Math.max(ret,i-j+1);
                if (D > k) {
                    while ( D  > k) {
                        C = s.charAt(j++);
                        cValue = A.get(C);
                        cValue--;
                        A.put(C,cValue);
                        if (cValue== 0) D--;
                    }
                }
        }

        return ret;
    }

    public int longestOnes(int[] nums, int k) {
        int ret = 0;
        int i = 0, j = 0;
        for(;i < nums.length;i++) {
            int C = nums[i];
            if (C == 0) {
                k--;
            }
            if (k >= 0) ret = Math.max(ret,i-j+1);
            if (k == -1) {
                while ( k < 0) {
                    C = nums[j++];
                    if (C == 0) k++;
                }
            }
        }

        return ret;
    }


    public static  void main(String[] args) {
        LSubStringV1 lv1 = new LSubStringV1();
        String s = "eceba";
        int[] A ={2,4,6};
        //System.out.println(--A[1]);
        System.out.println(lv1.lengthOfLongestSubstringKDistinct(s,2));
    }
}
