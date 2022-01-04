package Goog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringTransformation1 {
    public boolean canConvert(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        Map<Character,Character> map = new HashMap<>();
        boolean allMatchedAllready = true;//everything matched with 26 char
        for ( int i = 0; i < str1.length() ; i++) {
            if (str1.charAt(i) != str2.charAt(i)) allMatchedAllready = false;
            if ( map.getOrDefault(str1.charAt(i),str2.charAt(i)) != str2.charAt(i)) return false;
            map.put(str1.charAt(i),str2.charAt(i));
        }
        Set<Character> cSetOfStr2 = new HashSet<>(map.values());// Str1 size >= str2.size.
        // Now if str2 size < 26 then we have a swap character which we can use.....
        return  (allMatchedAllready || (cSetOfStr2.size() < 26));
    }

    public static void main(String[] args) {
        StringTransformation1 st = new StringTransformation1();
        System.out.println(st.canConvert("abcd","aadc"));
    }

}
