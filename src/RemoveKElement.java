/*
Given an array, please check if we can remove at most one element to make it strictly increasing.
For 1
Maintain prev and prevprev.
 */
public class RemoveKElement {
    public boolean isPossible(int[] iA) {

        if ((iA == null) || (iA.length <= 1)) return true;
        int nFaults = 0; int maxPrev = iA[0];
        for(int i = 1; i < iA.length;i++) {
            int cElem = iA[i];
            if (cElem >= maxPrev ) {
                maxPrev = cElem;
                continue;
            } else {//Fault
                nFaults++;
                if (nFaults > 1) return false;
                //Can we keep current element.
                if ((i == 1)|| (cElem >= iA[i-1])) {//Yes we can.
                    maxPrev = cElem;
                    continue;
                }
                //We can not , maxPrev do not change.
            }

        }
        return true;
    }


    public boolean isPossibleForK(int[] iA,int k) {
        if ((iA == null) || (iA.length <= k)) return true;
        if ( findNumber(iA) > k) {
            return false;
        }
        return true;
    }

    private int findNumber(int[] iA) {
        int[] miss = new int[iA.length];
        boolean isBreak = false;
        for (int i = iA.length-2;i >= 0;i--) {
            if (iA[i] <= iA[i+1]) {
                if (isBreak) {
                    isBreak = false;
                    int nIndex = findNextLowestIndex(iA,i);
                    miss[i]= Math.min(miss[i + 1],miss[nIndex - i]);
                } else {
                    miss[i] = miss[i + 1];
                }
             } else {
                isBreak = true;
                int nextHighestIndex  = findNextHighestIndex(iA,i,iA[i]);
                System.out.println(" i = "+i+" nextHighestIndex = "+nextHighestIndex);
                if (nextHighestIndex == iA.length) {
                    miss[i] = (nextHighestIndex - i -1);
                } else {
                    miss[i] = (nextHighestIndex - i -1)+miss[nextHighestIndex];
                }
            }
        }
        return miss[0];
    }

    private int findNextLowestIndex(int[] iA,int i) {
        int retV = iA.length;
        for(int index = i+1;index < iA.length;index++) {
            if (iA[i] >= iA[index]) return index;
        }
        return retV;
    }

    private int findNextHighestIndex(int[] iA,int i,int v) {
        int retV = iA.length;
        for(int index = i+1;index < iA.length;index++) {
            if (v <= iA[index]) return index;
        }
        return retV;
    }


    public static void main(String[] args) {
         int[] iA1 = {1,2,5,3,6,7,8};
        int[] iA2 = {1,2,5,3,6,7,8};
        int[] iA3 = {1, 2, 3, 7, 8, 4, 5};
        RemoveKElement rk = new RemoveKElement();
        /*
        System.out.println(rk.isPossible(iA1));
        System.out.println(rk.isPossible(iA2));
        System.out.println(rk.isPossible(iA3));
        System.out.println(rk.isPossibleForK(iA1,1));
        System.out.println(rk.isPossibleForK(iA2,1));
        System.out.println(rk.isPossibleForK(iA3,1));
        */
        int[] iA4 = {1,2,7,4,5,8,10,6,7};
        //System.out.println(rk.isPossibleForK(iA4,1));
        //System.out.println(rk.isPossibleForK(iA4,2));
        System.out.println(rk.isPossibleForK(iA4,3));
    }
}
