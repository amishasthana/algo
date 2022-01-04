import java.util.*;

/*
1178. Number of Valid Words for Each Puzzle
Hard
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:

    word contains the first letter of puzzle.
    For each letter in word, that letter is in puzzle.
    For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).

Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].



Example :

Input:
words = ["aaaa","asas","able","ability","actt","actor","access"],
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
Output: [1,1,3,2,4,0]
Explanation:
1 valid word for "aboveyz" : "aaaa"
1 valid word for "abrodyz" : "aaaa"
3 valid words for "abslute" : "aaaa", "asas", "able"
2 valid words for "absoryz" : "aaaa", "asas"
4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.



Constraints:

    1 <= words.length <= 10^5
    4 <= words[i].length <= 50
    1 <= puzzles.length <= 10^4
    puzzles[i].length == 7
    words[i][j], puzzles[i][j] are English lowercase letters.
    Each puzzles[i] doesn't contain repeated characters.


 */
public class WordPuzzle {
    public static final int PUZZLE_LEN = 7;
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> retList = new ArrayList<>();
        int[] bWord = new int[words.length];

        Map<Integer,Integer> lValidStr = new HashMap<>();
        for(String s : words) {
            BitSet bSet = new BitSet(26);
            int len = populateAndValidate(s,bSet);
            if (len <= PUZZLE_LEN) {
                int v = getIntegerFromBitSet(bSet);
                int freq = lValidStr.getOrDefault(v,0);
                freq++;
                lValidStr.put(v,freq);
            }
        }

        for(int i = 0; i < puzzles.length;i++) {
            int bPuzzle = getIntegerFromBitSet(initBitSet(puzzles[i]));
            String cPuz = puzzles[i];
            int nOfWords = 0;
            int fChar = getIntegerFromBitSet(initBitSet(cPuz.charAt(0)+""));
            for(int wInt : lValidStr.keySet()) {

                if (!( (fChar & wInt) == fChar)) {
                    continue;
                }

                if ( (wInt & bPuzzle) == wInt) {
                    nOfWords += lValidStr.get(wInt);
                }

            }//End of word loop for a puzzle
            retList.add(nOfWords);
        }//End of puzzle loop

        return retList;
    }

    private int populateAndValidate(String s,BitSet bSet) {
         int len = 0;
        for(char c : s.toCharArray()) {
            if (!bSet.get(c-'a')) {
                len++;
                bSet.set(c-'a');
            }
            if (len > PUZZLE_LEN) {
                return PUZZLE_LEN;
            }
        }
        return len;
    }

    private int getIntegerFromBitSet(BitSet bs) {
        int value=0;
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i+1)) {
            value += (1 << i);
        }
        return value;
    }

    private BitSet initBitSet(String s) {
        BitSet bSet = new BitSet(26);
        for(char c : s.toCharArray()) {
            bSet.set(c-'a');
        }
        return bSet;
    }
}
