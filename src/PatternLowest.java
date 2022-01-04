/*
Infinite periodic string t with pattern "s" which is repeated infinitely to generate t.
 n --> length of s.
 Generate a new "n" character string following steps:
   a) He will choose an offset : a non negative integer x.
   b) He will choose a length of a step : a prime numebr p that is less than m.
   c) First n characters of string t, if we start at index X and after each character we move p positions to the right.



 */

import java.util.*;

public class PatternLowest {
    /*
        Assume position within array.
     */
    private int compareCharPattern(char[] cArray,int fPos,int fGap,int sPos,int sGap) {
        int index = 0;
        int compare = 0;
        while(index < cArray.length) {
            char fChar = getChar(cArray,(fPos+index*fGap));
            char sChar = getChar(cArray,(sPos+index*sGap));
            compare = (fChar - sChar);
            if (compare != 0) return compare;
            index++;
        }
        return compare;
    }

    private char getChar(char[] cArray,int pos) {
          return cArray[pos%cArray.length];
    }

    /*
     * Return null of string is null or of size 0
     * Its not clear what happens when size is 1. Right now returning the same string.
    */
    public String getMin(String s) {
        if ((s == null) || (s.length() == 0) ) {
             return null;
        }
        int size = s.length();
        if (size == 1) return s;//Trivial case.
        char[] cArray = s.toCharArray();
        int[] aOfPrime = getArrayOfPrime(size);

        int lCharPos = 0;
        int lGap = aOfPrime[0];
        for(int i = 0;i < cArray.length;i++) {
            for (int pIndex = 0; pIndex < aOfPrime.length; pIndex++) {

                if(cArray[lCharPos] < cArray[i]) continue;//Optimization no need to compare if initial char is high
                int compare = compareCharPattern(cArray, lCharPos, lGap,i, aOfPrime[pIndex]);
                if (compare > 1) {//Flip lowest
                    lCharPos = i;
                    lGap = aOfPrime[pIndex];
                }
            }
        }
        String retValue = getLowestElement(lCharPos,lGap,cArray);
        return retValue;
    }


    private String getLowestElement(int lCharPos,int lGap,char[] cArray) {
        StringBuilder strB = new StringBuilder(cArray.length);
        int index = 0;
        while (index < cArray.length) {
            strB.append(getChar(cArray,(lCharPos+(lGap*index))));
            index++;
        }
        return strB.toString();
    }

    /*
    https://en.wikipedia.org/wiki/Sieve_of_Sundaram
    Understand it
     */
    private int[] getArrayOfPrime(int max) {

        int counter = 1;
        // Because the number can e 2n+2 for a given n
        // and we want a prime number less than n,
        // we reduce it to half
        int bSize = (max - 2) / 2;

        // BitSet created with a specific size
        // with default value initialized as false
        BitSet bitSet = new BitSet(bSize);

        // set the index number of the form
        // (i + j + 2ij) as true such that 1<=i<=j
        // this is the main logic of the sieve of sundaram
        for (int i = 1; i <= bSize; i++) {
            for (int j = i; (i + j + 2 * i * j)
                    <= bSize; j++) {
                bitSet.set(i + j + 2 * i * j);
            }
        }


        // Now print the odd prime list, with a little
        // formatting for eye-candy.
        int[] arrayPrime = new int[bSize];
        int arrPos = 0;
        arrayPrime[arrPos] = 2;//2 is not there.
        arrPos++;
        for (int i = 1; i < bSize; i++) {
            if (bitSet.get(i) == false) {
                //System.out.print((2 * i + 1));
                arrayPrime[arrPos++] = (2 * i + 1);
                //System.out.print(++counter % 9 = 0 ? "\n" : "\t");
            }
        }

        Arrays.stream(arrayPrime).forEach(System.out::println);
        return arrayPrime;
    }

    public static void main(String[] args) {
        PatternLowest patternLowest = new PatternLowest();
        System.out.println(patternLowest.getMin("startraek"));

    }

}
