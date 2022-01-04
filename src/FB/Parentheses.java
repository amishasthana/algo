package FB;

import java.util.ArrayList;
import java.util.List;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]

Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Example 3:

Input: ")("
Output: [""]

 */
public class Parentheses {

    char L = '(';
    char R = ')';
    public List<String> removeInvalidParentheses(String s) {
        int coun = 0;
        List<String> lis = new ArrayList<>();
        boolean rBal= true;
        for(char c : s.toCharArray()) {
            if (c == L) {
                coun++;
            } else if(c == R) {
                coun--;
            }
            if (coun < 0)  rBal = false;
        }
        if (rBal && (coun == 0)) {
            lis.add(s);
            return lis;
        } else  {
            char[] cArr = new char[2];
            // ())()
            if (!rBal) {
                cArr[0] = L;cArr[1] = R;
                findUnbalanced(s,lis,cArr,0);
            } else {
                // (()() --> )()(( --> ))((  --> (())
                         //           --> )()( --> ()()
                         // --> )()(  --> repeat
                StringBuilder strB = new StringBuilder(s);
                cArr[0] = R;cArr[1] = L;
                findUnbalanced(strB.reverse().toString(),lis,cArr,0);
            }
        }
        return lis;
    }

    private void findUnbalanced(String s,List<String> lis,char[] cArr,int loc) {
        int coun = 0;
        int i = 0;
        for(char c : s.toCharArray()) {
            if (c == cArr[0]) {
                coun++;
            } else if(c == cArr[1]) {
                coun--;
            }
            if (coun < 0) {
                
            }
        }
    }
}

