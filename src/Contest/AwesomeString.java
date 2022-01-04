package Contest;
/*
1542. Find Longest Awesome Substring

    User Accepted: 309
    User Tried: 1262
    Total Accepted: 332
    Total Submissions: 2833
    Difficulty: Hard

Given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it palindrome.

Return the length of the maximum length awesome substring of s.



Example 1:

Input: s = "3242415"
Output: 5
Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.

Example 2:

Input: s = "12345678"
Output: 1

Example 3:

Input: s = "213123"
Output: 6
Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.

Example 4:

Input: s = "00"
Output: 2



Constraints:

    1 <= s.length <= 10^5
    s consists only of digits.

Solution : If a string is palindrome it will have the following:
a) Size n --> Even --> All even.
b) Odd ---> All even except one.
One way --> 0-9 --> Tree set. So if I ask how many of digit x between pos n and n+delta. I can get it.
we know its odd or even.
if odd, first find middle and see how many within bound. if not odd
   a) then no go b) find when if become even.
If even --> Start one by one. if any odd, then no go.

Solution 2 : Much better.
a) For each pos figure out for 0-9 how many digits so far. Just use a bit array and track odd/even.
b) Between any two pos just need to make sure the delta is all even except maybe one.
   --> If both odd or both even then we are even. if even/odd then issue.
c) Use this bit array to int, and use that as key.
d) For each key just maintain max/min. Now for even case, max-min is the possible value.
e) For odd, take a bit array find all combination by flipping one bit at a time and if the
key exist then find abs|min - max|

max of all is the solution.

public int longestAwesome(String s) {
        int res = 0, cur = 0, n = s.length(), seen[] = new int[1024];
        Arrays.fill(seen, n);
        seen[0] = -1;
        for (int i = 0; i < n; ++i) {
            cur ^= 1 << (s.charAt(i) - '0');
            for (int a = 0; a < 10; ++a)
                res = Math.max(res, i - seen[cur ^ (1 << a)]);
            res = Math.max(res, i - seen[cur]);
            seen[cur] = Math.min(seen[cur], i);
        }
        return res;
    }


 */

import java.util.*;

public class AwesomeString {
    Map<Integer, List<Integer>> mChar = new HashMap<>();
    int max = 1;
    public int longestAwesome(String s) {

        BitSet bs = new BitSet(10);

        List<Integer> zLis = new LinkedList<>();
        zLis.add(0);zLis.add(0);
        mChar.put(0,zLis);

        for(int i = 1; i <= s.length();i++) {//Calculated everything.
            int val = s.charAt(i-1)-'0';
            bs.flip(val);
            val = cBSToInt(bs);
            List<Integer> lis = mChar.getOrDefault(val,new LinkedList<>());
            if (lis.isEmpty()) {
                lis.add(i);lis.add(i);
                mChar.put(val,lis);
            } else {
                lis.set(1,i);//only max.
                max = Math.max(max,i - lis.get(0));//even calculated.
            }

        }
        // All even calculated. Now for odd.
        for(Map.Entry<Integer, List<Integer>> ES : mChar.entrySet()) {
            BitSet cBS = intToBytes(ES.getKey());
            int lMax = getMaxOdd(cBS);
            max = Math.max(max,lMax - ES.getValue().get(0));
        }
        return max;
    }



    private int getMaxOdd(BitSet bs) {
        int max = 1;
        for(int i =0; i < 10;i++) {
            bs.flip(i);
            int v = cBSToInt(bs);
            if (mChar.get(v) != null) {
                max = Math.max(max,mChar.get(v).get(1));
            }
            bs.flip(i);
        }
        return max;
    }

    private BitSet intToBytes( final int i ) {
        BitSet bs = BitSet.valueOf(new long[]{i});
        return bs;
    }

