package Goog;

import java.util.*;

public class WordBreadTwoAnother {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> list = new LinkedList<>();
        if ( (s == null) || (wordDict == null) || (wordDict.size() == 0 ) ) return list;
        Set<String> dict = new HashSet<>();
        dict.addAll(wordDict);
        Map<String,List<String>> map = new HashMap<>();
        return solve(s, dict, map);

    }

    private List<String>   solve(String s, Set<String> dict, Map<String,List<String>> map ) {
        List<String> list = new LinkedList<>();
        if (s.isEmpty() ) {
            list.add("");
            return list;
        }

        if (map.get(s) != null) return map.get(s);
        for(String sDict : dict) {
            if ( s.startsWith(sDict)) {
                List<String> childList = solve(s.substring(sDict.length()),dict,map);
                for( String child : childList) {
                    list.add(sDict+ (child.equals("")?"":" "+child) );
                }
            }
        }
        map.put(s,list);
        return list;

    }

    public static void main(String[] args) {
        String[] sA = {"cats", "dog", "sand", "and", "cat"};
        List<String> ls = new ArrayList<>();
        for(String a : sA) {
            ls.add(a);
        }
        WordBreadTwoAnother wb = new WordBreadTwoAnother();
        List<String> strL = wb.wordBreak("catsanddog",ls);
        strL.forEach(System.out::println);
        //System.out.println(str.to)
    }
}
