package backtrack;

import java.util.ArrayList;
import java.util.List;

/*
Palindrome Partitioning
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
//include both i and j
dp[i,j] =

 */
public class PalindromePartition {
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
        PalindromePartition pp = new PalindromePartition();
        List<List<String>> l1 = pp.partition("aab");
        System.out.println("List length "+l1.size());
    }

}
