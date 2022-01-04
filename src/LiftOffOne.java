import java.util.Arrays;

public class LiftOffOne {

    //public LiftOffOne()
    /*

    Leading zeros may be elided from each group (but each group must retain at least one digit).

1203:405:0:0:a0b:0:0:fffe

One or more consecutive groups of all zeros may be abbreviated as ::, but this may only be done once.

1203:405::a0b:0:0:fffe
1203:405:0:0:a0b::fffe
     */

    public int[] praseIPVSix(String add) {
        int[] retArr = new int[16];
        if (!valid(add)) return null;
        String[] seg = add.split(":");
        int index = 0;
        for(String s : seg) {
            if (s.length() < 4) { s = leftPad(s); }
            String fPart = s.substring(0,2);
            String sPart = s.substring(2,s.length());
            retArr[index++] = convertToDecimal(fPart);
            retArr[index++] = convertToDecimal(sPart);
        }
        return retArr;
    }

    private String leftPad(String s) {
        StringBuilder strB = new StringBuilder();
        for(int i = 4; i > s.length();i--) {
            strB.append('0');
        }
        return strB.append(s).toString();
    }

    // Assume 2 character valid hex
    private int convertToDecimal(String s) {
       int val = 0;
       char fC = s.charAt(1);
       char sC = s.charAt(0);
       // a --> pos 1    (7 * 16 ^1)
       val +=  hexValue(fC);
       val +=   hexValue(sC)*16;
       return val;
    }

    private int hexValue(char c) {
        int val = 0;
        if ( isChar(c) ) {
            val  = ((c - 'a')+10);
        } else {
            val  = (c - '0');
        }
        return val;
    }

    private boolean isChar(char c) {
        if ( ( (c >= (int)'a') && (c <= (int)'z')) ) return true;
        return false;
    }

    private boolean isDigit(char c) {
        if ( ( c >= (int)'0') && (c <= (int)'9') ) return true;
        return false;
    }

    /*
        These char val
         0-9,a-f
         : and .
        Format validation :
          Tokeninze ":" --> String [] -> 8. Each should be 4 char long.

     */
    private boolean valid(String add) {
        if (add == null) return false;
        String[] seg = add.split(":");
        if ((seg == null) || (seg.length != 8)) return false;
        for(String s : seg) {
            if ( (s.length() > 4) || (s.length() <= 0  )) return false;
            if (!validateHex(s))  return false;
        }
        return true;
    }

    private boolean validateHex(String s) {
        String ss = s.toLowerCase();
        for(char c : ss.toCharArray()) {
            if ( isChar(c)
                || isDigit(c)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /*
        1203:0405:0000:0000:0a0b:0000:0000:fffe
[18 3 4 5 0 0 0 0 10 11 0 0 0 0 255 254]
         */
        LiftOffOne lo  = new LiftOffOne();
        //String IP = "1203:0405:0000:0000:0a0b:0000:0000:fffe";
        String IP2 = "1203:405:0:0:a0b:0:0:fffe";
        int[] array =  lo.praseIPVSix(IP2);
        System.out.println(Arrays.toString(array));

    }

}

