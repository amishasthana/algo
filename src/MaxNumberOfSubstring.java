import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
1297. Maximum Number of Occurrences of a Substring


Given a string s, return the maximum number of ocurrences of any substring under the following rules:

    The number of unique characters in the substring must be less than or equal to maxLetters.
    The substring size must be between minSize and maxSize inclusive.



Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).

Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.

Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3

Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0



Constraints:

    1 <= s.length <= 10^5
    1 <= maxLetters <= 26
    1 <= minSize <= maxSize <= min(26, s.length)
    s only contains lowercase English letters.

 User Accepted: 1066
    User Tried: 1565
    Total Accepted: 1089
    Total Submissions: 3417
    Difficulty: Medium

Given a string s, return the maximum number of ocurrences of any substring under the following rules:

    The number of unique characters in the substring must be less than or equal to maxLetters.
    The substring size must be between minSize and maxSize inclusive.



Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).

Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.

Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3

Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0



Constraints:

    1 <= s.length <= 10^5
    1 <= maxLetters <= 26
    1 <= minSize <= maxSize <= min(26, s.length)
    s only contains lowercase English letters.


 */
public class MaxNumberOfSubstring {

    Map<String,Integer> mapOfString = new HashMap<>();
    int maxOccurence = 0;
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
          char[] cArray = s.toCharArray();
          StringBuilder strB = new StringBuilder();
          for(int i = 0; i < s.length(); i++) {
              Set<Character> uniqueChar = new HashSet<>();
              for(int initial = i; initial < Math.min((i+minSize-1),cArray.length);initial++) {
                  uniqueChar.add(cArray[initial]);
                  strB.append(cArray[initial]);
              }
              for(int j = (i+minSize-1); j < Math.min((i+maxSize),cArray.length); j++) {
                  uniqueChar.add(cArray[j]);
                  if (uniqueChar.size() >  maxLetters) {
                      break;
                  }
                  strB.append(cArray[j]);
                  String key = strB.toString();
                  Integer freq = 1+ ((mapOfString.get(key) == null)?0:mapOfString.get(key));
                  System.out.println(" key "+key+" freq "+freq);
                  mapOfString.put(key,freq);
                  if (freq > maxOccurence) {
                      maxOccurence = freq;
                  }
              }
              strB.delete(0,strB.length());
          }
          return maxOccurence;
    }

    public static void main(String[] args) {
        /*"aaaa"
        1
        3
        3*/
        MaxNumberOfSubstring maxNumber = new MaxNumberOfSubstring();
        maxNumber.maxFreq("aaaa",1,3,3);
    }



}
