package FB;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordDictionary {
    /** Initialize your data structure here. */
    char dot = '.';
    Map<Integer, Set<String>> mWord = new HashMap<>();
    public WordDictionary() {

    }

    public void addWord(String word) {
          Set<String> wL = mWord.getOrDefault(word.length(),new HashSet<>());
          wL.add(word);
          mWord.put(word.length(),wL);
    }

    public boolean search(String word) {
        Set<String> wL = mWord.get(word.length());
        if (wL == null) return false;
        for(String s : wL) {
            if (compare(s,word)) return true;
        }
        return false;
    }

    public boolean compare(String s1,String s2) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        for(int i = 0; i < c1.length;i++) {
            if ((c1[i] == dot) || (c2[i] == dot)) continue;
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }
}
