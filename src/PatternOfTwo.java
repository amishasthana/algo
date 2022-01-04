
import java.util.HashMap;
import java.util.Map;

/*
    Only two types of object in an array.
    Say X and Y.
    Find the longest subsequence so N(X) = N(Y)
    * Start from back. (Positions are in between)
    * At each compute the sum = (+1 for X so far - 1 for Y so far)
    * Basic idea if "sum" same within that range its N(X) = N(Y)
    * Hash map of "Sum" to pos. Only the higest pos. If duplicate calculte the delta if highest store it.
    *
    *     XXYXYYX
    *     Array length = 7
    *     Total pos
    *
    *
    *
 */
public class PatternOfTwo {

       private int sPos = -1;
       private int mPos = -1;
       public final static char X = 'X';


       /*
         Assuming it has only X and Y.
        */
       private void calculateRange(char[] iArray) {
           if (nullCheckFail(iArray)) {
               return;
           }
           Map<Integer,Integer> mapOfEqualLength = new HashMap<>();
           mapOfEqualLength.put(0,iArray.length);// 0 length at start.
           int sumSoFar = 0;
           for(int i = iArray.length-1; i >= -1;i--) {//Pos of in between
                   int pos = i+1;//pos in array
                   if (pos == iArray.length) {//Start of array
                       mapOfEqualLength.put(0,pos);
                       continue;
                   }
                   if (X == iArray[pos]) {
                       sumSoFar += 1;
                   } else {
                       sumSoFar -= 1;
                   }


               if (mapOfEqualLength.get(sumSoFar) != null) {
                   if ( (mPos -sPos) < (mapOfEqualLength.get(sumSoFar) - pos)) {
                       mPos = mapOfEqualLength.get(sumSoFar);
                       sPos = pos;
                   }
               } else {
                   mapOfEqualLength.put(sumSoFar,pos);
               }
           }//End of for
           System.out.println("Range is from "+sPos+" to "+mPos);
           for(int i = sPos;i < mPos;i++) {
               System.out.print(iArray[i]);
           }
       }



       private boolean nullCheckFail(char[] iArray) {
            if ((iArray == null) || (iArray.length == 0)) {
                return true;
            }
            return false;
       }

       public static void main(String[] args) {
           PatternOfTwo pTwo = new PatternOfTwo();
           char[] cArray = "XXYXXYX".toCharArray();
           pTwo.calculateRange(cArray);
       }
}
