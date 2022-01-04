import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
139. Word Break
Medium

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false


 */
public class WordBreak {

    Set<String> dictSet = new HashSet<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        dictSet.addAll(wordDict);
        boolean[] exist = new boolean[s.length()+1];// Substring need index+1.
        exist[0] = true;//So it can start.
        findifWordBreak(s,exist);
        return exist[s.length()];
    }

    private void findifWordBreak(String s,boolean[] exist) {
        for(int i = 1;i <= s.length();i++) {//i = 1. as 0,1, is just first char
            for(int j = 0;j < i;j++) {
                if (exist[j] && dictSet.contains(s.substring(j,i))) {
                    exist[i] = true;
                }
            }
        }
    }
    /*
    Similar.
    public boolean wordBreak(String s, Set<String> dict) {
    if (dict.contains(s)) return true;
    Queue<Integer> queue = new LinkedList<Integer>();
    queue.offer(0);
    // use a set to record checked index to avoid repeated work.
    // This is the key to reduce the running time to O(N^2).
    Set<Integer> visited = new HashSet<Integer>();
    visited.add(0);
    while (!queue.isEmpty()) {
        int curIdx = queue.poll();
        for (int i = curIdx+1; i <= s.length(); i++) {
            if (visited.contains(i)) continue;
            if (dict.contains(s.substring(curIdx, i))) {
                if (i == s.length()) return true;
                queue.offer(i);
                visited.add(i);
            }
        }
    }
    return false;
}
     */

    public static void main(String[] args) {
        String[] dict = {"leet","code","leeta"};
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak("leetaacode", Arrays.asList(dict)));
    }
}
