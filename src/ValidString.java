/*
Given a String, validate if it is a valid identifier. If any characters in a string are appearing more than 3 times consecutively,
 the string is invalid else valid. Follow up to design a algorithm to find such valid identifiers and design a system.
 */
public class ValidString {
    public boolean validString(String s) {
        if ((s == null) || (s.length() <= 2)) return true;
        int cRepeat = 1;
        char[] cArray = s.toCharArray();
        char cChar = cArray[0];

        for(int i = 1;i < cArray.length;i++) {
             char c = cArray[i];
             if (c == cChar) {
                 cRepeat++;
                 if (cRepeat == 3) return false;
             } else {
                 cRepeat = 1;
                 cChar = c;
             }
        }

        return true;// Just fyi
    }

    public static void main(String[] args) {
        ValidString vs = new ValidString();
        System.out.println(vs.validString("aabaccdcc"));
    }

}
