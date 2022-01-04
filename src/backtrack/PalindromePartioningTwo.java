package backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
132. Palindrome Partitioning II
Hard

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.


public int minCut(String s) {
        // validate input
        if (s == null || s.length() <= 1) {
            return 0;
        }
        // dp
        int N = s.length();
        int[] dp = IntStream.range(0, N).toArray(); // initial value: dp[i] = i

        for (int mid = 1; mid <  N; mid++) { // iterate through all chars as mid point of palindrome
            // CASE 1. odd len: center is at index mid, expand on both sides
            for (int start = mid, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
            // CASE 2: even len: center is between [mid-1,mid], expand on both sides
            for (int start = mid - 1, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
        }
        return dp[N - 1];
    }


 */
public class PalindromePartioningTwo {
    public int minCutT(String s) {
        // validate input
        if (s == null || s.length() <= 1) {
            return 0;
        }
        // dp
        int N = s.length();
        int[] dp = IntStream.range(0, N).toArray(); // initial value: dp[i] = i

        for (int mid = 1; mid <  N; mid++) { // iterate through all chars as mid point of palindrome
            // CASE 1. odd len: center is at index mid, expand on both sides
            for (int start = mid, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
            // CASE 2: even len: center is between [mid-1,mid], expand on both sides
            for (int start = mid - 1, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
        }
        return dp[N - 1];
    }


    public int minCut(String s) {
        List<List<String>> L = new ArrayList<>();
        int[] dp = new int[s.length()];// partitoning till nth element.
        for(int i = 0; i < dp.length;i++) {
            dp[i] = i;//oth element no partitoning. 1 element 1 partition to divide into 2 etc....
        }
        //odd palindrome.
        /*
          2--> mid
          r= 1 , 1 --3
          0 + mid+r

         */
        for(int mid  = 1; mid < s.length();mid++) {
            for(int start = mid,end = mid;( (start >= 0) && (end < s.length()) && (s.charAt(start) == s.charAt(end))) ;start --,end ++) {
                int cut = (start <= 0)?0:dp[start-1]+1;
                dp[end] = Math.min(cut,dp[end]);
            }

            for(int start = mid-1,end = mid;( (start >= 0) && (end < s.length()) && (s.charAt(start) == s.charAt(end))) ;start --,end ++) {
                int cut = (start <= 0)?0:dp[start-1]+1;
                dp[end] = Math.min(cut,dp[end]);
            }
        }
        return dp[s.length()-1];
    }


    public int minCut1(String s) {
        List<List<String>> L = partition(s);
        int min = Integer.MAX_VALUE;
        for(List<String> cL : L) {
            min = Math.min(min,cL.size());
        }
        return min-1;
    }
    public List<List<String>> partition(String s) {
        List<List<String>> L = new ArrayList<>();
        backTrack(s,L,new ArrayList<>(),0);
        return L;
    }

    private void backTrack(String s,List<List<String>> L, List<String> T,int start) {
        if (start == s.length() ) {
            L.add(new ArrayList<>(T));
            return;
        }
        for(int i = start; i < s.length();i++) {
            if (isPalindrome(s,start,i) ) {
                T.add(s.substring(start,i+1));
                backTrack(s,L,T,i+1);
                T.remove(T.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s, int l, int h) {
        while (l < h) {
            if (s.charAt(l++) != s.charAt(h--) ) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartioningTwo pp = new PalindromePartioningTwo();
        int n  = pp.minCut("aab");
        System.out.println("List length "+n);
    }

}
