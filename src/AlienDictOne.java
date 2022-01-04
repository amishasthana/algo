import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Verifying an Alien Dictionary
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.



Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

 */
public class AlienDictOne {
    public boolean isAlienSorted(String[] words, String order) {
         //char[] oArray = order.toCharArray();
        Map<Character,Integer>  orderMap = new HashMap<>();
        int index = 0;
        for(char c : order.toCharArray()) {
            orderMap.put(c,index++);
        }

        String fWord = words[0];
        for(int i = 1;i < words.length;i++) {
            String sWord = words[i];
            if (customCompare(fWord,sWord,orderMap) > 0) {
                return false;
            }
            fWord = sWord;
        }
        return true;

    }

    private int customCompare(String s1,String s2,Map<Character,Integer> oMap) {
        int l = Math.min(s1.length(),s2.length());
        for(int i =0; i < l;i++) {
            int diff = oMap.get(s1.charAt(i))-oMap.get(s2.charAt(i));
            if (diff == 0) continue;
            return diff;
        }
        return s1.length()-s2.length();

    }

    public static void main(String[] args) {
        String[] aStrA = {"abc","abcd","ab"};
        Arrays.sort(aStrA);

        System.out.println(Arrays.toString(aStrA));
    }


}
