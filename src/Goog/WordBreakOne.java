package Goog;

import java.util.*;

public class WordBreakOne {

    int[] DP = null;// s. Can it be broken into the corresponding elements.
    Set<String> dict = new HashSet<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        if ( (s == null) || (wordDict == null) || (wordDict.size() == 0 ) ) return false;
        for(String ss : wordDict) {
            dict.add(ss);
        }
        DP = new int[s.length()+1];
        Arrays.fill(DP,-1);
        DP[s.length()] = 1;
        return solve(s,0);
    }

    private boolean solve(String str, int s) {
        int n = str.length();
       // if (( s >= n) || (s < 0) )  return true;
       // if ( DP[s]  == 1) return true;// -1, not cal, 0 false, 1 true
        for(int i = n-1; i >= 0; i--) {
            for(int j = i+1; j <= n;j++) {
                 String saa = str.substring(i,j);
                DP[i] = ( dict.contains(str.substring(i,j)) && (DP[j] == 1) )?1:0 ;
                if (DP[i] == 1 ) break;
            }
        }
        return (DP[0] == 1);
    }

    public static void main(String[] args) {
        String[] sA = {"cats", "dog", "sand", "and", "cat"};
        List<String> ls = new ArrayList<>();
        for(String a : sA) {
            ls.add(a);
        }
        WordBreakOne wb = new WordBreakOne();
        System.out.println(wb.wordBreak("catsandog",ls));
    }


}
