package Contest;

/*
493. Reverse Pairs
Hard

Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2

Example2:

Input: [2,4,3,5,1]
Output: 3

Note:

    The length of the given array will not exceed 50,000.
    All the numbers in the input array are in the range of 32-bit integer.

Accepted
48,388
Submissions
182,168
 */
public class ReversePair {

    int res = 0;
    int[] temp = null;

    public int reversePairs(int[] nums)  {
        temp = new int[nums.length];
        mergeSort(nums,0,nums.length);
        return res;
    }

    public void mergeSort(int[] A, int s, int e) { // s inclusive. e exclusive.
        if (s >= (e-1)) return;
        int mid = (s+e)/2;
        mergeSort(A,s,mid);
        mergeSort(A,mid,e);
        int f = s; int sec = mid;
        for(int i = s; i < e;i++) {
            if (doNotSwap(get(A,f,mid) , get(A,sec,e))) {
                temp[i] = A[f++];
            } else {
                temp[i] = A[sec++];
                if (f < mid) {
                    res += getFitCriterion(A,f,mid-1,2*temp[i]);
                }
            }
        }
        for(int i = s; i < e;i++) {
            A[i] = temp[i];        }

        //System.out.println(Arrays.toString(A));
    }

    private boolean doNotSwap(int f,int s) {
        if ((f < 0) && (s < 0)) {
            return !( f > 2*s);
        } else {
            return (f <= s);
        }
    }

    private int getFitCriterion(int[] A,int s,int e,int v) {
        if (A[e] <= v) return 0;
        int l = s; int r = e; int m = (l+e)/2;
        while (l <= r) {
            if (A[m] > v) {
                r = m-1;
            } else {
                l = m+1;
            }
            m = (l+e)/2;
        }

        return (e-m)+1;
    }

    public int get(int[] A, int pos, int last) {
        if (pos >= last) return Integer.MAX_VALUE;
        return A[pos];
    }


    public static void main(String[] args) {
        //int[] A = {2,4,3,5,1};
        int[] A = {-5,-5};
        ReversePair RP = new ReversePair();
        System.out.println(RP.reversePairs(A));

    }
}
