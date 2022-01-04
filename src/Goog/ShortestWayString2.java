package Goog;

import java.util.Arrays;

/* his one assume that its a subsequence.
That means the following:
   a) for a character star with the first occurence.
   b) Have boolean search. (Tree set will be fine but try one without one.)
 */
public class ShortestWayString2 {

    public int shortestWay(String source, String target) {
        char[] cS = source.toCharArray();
        char[] cT = target.toCharArray();
        int[][] cSourcePos = new int[source.length()][26];
        for(int i = source.length()-2;i >= 0; i--) {
            char c = cS[i];
            cSourcePos[i] = Arrays.copyOf(cSourcePos[i+1],26);
            cSourcePos[i][c -'a'] = i+1;
        }
        int t = 1;int ps = 0;
        for(int i = 0; i < target.length();) {
            if (ps == (cS.length-1)) {
                t++;
                ps = 0;
            }
            //while ( cS[ps] == cT[i]) {
            if ( (cS[ps] != cT[i]) || (cSourcePos[ps][cT[i] - 'a'] == 0)) {
                return -1;
            }
        }
        return t;
    }

    /*
    public int shortestWay(String source, String target) {
        char[] cSource = source.toCharArray();
        Map<Character,List<Integer>> M = new HashMap<>();
        for(int i = 0; i < cSource.length;i++) {
            List<Integer> L = M.getOrDefault(cSource[i],new LinkedList<>());
            L.add(i);
            M.put(cSource[i],L);
        }
        int n = 0;
        for(int i = 0; i < target.length();i++) {
            List<Integer> fL = M.get(target.charAt(i));
            if (fL == null) return -1;
            n++;
            int pos = fL.get(0);
            while (i < (target.length()-1)) {
                pos = search(M.get(target.charAt(i+1)),pos);
               if (pos == -1) break;
               i++;//matched so far.
            }
        }
        return (n == 0)?-1:n;
    }

     */

    //Search higher then the given index, ceiling.
    /*
        s = n h = n  --> n-2
        m = n.
        s = n h = n+1. n
        m = n, s = n+1  return n but we want n+1
        n , n+2, n
        m = n+1,
        h = n+1
     */
    /*
    private int search(List<Integer> L, int I) {
        if (L == null) return -1;
        int low = L.get(0);
        int h = L.get(L.size()-1);

        int mid = -1; int pos = -1;
        while (low <= h) {
            mid = low + (h - low)/2;
            if (mid <= I) {
                low = mid+1;
            } else {
                h = mid-1;
                pos = mid;
            }
        }
        return pos;
    }
    */

    public static void main(String[] args) {
        ShortestWayString2 sw = new ShortestWayString2();
        System.out.println(sw.shortestWay("aaaaa","aaaaaaaaaaaaa"));
    }
}
