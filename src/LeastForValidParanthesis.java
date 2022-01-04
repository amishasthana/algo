import java.util.Arrays;

public class LeastForValidParanthesis {
    public String minRemoveToMakeValid(String s) {
        if ((s == null) || (s.length() == 0)) return s;
        int lo = 0;// increment or decrement. Should not be negative.
        int[] vArray = new int[s.length()];
        Arrays.fill(vArray,-2);
        for(int i = 0; i < s.length();i++) {
            char c = s.charAt(i);
            if (isSmallChar(c)) {//leave it at -2
                continue;
            } else if (isLeftBracket(c)) {
                if (i == (s.length()-1)) {
                    vArray[i] = -1;//Special case.
                    continue;
                }
                lo++;
            } else {
                //Has to be right bracket.
                if (lo == 0) {
                    vArray[i] = -1;//need to discard.
                    continue;
                }
                lo--;
            }
            vArray[i] = lo;
        }// end of first loop

        //lo is zero then we are good.
        if (lo != 0) {
            for(int i = (s.length()-1);i >= 0;i--) {
                if ((vArray[i] != -1) && isLeftBracket(s.charAt(i))) {
                    lo--;
                    vArray[i] = -1;//ignoring them
                }
                if (lo == 0) break;
            }
        }

        StringBuilder strB = new StringBuilder();
        for(int i = 0; i < s.length();i++) {
            if( vArray[i] != -1) strB.append(s.charAt(i));

        }//End of second for
        return strB.toString();

    }//End of method

    private boolean  isLeftBracket(char c) {
        return ( '(' == c );
    }

    private boolean  isRightBracket(char c) {
        return ( ')' == c );
    }

    private boolean  isSmallChar(char c) {
        return (( ')' != c ) && ( '(' != c ));
    }

    public static void main(String[] args) {
        String s1 = "lee(t(c)o)de)";
        String s2 = "a)b(c)d";
        String s3 = "))((";
        LeastForValidParanthesis lp = new LeastForValidParanthesis();
        //System.out.println(lp.minRemoveToMakeValid(s1));
        //System.out.println(lp.minRemoveToMakeValid(s2));
        System.out.println(lp.minRemoveToMakeValid(s3));
    }

}
