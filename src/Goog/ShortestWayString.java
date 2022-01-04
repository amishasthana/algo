package Goog;
/*
1055. Shortest Way to Form String
Medium

From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.



Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".

Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.

Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".



Constraints:

    Both the source and target strings consist of only lowercase English letters from "a"-"z".
    The lengths of source and target string are between 1 and 1000.

Accepted
41.5K
Submissions
72.6K
Seen this question in a real interview before?

This SOLUTION assume from TARGET you are taking a continous sequence.
 */
import java.util.*;
public class ShortestWayString {

    int[] min = null;
    char[] cArrayS = null;
    char[] cArrayT = null;
    Map<Character,List<Integer>> M = null;
    int ls = 0;
    int lt = 0;

    public int shortestWay(String source, String target) {
        M = new HashMap<>();
        ls = source.length();
        lt = target.length();
        cArrayS = source.toCharArray();
        cArrayT = target.toCharArray();
        min = new int[lt];


        for (int i = 0;i < ls;i++ ) {
            List<Integer> L = M.getOrDefault(cArrayS[i],new ArrayList<>() );
            L.add(i);
            M.put(cArrayS[i],L);
        }
        for(int i = lt-1; i >= 0;i--)
            findMin(i);
        return (min[0] == 0)?-1:min[0];

    }

    private void findMin(int p) {//p pos in target
        if ((p<0) || (min[0] == -1)) return;
        List<Integer> L = M.get(cArrayT[p]);
        if ( L == null) {
            min[0] = -1;
            return;
        }
        min[p] = lt;
        for(int i : L) {//i pos in source
            boolean matched = true;
            int pos = 0;
            while (matched && ((i+pos) < ls )
                    && ((p+pos) < lt )) {
                if (cArrayS[i+pos] == cArrayT[p+pos] ) {
                    int minFromRest = ((p+pos) == (lt-1))?0:min[p+pos+1];
                    min[p] = Math.min(min[p],1 + minFromRest);
                } else {
                    matched = false;
                }
            pos++;
        }// end of while
    }//end of for

}

public static void main(String[] args) {
    ShortestWayString sw = new ShortestWayString();
    System.out.println(sw.shortestWay("xyz","xyxyz"));
}

}
