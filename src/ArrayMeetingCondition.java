import java.util.HashSet;
import java.util.Set;

/*
Given an array A that is a permutation of n numbers [1-n]. Find the number of subarrarys S that meets the following condition max(S) - min(S) = length(S) - 1.

Example 1:

Input: [4, 3, 1, 2, 5]
Output: 10
Explanation:
subarrays that meets the condition are
[4]
[3]
[1]
[2]
[5]
[4 3]
[1 2]
[3 1 2]
[4 3 1 2]
[4 3 1 2 5]

Apparently there is ref to https://www.geeksforgeeks.org/min-max-range-queries-array/
but not sure if and how.

Will do n^2.


 */
public class ArrayMeetingCondition {


    public void printArray(int[] iA) {
        int[][] printed = new int[iA.length][iA.length];
        for(int i = 0; i < iA.length;i++) {
            int cElem = iA[i];
            Set<Integer> deltaSet = new HashSet<>();
            for(int j = i; j< iA.length;j++) {
                deltaSet.add(Math.abs(iA[j]-cElem));
                if ( deltaSet.contains (j-i) && (printed[i][j] == 0)) {
                    printElem(iA,i,j);
                    printed[i][j] = 1;
                }
            }
        }
    }

    private void printElem(int[] iA,int i,int j) {
        if (j >= iA.length) return;
        for(int ii = i; ii <= j;ii++) {
            System.out.print(iA[ii]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //int[] iA = {4,3,1,2,5};
        int[] iA = {4,6,5,1,3,2};
        ArrayMeetingCondition amc = new ArrayMeetingCondition();
        amc.printArray(iA);
    }
}