    private int cBSToInt(BitSet bs) {
        int value=0;
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i+1)) {
            value += (1 << i);
        }
        return value;
    }

    public static void main(String[] args) {
        AwesomeString as = new AwesomeString();
        //System.out.println(as.longestAwesome("0101447"));
        System.out.println(as.longestAwesome("213123"));
    }

    /*
    Example Input "3242415" Explanation with BitMask
54
mstr_shifu's avatar
mstr_shifu
61

Last Edit: August 21, 2020 12:00 PM

579 VIEWS

Definition of Awesomeness
String S is a palindrome if S equals to its reversed version: "1112111"
Any string is awesome if we can reorder its characters to make it a palindrome:
"24241" can be reordered to "24142".

Properties of Awesome string
There are 2 cases that a string can be reordered to be a palindrome:
Case 1. All of its characters have even occurrences:
"2424" -> there are two '2's and two '4's.
Case 2. All of its characters have even occurrences except one:
"24241" -> there are two '2's, two '4's and one '1'

Brute force solution
The most straighforward solution would be keeping track of all counters while looping through characters. Once we are at position i, we go back and subtract all our previous counters from our current counter to check if any of them is awesome. However, its time complexity is O(10*N^2): 10 digits * N chars * N comparisons which is not desirable

BitMask solution
We don't have to keep track of counters, we are only interested in odd counts in a substring. We can use one bit to say if some digit has even or odd count at any point.
Let 0 represent "even" and 1 represent "odd".
For example, for an input of "233":

    i = 0, char = '2', xor 2nd bit from right:
    mask = "100"
    i = 1, char = '3', xor 3rd bit from right:
    mask = "1100"
    i = 2, char = '3', xor 3rd bit from right:
    mask = "0100"

The last mask is "0100" which says it has only one number with odd count, so, the input can be rearranged to make it a palindrome: "233" => "323".

Even simpler, if the input is "22", we set and unset 2nd bit, then the remaining mask would be "000", which says we have numbers with all having even counts.

Now, let's analyze the example input "3242415" from problem definition.

    i = 0, char = '3', xor 3rd bit from right:

     mask = '0000001000'

    i = 1, char = '2', xor 2nd bit from right:

     mask = '0000001100'

    i = 2, char = '4', xor 4th bit from right:

     mask = '0000011100'

    i = 3, char = '2', xor 2nd bit from right:

     mask = '0000011000'

    i = 4, char = '4', xor 4th bit from right:

     mask = '0000001000'

    i = 5, char = '1', xor 1st bit from right:

     mask = '0000001010'

    i = 6, char = '5', xor 5th bit from right:

     mask = '0000101010'

The problem asks to find the longest awesome substring, it can be anywhere between i and j such that i <= j <= lenth of input. For this, on every step above, we need to memoize the current mask and check if we have seen similar mask before.
Update the answer if:
a. We have seen similar mask before.
b. We have seen a mask such that it differs from the current mask by one bit being different.

class Solution:
    def longestAwesome(self, s: str) -> int:
        n = len(s)
        ans, mask = 0, 0

        memo = [n] * 1024
        memo[0] = -1

        for i in range(n):
            mask ^= 1 << int(s[i])

			# Case 1. Check if we have seen similar mask
            ans = max(ans, i - memo[mask])

			# Case 2. Check for masks that differ by one bit
            for j in range(10):
                test_mask = mask ^ (1 << j)
                ans = max(ans, i - memo[test_mask])

			# save the earliest position
            memo[mask] = min(memo[mask], i)

        return ans

There are number of questions arise from the above solution:
Question 1. Why 1024?
Since the input only contains 10 digits ("0123456789"), we can only see 2^10 or 1024 variations of bit masks: 0000000000, 0000000001, .... , 1111111110, 1111111111
Question 2. What is 1 << int(s[i])
It shifts 1 by s[i] times, for ex: 1 << 3 gives us '1000'.
And it updates the current mask: 0000 ^ 1000 = 1000 or 1110 ^ 1000 = 0110
If you didn't get it, please, read bit operations in depth.
Question 3. What is the test mask
The test mask in above solution alters one of the bits of the current mask to check for Case 2.
Question 4. Why do we check for similar masks?
That means between the similar mask and the current mask, we have seen such digits that xored themselves along the way and disappeared from the mask. Only if the number of digits is even, they can disappear from the current mask: for ex: "223", the current mask evolution is: "100" -> "000" -> "1000".
Question 5. Why do we check for masks that differ by one bit?
Since it is allowed to have one number with odd count, we can check for masks that differ by one bit from the current mask.

I think, you got this, let me know if you have any other questions.


     */
}



