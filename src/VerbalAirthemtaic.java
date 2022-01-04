import java.util.*;

/*
1307. Verbal Arithmetic Puzzle

    User Accepted: 179
    User Tried: 677
    Total Accepted: 321
    Total Submissions: 1914
    Difficulty: Hard

Given an equation, represented by words on left side and the result on right side.

You need to check if the equation is solvable under the following rules:

    Each character is decoded as one digit (0 - 9).
    Every pair of different characters they must map to different digits.
    Each words[i] and result are decoded as one number without leading zeros.
    Sum of numbers on left side (words) will equal to the number on right side (result).

Return True if the equation is solvable otherwise return False.



Example 1:

Input: words = ["SEND","MORE"], result = "MONEY"
Output: true
Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652

Example 2:

Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214

Example 3:

Input: words = ["THIS","IS","TOO"], result = "FUNNY"
Output: true

Example 4:

Input: words = ["LEET","CODE"], result = "POINT"
Output: false



Constraints:

    2 <= words.length <= 5
    1 <= words[i].length, result.length <= 7
    words[i], result contains only upper case English letters.
    Number of different characters used on the expression is at most 10.

    This should be similar to Sudoku no?
    Think of number of square as Number of distinct character.
    Map one character to each square, and check if the sum works.
    If not back track and try it.


 */
public class VerbalAirthemtaic {
     private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};
    Set<Character> cSet = new HashSet<>();//Set of all characters.
    Map<Character,Integer> mapOfCharacterToIndex = null;//Which character mapped to which Array Index
    String[] ws = null;String res = null;
    boolean solved = false;

    public boolean isSolvable(String[] words, String result) {
        ws = words; res = result;
        for(String s : words) {
            for(char c : s.toCharArray()) {
                if (cSet.contains(c)) continue;
                cSet.add(c);
            }
        }
        for(char c : result.toCharArray()) {
            if (cSet.contains(c)) continue;
            cSet.add(c);
        }
        mapOfCharacterToIndex = new HashMap<>();
        int index = 0;
        for(char c: cSet) {
            mapOfCharacterToIndex.put(c,index++);
        }
        int[] cArray = new int[cSet.size()];
        BitSet bSet = new BitSet(10);
        return solveUsingBackTrack(0,cArray,bSet);
    }

    /*
       cIndex : Index in the array.
       cArray : Array of distinct characters.

     */
    public boolean solveUsingBackTrack(int cIndex,int[] cArray,BitSet bSet) {
        if (cIndex == cArray.length) {

               return solve(cArray);
        }
        //for(int i = cIndex; i < cArray.length;i++) {
            for(int v = 0; v < 10;v++) {
                if (bSet.get(v)) continue;
                bSet.set(v);
                cArray[cIndex] = v;
                if (!solveUsingBackTrack(cIndex+1,cArray,bSet))  {
                    bSet.clear(v);
                    cArray[cIndex] = -1;
                } else {
                    return true;
                }
            }
        //}
        return false;
    }

    /*
    Input: words = ["SEND","MORE"], result = "MONEY"
Output: true
Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652

     */
    public void test(String[] words, String result) {
        mapOfCharacterToIndex = new HashMap<>();
        mapOfCharacterToIndex.put('S',0);
        mapOfCharacterToIndex.put('E',1);
        mapOfCharacterToIndex.put('N',2);
        mapOfCharacterToIndex.put('D',3);
        mapOfCharacterToIndex.put('M',4);
        mapOfCharacterToIndex.put('O',5);
        mapOfCharacterToIndex.put('R',6);
        mapOfCharacterToIndex.put('Y',7);
        int[] cArray = new int[10];
        cArray[0] = 9;
        cArray[1] = 5;
        cArray[2] = 6;
        cArray[3] = 7;
        cArray[4] = 1;
        cArray[5] = 0;
        cArray[6] = 8;
        cArray[7] = 2;
        ws = words; res = result;
        System.out.println(solve(cArray));
    }

    public boolean solve(int[] cArray) {
        int sum = 0;
        for(String s : ws) {
            int test = convert(s,cArray);
            //System.out.println(" s "+s+" "+test);
            sum += test;
        }
        if (convert(res,cArray) == sum) return true;
        return false;
    }

    private int convert(String s,int[] cArray) {
        int num = 0;
        char[] cArr = s.toCharArray();
        for(int i = s.length()-1; i >= 0;i--) {
            /*
            if (('S' == cArr[i]) && (cArray[mapOfCharacterToIndex.get(cArr[i])] == 9)) {
                                     System.out.println(" For char "+cArr[i]+" value is  "+cArray[mapOfCharacterToIndex.get(cArr[i])]);
            }
            */
            //System.out.println(" For char "+cArr[i]+" value is  "+cArray[mapOfCharacterToIndex.get(cArr[i])]);
            num += (cArray[mapOfCharacterToIndex.get(cArr[i])]*POW_10[s.length()-1-i]);
        }
        return num;
    }




    public static void main(String[] args) {
        String[] sArr2 = {"LEET", "CODE"};  //"POINT"     false
       String[] sArr1 = {"SEND","MORE"};   //MONEY       true
        VerbalAirthemtaic vab = new VerbalAirthemtaic();
        //vab.test(sArr,"MONEY");
        System.out.println(vab.isSolvable(sArr2,"POINT"));
    }
}
