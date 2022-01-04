import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d
in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]


    Create an auxiliary array aux[] and store sum of all possible pairs in aux[].
    The size of aux[] will be n*(n-1)/2 where n is the size of A[].
    Sort the auxiliary array aux[].
    Now the problem reduces to find two elements in aux[] with sum equal to X.
    We can use method 1 of this post to find the two elements efficiently. There is following important point to note though.
    An element of aux[] represents a pair from A[].
    While picking two elements from aux[], we must check whether the two elements have an element of A[] in common.
     For example, if first element sum of A[1] and A[2], and second element is sum of A[2] and A[4], then these two elements of aux[] don’t represent four distinct elements of input array A[].

 */
public class FourSum {

    int[] iA = null; int totalIterate = 0;
    int[] bitA = null;
    Map<Integer,Set<Integer>> mapOfVal = new HashMap<>();//Key is sum and Value is Index.
    int minV = Integer.MAX_VALUE;
    int maxV = Integer.MIN_VALUE;
    public Set<Set<Integer>> init(int total,int steps,int[] iA) {
        this.iA = iA;
        bitA = new int[iA.length];
        totalIterate = steps;
        for(int i = 0; i < iA.length;i++) {
            int v = iA[i];
            Set<Integer> indexSet = mapOfVal.get(v);
            if (indexSet == null) {
                indexSet = new HashSet<>();
            }
            indexSet.add(i);
            mapOfVal.put(v,indexSet);
            if (v < minV) {
                minV = v;
            }
            if (v > maxV) {
                maxV = v;
            }

        }
        return findSetWithValue(total,steps,0);

    }

    private Set<Set<Integer>> findSetWithValue(int valToFind,int nElem,int indexToStart) {
        Set<Set<Integer>> res = new HashSet<>();
        for(int i = indexToStart; i < iA.length;i++) {
            int curElem = iA[i];
            int cValue = valToFind-curElem;
            if(nElem == 1) {
                if (cValue == 0) {
                    Set<Integer> curSet = new HashSet<>();
                    curSet.add(i);
                    res.add(curSet);
                }
            } else {
                Set<Set<Integer>> newResultSet = findSetWithValue(valToFind-curElem,nElem-1,i+1);
                for(Set<Integer> s : newResultSet) {
                    s.add(i);
                    res.add(s);
                }
            }
        }
        return res;
    }

    public void printSet(Set<Set<Integer>> setOfInt) {
        for(Set<Integer> s : setOfInt) {
           for(Integer i : s) {
               System.out.print(i+" ");
           }
            System.out.println();
        }
    }





    public static void main(String[] args) {
        int[] iA = {1, 0, -1, 0, -2, 2};
        FourSum fs = new FourSum();
        fs.printSet(fs.init(0,4,iA));
    }


}
