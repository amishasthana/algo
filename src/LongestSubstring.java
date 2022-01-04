import java.util.*;

public class LongestSubstring {
    /*
    Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


     */

    public int lengthOfLongestSubstring(String s) {
         if((s == null) || (s.length() == 0)) return 0;
         int maxSize = 0;
         Map<Character,Integer> mapOfChar = new HashMap<>();//Character and last time it came....
         int i = 0;
         int sIndex = 0;
         int cSize = 0;
         /*
            What are the options :
             a) character is completely new --> increase size.
             b) character exist but last one is before startIndex, --> increase size.
             c) character exist and on or after startIndex --> startIndex move to lastIndex+1. Size decrease .
                Before it compare to maxSize....
          */

         for(char c : s.toCharArray()) {
             Integer lastPos = mapOfChar.get(c);
             if (lastPos ==null) {
                 lastPos = i;
                 cSize++;
             } else {//Allready exist
                 if (sIndex <= lastPos) {//Need to discard.
                     if (cSize >= maxSize) {
                         maxSize = cSize;
                     }
                     // Sindex lastPos   i -->  current cSize = (i - sIndex)
                     //  move sindex = lastPos+1
                     // => cSize = (i -(lastPos+1))+1
                     cSize = i - lastPos;
                     sIndex = lastPos+1;
                 } else {
                     cSize++;
                 }
             }
             if (cSize >= maxSize) {
                 maxSize = cSize;
             }
             mapOfChar.put(c,i);
             i++;
         }

         return maxSize;
    }

    public static void main(String[] args) {
        String Input= "pwwkew";
        LongestSubstring ls = new LongestSubstring();
        System.out.println(ls.lengthOfLongestSubstring(Input));
    }
}
