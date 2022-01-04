import java.util.HashMap;
import java.util.Map;

/*


Basically a Map of Number to letter provided just like a phone dial pad.
0--> A,F,G
1--> B,C,R,X
....

Given a set of numbers : say "145" --> Print all combination it can point to.

There are various ways to solve it. The best way as far as I can see is as follows:
1) Calculate the total combination possible.
2) For each number at Xth position you are filling the Xth position
3) There is a repeating factor, based on where its in the array.

 */


public class PhoneBookOptimized {

    class PhoneBook {
        final int n;
        final char[] cArray;
        final int size;

        public PhoneBook(int i,String alp) {
            n = i;
            size = alp.length();
            cArray = alp.toCharArray();
        }


    }

    Map<Integer,PhoneBook> mOfPhone = new HashMap<>();

    private void init() {
        PhoneBook pb = new PhoneBook(3,"XYZ");
        mOfPhone.put(3,pb);

        pb = new PhoneBook(4,"ABC");
        mOfPhone.put(4,pb);

        pb = new PhoneBook(5,"KLMN");
        mOfPhone.put(5,pb);

    }



    /*
        The main method.
     */
    public void printTheNumber(int[] nArray) {
        init();
        if ((nArray == null) || (nArray.length == 0)) {
            return;
        }

        int tRepeat = getTotalSize(nArray);
        char[] cArray = new char[nArray.length];
        for(int i = 0; i < tRepeat;i ++) {//Main loop
            int rFactor = 1; //What is this variable. It indicate the number of time the letter should repeat.
            for(int j = 0;j<nArray.length;j++) {//For each number
                int currentNum = nArray[j];
                char[] charForNum = mOfPhone.get(currentNum).cArray;
                cArray[j] = figureOutChar(charForNum,i,rFactor);
                rFactor *= charForNum.length;
            }
            for(int  p = 0;p < nArray.length;p++) {
                System.out.print(cArray[p]);
                cArray[p] = ' ';
            }
            System.out.println();
        }
    }


    /*
         So if rFactor = 1 then any alphabet should repeat 1.
         So lets say its 10th loop. --> loop/arraysize
         If its 3 then?
            10th loop -->
                3 * array size is when it should come back
                = 3 * 4 = 12

     */
    private char figureOutChar(char[] charForNum,int loop,int rFactor) {
        int pos = 0;
        if (rFactor == 1) {
            pos = (loop/rFactor) % (charForNum.length);
        } else {
            pos = (loop/rFactor) % (charForNum.length);
        }
        char c = charForNum[pos];
        return c;
    }

    private int getTotalSize(int[] nArray) {
        int totalSize = 1;
        for(int i : nArray) {
            totalSize *= mOfPhone.get(i).size;
        }
        return totalSize;
    }

    public static void main(String[] args) {
        PhoneBookOptimized pbo = new PhoneBookOptimized();
        int[] iA = new int[]{3,4,5};
        pbo.printTheNumber(iA);
    }

}
