/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"

  If its n^2 then why so much hoopla.
   For each i find the max one be done with it.


 */
public class PalinDrome {
    int maxLength;//Max length of palindrome.
    int start;//From where does palindrome start. inclusive

    public String longestPalindrome(String s) {
        if ((s == null) || (s.length() <2)) {
            return null;
        }
        char[] cArray = s.toCharArray();
        for( int i = 0; i < cArray.length;i++) {
            findLargest(cArray,i,i);
            findLargest(cArray,i,i+1);
        }
        return s.substring(start,(start+maxLength));
    }

    private void findLargest(char[] cArray,int start,int end) {//Start and end inclusive.
        while ((start >= 0) && (end < cArray.length) && (cArray[start] == cArray[end])) {
            start--;
            end++;
        }
        if (maxLength < (end-start-1)) {//End and start are -1 and +1 from actual position. So....
            maxLength = end-start-1;
            this.start = start+1;
        }
    }



    public static void main(String[] args) {
        PalinDrome pd = new PalinDrome();
        System.out.print(pd.longestPalindrome("cbeffebc"));
    }


}
