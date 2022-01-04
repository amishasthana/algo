package Goog;

import java.util.*;

/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"

Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]


 */
public class GroupStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<Integer,List<String>> map = new HashMap<>();
        for(String s : strings) {
            int n = s.length();
            List<String> setS = map.get(n);
            if (setS == null) setS = new LinkedList<>();
            setS.add(s);
            map.put(n,setS);
        }
        List<List<String>> res = new LinkedList<>();

        for(Map.Entry<Integer,List<String>> e : map.entrySet() ) {
            int len = e.getKey();
            List<String> set  = e.getValue();
            List<String> list = new LinkedList<>();
            res.add(list);
            String pStr = null;
            int delta1 =  0;
            int delta2 =  0;
            int preDelta = 0;
            for(String cStr : set) {
                if ( pStr != null ) {
                    delta1 = pStr.charAt(0) - cStr.charAt(0);
                    delta2 = pStr.charAt(0)+26 - cStr.charAt(0);
                    boolean allMatched = true;
                    for(int i = 1; i < cStr.length(); i++) {
                        if ( (Math.max(cStr.charAt(0),pStr.charAt(0)) - Math.min(cStr.charAt(0),pStr.charAt(0)) != delta1)
                                && (Math.min(cStr.charAt(0),pStr.charAt(0))+26 - Math.max(cStr.charAt(0),pStr.charAt(0)) != delta2) )
                        {allMatched = false; break;}
                    }

                    if (!allMatched) {
                        list = new LinkedList<>();
                        res.add(list);
                    }
                }
                pStr = cStr;
                list.add(cStr);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GroupStrings gs = new GroupStrings();
        String[] sa = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        List<List<String>> ls = gs.groupStrings(sa);
        System.out.println(ls);
    }

}
