/*
Easiest is :
Median = middle element if odd, ((sum of middle 2)/2) for even
a1 -> N1, a2 -> N2
In  a sorted array of two
(N1+N2) % 2 == 0 -> ()(N1 + N2) /2 + (N1+N2 )/2 -1 )/2 is the median

It is possible to represent -> ((N1 + N2) /2 + (N1+N2-1 )/2  )/2

Another way-> Start from two median.
1 3 5 -> 3
7 9 10 13   -> 9.5
Total median -> 8

9.5 > 3
9 > 5

N1/2 , and N2/2 removed.


*/
public class MedianOfTwoSortedArray {

    public double giveMedian(int[] aA,int[] bA) {
        int s1 = (aA == null)?0:aA.length;
        int s2 = (bA == null)?0:bA.length;
        int tLength = s1+s2;
        if ( tLength == 0) {
            throw new IllegalArgumentException("Both arrays null or zero length");
        }
        if (s1 == 0) {
            return (bA[s2/2] + bA[(s2-1)/2])/2;
        }
        if (s2 == 0) {
            return (aA[s1/2] + aA[(s1-1)/2])/2;
        }
        int fIndex = 0; int sIndex = 0;
        int fM = 0; int sM = 0;
        int i = 0;int size = tLength/2;
        for(; i <= size;i++) {
            sM = fM;
            if (fIndex == (aA.length)) {//A Exhausted
                fM = bA[sIndex++];
                continue;
            }
            if (sIndex == (bA.length)) {//B Exhausted
                fM = aA[fIndex++];
                continue;
            }

            int fV = aA[fIndex];
            int sV = bA[sIndex];

            if(fV <= sV) {
                fM = fV;
                fIndex++;
            } else {
                fM = sV;
                sIndex++;
            }
        }
        if ( tLength%2 == 0) {
            return ((1.0 * (fM+sM))/2 ) ;
        }
        return fM;

    }

    public static void main(String[] args) {
        MedianOfTwoSortedArray mTS = new MedianOfTwoSortedArray();
        int[] a1 = {1,3,7,8,11};
        //int[] a2 = {};
        int[] a2 = {2,7,9,10,11,13};
        //int[] a1 = {1,2};
        //int[] a2 = {1,2};
        System.out.println(mTS.giveMedian(a1,a2));
    }

}
