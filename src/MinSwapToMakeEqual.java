/*
1247. Minimum Swaps to Make Strings Equal
Medium

You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].

Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.



Example 1:

Input: s1 = "xx", s2 = "yy"
Output: 1
Explanation:
Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".

Example 2:

Input: s1 = "xy", s2 = "yx"
Output: 2
Explanation:
Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.

Example 3:

Input: s1 = "xx", s2 = "xy"
Output: -1

Example 4:

Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
Output: 4



Constraints:

    1 <= s1.length, s2.length <= 1000
    s1, s2 only contain 'x' or 'y'.


 */
public class MinSwapToMakeEqual {
    public static final char X = 'x';
    public static final char Y = 'y';
    int x1 = 0; int x2 = 0;
    int y1 = 0; int y2 = 0;
    int swap1 = 0;
    int swap2 = 0;

    public int minimumSwap(String s1, String s2) {
        if ((s1 == null) || (s2 == null) ||(s1.length() == 0) || (s2.length() == 0)) {
            return 0;
        }

        if(solExist(s1,s2)) {
            if (swap1%2 == 0) {
                return (swap1/2 + swap2/2);
            } else {
                return (swap1/2 + swap2/2)+2;
            }
        }
        return -1;
    }

    /* Assume not null and have some size. Also of equal size. Also only x and y */
    private boolean solExist(String s1,String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for(int i = 0; i < c1.length;i++) {
            if (c1[i] != c2[i]) {
                if (c1[i] == X) {
                    swap1++;
                } else {
                    swap2++;
                }
            }
            if (c1[i] == X) {
                x1++;
            } else {
                y1++;
            }

            if (c2[i] == X) {
                x2++;
            } else {
                y2++;
            }
        }

        if(((x1+x2)%2 != 0) || ((y1+y2)%2 != 0)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MinSwapToMakeEqual ms = new MinSwapToMakeEqual();
        //String s1 = "xy";String s2 = "yx";
        String s1 = "xxyyxyxyxx";String s2 = "xyyxyxxxyx";
        System.out.print(ms.minimumSwap(s1,s2));
    }
}
