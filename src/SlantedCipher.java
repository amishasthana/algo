/*
A string is being encoded using a slanted transposition cipher that uses an integer numberOfRows.
 For example, assume the input string is 'my name is' and the number of rows is 3.The input string is encoded in a slanted fashion in 3 rows as given below.
 Gaps in the string are taken as blank characters.The output is then read row-wise, with the gaps being read as underscores.Hence, for the example given in the image
image

the given encoded input string
'mnes__ya_____mi' reads as 'my name is' after decoding, which is the required output. Given the number of rows used and the encoded string, find the required output string.
 */
public class SlantedCipher {
    /* String is not null. n = number of rows. */
    public String encode(String s,int n) {
        char[] cArray = s.toCharArray();
        int maxColumns = cArray.length/n + n;
        //Maybe need to add space...
        char[][] cipherArray = new char[n][maxColumns];
        initialize(cipherArray);
        /* This is the string index */
        int col = 0; int colIncrement = 0;int row = 0;
        for(int i = 0; i < cArray.length;i++) {
            row = i%n;
            cipherArray[row][col] = cArray[i];
            col++;
            if ((col-colIncrement)%n == 0) {
                col = ++colIncrement;//resetting.
            }
        }
        StringBuilder actString = new StringBuilder();
        for(int i = 0; i < n;i++) {
            actString.append(getEncodedString(cipherArray[i]));
        }
        return actString.toString();
    }

    private void initialize(char[][] cipherArray) {
        for(int i = 0; i < cipherArray.length; i++) {
            for(int j = 0; j < cipherArray[0].length; j++) {
                cipherArray[i][j] = ' ';
            }
        }
    }


    public String decode(String s,int n) {
        char[] outArray = new char[s.length()];
        for(int i = 0; i < outArray.length; i++) {
            outArray[i] = ' ';
        }
        int buffer = 0; // Will occur when break.
        int multiplier = 0;
        char[] cArr = s.toCharArray();boolean isBreak = false;
        for(int i = 0; i < cArr.length;i++) {
            char c = cArr[i];
            if(!isBreak && (c == ' ')) {// Its a break. Ignore that many character.
                isBreak = true;
                buffer++;
                i += buffer;//That many empty char
                c = cArr[i];
                multiplier = 0;
            } else {
                isBreak = false;
            }
            outArray[buffer+n*multiplier] = c;
            multiplier++;
        }
        return new String(outArray);
    }


    private String getEncodedString(char[] cArray) {
        int j = cArray.length-1;
        for(;j >= 0;j--) {
            if (cArray[j] == ' ') continue;
            else break;
        }
        StringBuilder strB = new StringBuilder();
        for(int i = 0;i <= j;i++) {
            strB.append(cArray[i]);
        }
        return strB.toString();
    }

    public static void main(String[] args) {
        SlantedCipher sc = new SlantedCipher();
        String encodedStr = sc.encode("my name is",3);
        System.out.println(encodedStr);
        System.out.println(sc.decode(encodedStr,3));
    }

}
