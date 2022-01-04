/*
Long Pressed Name
Easy

Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.



Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.

Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.

Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true

Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.



Note:

    name.length <= 1000
    typed.length <= 1000
    The characters of name and typed are lowercase letters.


    public boolean isLongPressedName(String name, String typed) {
        int i = 0, m = name.length(), n = typed.length();
        for (int j = 0; j < n; ++j)
            if (i < m && name.charAt(i) == typed.charAt(j))
                ++i;
            else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
                return false;
        return i == m;
    }



 */
public class LongPressedName {
    /* We  */
    public boolean isLongPressedName(String name, String typed) {
           int n = 0; int t = 0;
           while ( (n< name.length()) && (t < typed.length())) {
               char N  = name.charAt(n);
               char T = typed.charAt(t);
               if ( N != T) {
                   if (n == 0) return false;//First char
                   if (T != typed.charAt(t-1)) return false;
                   while ((t < typed.length()) && (typed.charAt(t) == typed.charAt(t-1)) ) t++;
                   continue;
               } else {//All matched.
                   n++;t++;
               }
           }
           if (n != name.length()) return false; //Not consumed all character of name.
           for(;t < typed.length();t++) {
               if (typed.charAt(t) != name.charAt(name.length()-1)) return false;
           }
           return true;
    }

    public static void main(String[] args) {
        LongPressedName lp = new LongPressedName();
        System.out.println(lp.isLongPressedName("pyplrz", "ppyypllr"));
        System.out.println(lp.isLongPressedName( "alex", "aaleex"));
        System.out.println(lp.isLongPressedName( "kikcxmvzi", "kiikcxxmmvvzz"));


    }
}
