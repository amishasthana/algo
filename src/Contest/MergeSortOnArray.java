package Contest;

import java.util.Arrays;

public class MergeSortOnArray {
    int[] temp = null;
    public void mergeSort(int[] A) {
        temp = new int[A.length];
        mergeSort(A,0,A.length);
        System.out.println(Arrays.toString(A));
    }

    public void mergeSort(int[] A,int s,int e) { // s inclusive. e exclusive.
        if (s >= (e-1)) return;
        int mid = (s+e)/2;
        mergeSort(A,s,mid);
        mergeSort(A,mid,e);
        int f = s; int sec = mid;
        for(int i = s; i < e;i++) {
            if (get(A,f,mid) <= get(A,sec,e)) {
                temp[i] = A[f++];
            } else {
                temp[i] = A[sec++];
            }
        }
        for(int i = s; i < e;i++) {
            A[i] = temp[i];
        }
    }

    public int get(int[] A,int pos,int last) {
        if (pos >= last) return Integer.MAX_VALUE;
        return A[pos];
    }

    public static void main(String[] args) {
        int[] A = {3,1,7,2,3,2,6,5,9,20,1};
        MergeSortOnArray msa = new MergeSortOnArray();
        msa.mergeSort(A);
    }
}
