public class ZigZagEffecientMemory {
    public String convert(String s, int numRows) {
        if ((s == null) || (s.length() <= numRows)) return s;
        StringBuilder[] strBArray = new StringBuilder[numRows];

        for(int i = 0 ; i < numRows; i++) {
            strBArray[i] = new StringBuilder();
        }
        int i = 0;
        boolean isStraight = true;
        while(i < s.length()) {
            if (isStraight) {
                for(int jj = 0;jj <  strBArray.length;jj++) {
                    if(i < s.length()) {
                        strBArray[jj].append(s.charAt(i++));
                    } else {
                        break;
                    }
                }
                isStraight = false;
            } else {
                for(int jj = strBArray.length-2;jj >= 1;jj--) {
                    if(i < s.length()) {
                        strBArray[jj].append(s.charAt(i++));
                    } else {
                        break;
                    }
                }
                isStraight = true;
            }
        }
        for(int j = 1 ; j < numRows; j++) {
            strBArray[0].append(strBArray[j].toString());
        }
        return    strBArray[0].toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        ZigZagEffecientMemory zz = new ZigZagEffecientMemory();
        System.out.println(zz.convert(s,4));
    }

}
