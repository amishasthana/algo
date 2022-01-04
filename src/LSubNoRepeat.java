import java.util.HashMap;
import java.util.Map;

public class LSubNoRepeat {
    public int lengthOfLongestSubstring(String s) {
        if ( (s == null) || (s.length() == 0)  ) return 0;
        Map<Character,Integer> mChar = new HashMap<>();
        int largeL = 0;
        int cL = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int pIndex = mChar.getOrDefault(c,-1);
            //   i = 5 , cL = 2-> 3,4 index.  i = 2 cL = 2. 0/1.
            if  ( ( pIndex  != -1) && ( (i - cL) <=  pIndex ) ) {
                largeL = Math.max(cL,largeL);
                cL = i - (pIndex+1) +1;
            } else {
                cL++;
            }
            mChar.put(c,i);
        }// End of for.
        largeL = Math.max(cL,largeL);

        return largeL;
    }
    public static void main(String[] args) {
        String s = " ";
        LSubNoRepeat ls = new LSubNoRepeat();
        System.out.print(ls.lengthOfLongestSubstring(s));
    }
}
