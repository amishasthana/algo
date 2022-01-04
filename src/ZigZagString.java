import java.util.ArrayList;
import java.util.List;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I


 */
public class ZigZagString {

    public String convert(String s, int numRows) {
        if ((s == null) || (s.length() <= numRows)) return s;
        //StringBuilder[] strBArray = new StringBuilder[numRows];
        List<List<Character>>  strBArray = new ArrayList<>();


        for(int i = 0 ; i < numRows; i++) {
            strBArray .add(new ArrayList<>());
        }

        int i = 0;
        boolean isStraight = true;
        while(i < s.length()) {
            if (isStraight) {
                populateStraight(s,i,strBArray);
                i = i+numRows;
                isStraight = false;
            } else {
                populateDiagnal(s,i,strBArray);
                i = i+numRows-2;
                isStraight = true;
            }
        }
        StringBuilder strB = new StringBuilder();
        for(int j = 0 ; j < numRows; j++) {
            List<Character> cList = strBArray.get(j);
            for(int jj = 0; jj < cList.size();jj++) {
                strB.append(cList.get(jj));
            }
        }
        return    strB.toString();
    }


    private void  populateStraight(String s,int start,List<List<Character>> strBArray) {
        for(int i = 0;i <  strBArray.size();i++) {
            if(start < s.length()) {
                strBArray.get(i).add(s.charAt(start++));
            } else {
                break;
            }
        }
    }

    private void  populateDiagnal(String s,int start,List<List<Character>> strBArray) {
        for(int i = strBArray.size()-2;i >= 1;i--) {
            if(start < s.length()) {
                strBArray.get(i).add(s.charAt(start++));
            } else {
                break;
            }
        }
    }


    public String convertFirst(String s, int numRows) {
        if ((s == null) || (s.length() <= numRows)) return s;
        StringBuilder[] strBArray = new StringBuilder[numRows];

        for(int i = 0 ; i < numRows; i++) {
            strBArray[i] = new StringBuilder();
        }
        int i = 0;
        boolean isStraight = true;
        while(i < s.length()) {
            if (isStraight) {
                populateStraight(s,i,strBArray);
                i = i+numRows;
                isStraight = false;
            } else {
                populateDiagnal(s,i,strBArray);
                i = i+numRows-2;
                isStraight = true;
            }
        }
        StringBuilder retStrb = new StringBuilder();
        for(int j = 0 ; j < numRows; j++) {
            //System.out.println(strBArray[j].toString());
            retStrb.append(strBArray[j].toString());
        }
        return    retStrb.toString();
    }

    private void  populateStraight(String s,int start,StringBuilder[] strBArray) {
        for(int i = 0;i <  strBArray.length;i++) {
            if(start < s.length()) {
                strBArray[i].append(s.charAt(start++));
            } else {
                break;
            }
        }
    }

    private void  populateDiagnal(String s,int start,StringBuilder[] strBArray) {
        for(int i = strBArray.length-2;i >= 1;i--) {
            if(start < s.length()) {
                strBArray[i].append(s.charAt(start++));
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        ZigZagString zz = new ZigZagString();
        System.out.println(zz.convert(s,4));
    }
}
