/*
Number can not have leading zero.
So 0.07 is good. 07 is not.
No comma. Only decimal.
Negative number possible.

 */
public class StringToNumber {

    public boolean isNumber(String s) {
        if ((s == null) || (s.length() == 0)) return false;
        boolean nonZeroNumberEncountered = false;
        boolean inDecimalPart = false;
        for(int i = 0; i < s.length();i++) {
            char cChar = s.charAt(i);
            if (isValidDigit(cChar)) {
                if (inDecimalPart) continue; //The only check in decimal is that it need to be digit.

                if (cChar == '0') {
                    if (nonZeroNumberEncountered) continue;
                    /*
                   cases are -0.01 true,  -03 false
                   03 false , 0.01 true, 0 true
                 */
                    if (i >= (s.length()-1)) {//last digit anyway
                        return true;
                    } else {
                        if (s.charAt(i+1) != '.') {
                            return false;
                        }
                    }
                } else {
                    nonZeroNumberEncountered = true;
                }
            } else if ((cChar == '-') ) {
                if (i == 0) continue;
                return false;//- can not be anyplace but first.
            } else if(cChar == '.') {//Decimal
                if (inDecimalPart) {//Alllready decimal has been encountered.
                    return false;
                }
                inDecimalPart = true;
                continue;
            } else {
                return false;//Some other char.
            }
        }
        return true;

    }

    private boolean isValidDigit(char c) {
        return ((c >= '0') && (c <= '9'));
    }

    public static void main(String[] args) {
        StringToNumber strN = new StringToNumber();
        System.out.println(strN.isNumber("0.01"));
        System.out.println(strN.isNumber("-03"));
        System.out.println(strN.isNumber("-0"));
        System.out.println(strN.isNumber("-1.45.6"));
        System.out.println(strN.isNumber("03"));
        System.out.println(strN.isNumber("003"));
        System.out.println(strN.isNumber("2457"));
    }

}
