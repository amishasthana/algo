package Contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
676. Implement Magic Dictionary
Medium

Design a data structure that is initialized with a list of different words.
Provided a string, you should determine if you can change exactly one character in this string to match any word
in the data structure.

Implement the MagicDictionary class:

    MagicDictionary() Initializes the object.
    void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
    bool search(String searchWord) Returns true if you can change exactly one character in searchWord
     to match any string in the data structure, otherwise returns false.



Example 1:

Input
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
Output
[null, null, false, true, false, false]

Explanation
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // return False
magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
magicDictionary.search("hell"); // return False
magicDictionary.search("leetcoded"); // return False



Constraints:

    1 <= dictionary.length <= 100
    1 <= dictionary[i].length <= 100
    dictionary[i] consists of only lower-case English letters.
    All the strings in dictionary are distinct.
    1 <= searchWord.length <= 100
    searchWord consists of only lower-case English letters.
    buildDict will be called only once before search.
    At most 100 calls will be made to searc

    Length Based map

     For each length --> a map
     Map<Integer,Map<String,List<String>>
     For suffix/prefix --> a list of words --> Map<String,List<String>>
 */
public class MagicDictionary {
    /** Initialize your data structure here. */
    Map<Integer, Map<String, List<String>>> mapDS = new HashMap<>();
    // by length, then by prefix and suffix. Prefix/Suffix --> str.length/2. Length 1 if any string then all length 1 true.
    public MagicDictionary() {
               mapDS = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
       for(String s : dictionary) {
           int l = s.length();
           Map<String,List<String>> resList = mapDS.getOrDefault(l,new HashMap<>());

           if (l == 1) {
               List<String> listOfElem = new ArrayList<>();
               listOfElem.add(s);
               resList.put(s,listOfElem);
           } else {
               String prefix = s.substring(0,l/2);
               String suffix = s.substring(l/2,l);
               List<String> listOfElemPrefix = resList.getOrDefault(prefix,new ArrayList<>());
               listOfElemPrefix.add(s);
               resList.put(prefix,listOfElemPrefix);
               List<String> listOfElemSuffix = resList.getOrDefault(suffix,new ArrayList<>());
               listOfElemSuffix.add(s);
               resList.put(suffix,listOfElemSuffix);
           }
           mapDS.put(l,resList);
       }
    }

    public boolean search(String searchWord) {
           int l = searchWord.length();
            Map<String,List<String>> mapOfElem =  mapDS.get(l);
            if (mapOfElem == null) return false;
           if ( l == 1) {
               if (mapOfElem.size() > 1) return true;
               if (mapOfElem.get(searchWord) == null) return true;
               return false;
           }
        String prefix = searchWord.substring(0,l/2);
        String suffix = searchWord.substring(l/2,l);
        List<String> listOfElemPrefix = mapOfElem.getOrDefault(prefix,new ArrayList<>());
        listOfElemPrefix.addAll(mapOfElem.getOrDefault(suffix,new ArrayList<>()));
        return findSimilar(searchWord,listOfElemPrefix);
    }

    private boolean findSimilar(String s, List<String> listOfElem) {
        char cArray1[] = s.toCharArray();
        boolean globalOneDiff = false;
        for(String w : listOfElem) {
            boolean onlyOneDiff = false;
            char[] cArray2 = w.toCharArray();
            for(int i = 0; i < cArray1.length;i++) {
                if (cArray1[i] == cArray2[i]) continue;
                onlyOneDiff  = !onlyOneDiff;
            }
            if (onlyOneDiff) globalOneDiff = true;
            if (globalOneDiff) return globalOneDiff;
        }
        return globalOneDiff;
    }

    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        String[] sA = {"hello","hallo","leetcode"};
        md.buildDict(sA);
        System.out.println(md.search("hhllo"));
    }

}
