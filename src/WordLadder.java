import java.util.*;

/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord,List<String> wordList) {
        if ((wordList == null) || (wordList.size() == 0)) return 0;
        Map<Integer,String> mDict = new HashMap<>();

        int index = 0;
        boolean endWordExist = false;
        boolean beginWordExist = false;
        int endWordIndex = -1;
        int beginWordIndex = -1;
        for(String w : wordList) {
            mDict.put(index++,w);
            if (w.equals(endWord)) {
                endWordExist = true;
                endWordIndex = index-1;
            }
            if (w.equals(beginWord)) {
                beginWordExist = true;
                beginWordIndex = index-1;
            }
        }
        if (!endWordExist ) return 0;
        if(!beginWordExist) {
            beginWordIndex = index;
            mDict.put(index++,beginWord);
        }

        int[][] gWord = new int[index][index];
        createGraph(mDict,gWord);

        int nHops = 1;
        boolean found = false;
        Queue<Integer> qWord = new ArrayDeque<>();
        qWord.add(beginWordIndex);
        Map<Integer,Integer> isVisitedMap = new HashMap<>();
        isVisitedMap.put(beginWordIndex,nHops);
        while (!qWord.isEmpty()) {
            Integer deqInteger = qWord.poll();
            int hopForParent = isVisitedMap.get(deqInteger);
            for(int i = 0; i < index;i++) {
                if ((isVisitedMap.get(i) == null) && (gWord[deqInteger][i] == 1)) {
                    if (i == endWordIndex) {
                        found = true;
                        nHops = hopForParent+1;
                        break;
                    }
                    isVisitedMap.put(i,hopForParent+1);
                    qWord.add(i);
                }
            }//End of child loop
            if (found) break;
        }// End of queue
        if (found) {
            return nHops;
        }
        return 0;
    }//End of method


    private void createGraph(Map<Integer,String> mDict,int[][] gWord) {
        for(int i = 0;i < gWord.length;i++) {
            String stratW = mDict.get(i);
            for(int j = i+1;j < gWord.length;j++) {
                String otherW = mDict.get(j);
                if (oneCharDiff(stratW,otherW)) {
                    gWord[i][j] = 1;
                    gWord[j][i] = 1;
                }
            }
        }//End of outer for loop.
    }

    private boolean oneCharDiff(String w1,String w2) {
        int diff = 0;
        for(int i = 0; i < w1.length();i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordLadder wl = new WordLadder();
      // String[] strA = {"hot","dot","dog","lot","log","cog"};
        String[] strA = {"a","b","c"};
        List<String> wordList = Arrays.asList(strA);
        System.out.print(wl.ladderLength("a","c",wordList));
        //System.out.print(wl.ladderLength("hit","cog",wordList));
    }
}
