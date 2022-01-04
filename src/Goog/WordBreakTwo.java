package Goog;

import java.util.*;

/*
140. Word Break II
Hard

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]


 */
public class WordBreakTwo {
    int[] DP = null;
    // s. Can it be broken into the corresponding elements. And second index number.
    Set<String> dict = new HashSet<>();
    Map<Integer,Set<String>> map = new HashMap<>();
    int nn = 0;

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> list = new LinkedList<>();
        if ( (s == null) || (wordDict == null) || (wordDict.size() == 0 ) ) return list;
        for(String ss : wordDict) {
            dict.add(ss);
        }
        nn = s.length();
        DP = new int[s.length()+1];
        Arrays.fill(DP,-1);
        DP[s.length()] = 1;
        for(int i = 0; i < s.length()+1;i++) {
            map.put(i, new HashSet<>());
        }

        if (solve(s,0) ) {
            list.addAll(map.get(0));
        }
        return list;
    }

    private Set<String>  populate(String s ,int index) {
        Set<String> newSet = new HashSet<>();
        if (index >= nn) {
            newSet.add(s);
        } else {
            for (String ss : map.get(index)) {
                newSet.add(s + " " + ss);
            }
        }
        return newSet;
    }

    private boolean solve(String str, int s) {
        int n = str.length();
        // if (( s >= n) || (s < 0) )  return true;
        // if ( DP[s]  == 1) return true;// -1, not cal, 0 false, 1 true
        for(int i = n-1; i >= 0; i--) {
            for(int j = i+1; j <= n;j++) {
                String sub = str.substring(i,j);
                if (dict.contains(sub) ) {
                    Set<String> nSet = populate(sub,j);
                    Set<String> oldSet = map.get(i);
                    oldSet.addAll(nSet);
                    if (DP[i] != 1) {
                        DP[i] = ( DP[j] == 1 )?1:0 ;
                    }
                }
                //if (DP[i] == 1 ) break; Dont break.
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
        WordBreakTwo wb = new WordBreakTwo();
        List<String> strL = wb.wordBreak("catsanddog",ls);
        strL.forEach(System.out::println);
        //System.out.println(str.to)
    }


}
