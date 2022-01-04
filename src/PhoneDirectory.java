import java.util.BitSet;

/*
379. Design Phone Directory
Medium

Design a Phone Directory which supports the following operations:

    get: Provide a number which is not assigned to anyone.
    check: Check if a number is available or not.
    release: Recycle or release a number.

Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);

Accepted
32,106
Submissions
70,666
 */
public class PhoneDirectory {

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    final int[] pNumber;//Index to number
    final int[] pIndex;//Num to index.
    int freeIndex;// In pNumber array.
    final int maxNumbers;
    final BitSet bSet;// Whether a particular number is assigned.
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        pNumber = new int[maxNumbers];
        pIndex = new int[maxNumbers];
        for(int i = 0; i < pNumber.length;i++) {
            pNumber[i] = i;
            pIndex[i] = i;
        }
        bSet = new BitSet(maxNumbers);
        freeIndex = 0;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (freeIndex == maxNumbers) {//assuming its properly maintained.
            return -1;
        }
        int num = pNumber[freeIndex];
        freeIndex++;
        bSet.set(num);
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !bSet.get(number);
    }

    /** Recycle or release a number.
     * final int[] pNumber;//Index to number
     final int[] pIndex;//Num to index.
     * @param number
     */
    public void release(int number) {
         if (bSet.get(number)) {//Assuming always true.
             int cIndex = pIndex[number];

             /* First fIndex -1 number put to cIndex if possible.
                 fIndex-1 put this number.
                 fIndex -= 1
                 and also i Num to index num --> fIndex
              */
             if (freeIndex > 1) {
                 pNumber[cIndex] = pNumber[freeIndex-1];
                 pIndex[pNumber[freeIndex-1]] = cIndex;
                 pNumber[freeIndex-1] = number;
                 freeIndex--;
                 pIndex[number] = freeIndex;
             } else {//Number allready at first
                 freeIndex--;
             }
             bSet.set(number,false);//Relase it.
         }
    }
}
