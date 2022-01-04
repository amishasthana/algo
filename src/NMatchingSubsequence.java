import java.util.ArrayList;
import java.util.HashMap;

/*
792. Number of Matching Subsequences
Medium

Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input:
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".

Note:

    All words in words and S will only consists of lowercase letters.
    The length of S will be in the range of [1, 50000].
    The length of words will be in the range of [1, 5000].
    The length of words[i] will be in the range of [1, 50].

beautiful code
public int numMatchingSubseq(String S, String[] words) {
    List<Integer[]>[] waiting = new List[128];
    for (int c = 0; c <= 'z'; c++)
        waiting[c] = new ArrayList();
    for (int i = 0; i < words.length; i++)
        waiting[words[i].charAt(0)].add(new Integer[]{i, 1});
    for (char c : S.toCharArray()) {
        List<Integer[]> advance = new ArrayList(waiting[c]);
        waiting[c].clear();
        for (Integer[] a : advance)
            waiting[a[1] < words[a[0]].length() ? words[a[0]].charAt(a[1]++) : 0].add(a);
    }
    return waiting[0].size();
}
 */
import java.util.*;
public class NMatchingSubsequence {
    public int numMatchingSubseq(String S, String[] words) {
        List<Integer>[] cArray = new ArrayList[26];//An array of List of Integer.
        Map<Integer,String> iToS = new HashMap<>();//Its basically a way to identify each String with a number.
        int[] charIndex = new int[words.length];//For each word what is the current character being processed.
        for(int i = 0; i < 26; i++) {
            cArray[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < words.length; i++) {
            iToS.put(i,words[i]);
            char c = words[i].charAt(0);
            List<Integer> matchedL = cArray[c - 'a'];
            matchedL.add(i);
            charIndex[i] = 0;
        }





        int mFreq = 0;
        for(char c : S.toCharArray()) {
            List<Integer> matchedL = cArray[c - 'a'];
            //List<Integer> matchedL = new ArrayList<>(matchedOldL);//Need to do this so it does not add to existing list.
            int cSize = matchedL.size();
            for(int j = 0; j < cSize;j++) {
                int wIndex = matchedL.get(j);

                String w = iToS.get(wIndex);
                //if (charIndex[wIndex] == -1) continue;// This word is done.
                if ( charIndex[wIndex] == (w.length() -1) ) {
                    //System.out.println("*** Matched "+w);
                    charIndex[wIndex] = -1;
                    mFreq++;
                } else {
                    charIndex[wIndex]++;
                    char nChar = w.charAt(charIndex[wIndex]);
                    List<Integer> newMatchedL = cArray[nChar - 'a'];
                    newMatchedL.add(wIndex);
                }
            }//End of inner for current character and words waiting for it.
            for(int j = 0; j < cSize;j++) {//Allready processed.
                matchedL.remove(0);
            }

        }//End of outer for main word S

        /*
        for(Integer key : iToS.keySet()) {
            String w = iToS.get(key);
            System.out.println(" w "+w+" matched "+S.contains(w));

        }
        */
        return mFreq;
    }


    public static void main(String[] args) {
        NMatchingSubsequence nMA = new NMatchingSubsequence();
        String[] error1 =
                {"wpddkvbnn"};

        String[] error =
                {"wpddkvbnn","lnagtva","kvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvju",
                "rwpddkvbnnugln","gloeofnpjqlkdsqvruvabjrikfwronbrdyyj","vbgeinupkvgmgxeaaiuiyojmoqkahwvbpwugdainxciedbdkos",
                "mspuhbykmmumtveoighlcgpcapzczomshiblnvhjzqjlfkpina","rgmliajkiknongrofpugfgajedxicdhxinzjakwnifvxwlokip",
                "fhepktaipapyrbylskxddypwmuuxyoivcewzrdwwlrlhqwzikq","qatithxifaaiwyszlkgoljzkkweqkjjzvymedvclfxwcezqebx"};

        String[] strA = {"a", "bb", "acd", "ace"};
        System.out.println(nMA.numMatchingSubseq("rwpddkvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvjubjgloeofnpjqlkdsqvruvabjrikfwronbrdyyjnakstqjac",error));

    }


}

