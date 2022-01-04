package Goog;
/*
2.) check if you can insert an interval. Started with Binary search on sorted list end up on a search
 in a BST traversal because handling of mid conditions in the sorted list was getting
 way to complex at that time. The interviewer allowed me to assume the input in
  whatever format(just state the assumptions).
  Follow up qs : what if the given list of schedule in all in the range of 1 .. 100.
  No need of any fancy data structure just use an array.. arr[i..j] = 1
  will represent some interval k's start to end.
 to check if you can insert just check if you there is any 1 in arr[incoming.start... incoming.end]
 */
import java.util.*;
public class InsertInterval {
    TreeSet<Integer> endS = new TreeSet<>();
    TreeSet<Integer> startS = new TreeSet<>();
    int tSize = 0;
    public boolean isInsertable(int[] list) {
        boolean ret = true;
        int lSize = endS.tailSet(list[0]).size();
        int rSize = startS.headSet(list[1]).size();
        if ((lSize + rSize ) > tSize) ret = false;
        endS.add(list[1]);
        startS.add(list[0]);
        tSize++;
        return ret;
    }

    int[] A = new int[102];
    TreeSet<Integer> allP = new TreeSet<>();
    public boolean isInsertable2(int[] list) {
        boolean ret = true;
        allP.add(0); allP.add(101);
        Integer l = allP.floor(list[0]);
        int r = allP.ceiling(list[1]);
        if ((A[l] > 0 ) || (A[r] >0)) ret = false;
        if (ret) {
            for (int i = list[0]; i <= list[1]; i++) {
                if (A[i] > 0) {ret = false; break;}
            }
        }
        allP.add(list[1]);
        allP.add(list[0]);
        A[list[0]] = 1 + A[l];
        A[list[1]] = A[r];
        return ret;
    }

    public static void main(String[] args) {
        InsertInterval is = new InsertInterval();
        int[] A = {2, 5};
        int[] B = {1, 3};
        int[] C = {5, 7};
        System.out.println(is.isInsertable2(A));
        System.out.println(is.isInsertable2(B));
        System.out.println(is.isInsertable2(C));
    }

}
