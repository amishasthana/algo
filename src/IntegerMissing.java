
/*
163. Missing Ranges Medium Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper],
return its missing ranges. Example: Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99, Output: ["2", "4->49", "51->74", "76->99"]

 */
public class IntegerMissing {

    public void printRange(int[] iA,int lower,int upper) {
        if ((iA == null) || (iA.length == 0)) {
            printNumber(lower,upper);
        }
        int cLower = lower;
        int index = 0;

        while (cLower <= upper) {
            int cElem = iA[index];
            int cUpper = Math.max(cLower,cElem-1);
            if((cLower <= cUpper) && (cLower != cElem)) {
                printNumber(cLower, cUpper);
            }
            cLower = cElem+ 1;
            index++;
            if (index == iA.length) break;
        }//End of while

        if (cLower <= upper) {
            printNumber(cLower,upper);
        }

    }

    private void printNumber(int cLower,int cUpper) {

            if (cLower == cUpper) {
                System.out.print(cLower+",");
            } else {
                System.out.print(cLower+"->"+cUpper+",");
            }

    }


    public static void main(String[] args) {

        int[] ia = new int[] {0, 1, 3, 50, 75};
        IntegerMissing im = new IntegerMissing();
        im.printRange(ia,0,99);
        int[] ia1 = new int[] {3,};
        im.printRange(ia1,0,4);

    }
}
