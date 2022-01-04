import java.util.*;

/*
1345. Jump Game IV

    User Accepted: 556
    User Tried: 1125
    Total Accepted: 610
    Total Submissions: 3209
    Difficulty: Hard

Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

    i + 1 where: i + 1 < arr.length.
    i - 1 where: i - 1 >= 0.
    j where: arr[i] == arr[j] and i != j.

Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.

Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

Example 4:

Input: arr = [6,1,9]
Output: 2

Example 5:

Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3



Constraints:

    1 <= arr.length <= 5 * 10^4
    -10^8 <= arr[i] <= 10^8


Basically its a DP problem.
 */
public class JumpGameIV {
    int[] minSteps = null;
    Map<Integer,Set<Integer>> posSet = new HashMap<>();
    public int minJumps(int[] arr) {
        minSteps = new int[arr.length];
        for(int i=1; i < arr.length;i++) minSteps[i] = -1;
        minSteps[0] = 0;
        populateSet(arr);
        findMinPathUsingGraph(arr);
        return minSteps[arr.length-1];
    }

    /* Optimal */
    private void findMinPathUsingGraph(int[] arr) {
        Queue<Integer> popQ = new ArrayDeque<>();
        popQ.add(0);//Store the index.
        while (!popQ.isEmpty()) {
            int cIndex = popQ.poll();
            //System.out.println("Current index poll"+cIndex);
            Set<Integer> cIndexSet = posSet.get(arr[cIndex]);
            int cSteps = 1 + minSteps[cIndex];
            if(cIndex > 0) {
                cIndexSet.add(cIndex-1);
            }
            if(cIndex < (arr.length-1)) {
                cIndexSet.add(cIndex+1);
            }
            for(Integer cIndexChild : cIndexSet) {
                //System.out.print("Child are "+cIndexChild);
                if (minSteps[cIndexChild] == -1) {
                    minSteps[cIndexChild] = cSteps;
                    popQ.add(cIndexChild);
                }
            }
            cIndexSet.clear();
            //System.out.println("Size "+popQ.size());
        }
    }

    private void findMinPath(int[] arr) {
        /*
             You find value for some value J .
             All J will have J+1 if not allready populated.
             All near and dear ones will be either J+2 if not allready populated.
         */
        Queue<Integer> popQ = new ArrayDeque<>();
        popQ.add(arr.length-1);//Store the index.
        while (!popQ.isEmpty()) {
            int cIndex = popQ.poll();
            int cMinValue = minSteps[cIndex]+1;
            Set<Integer> cIndexSet = posSet.get(arr[cIndex]);
            if(cIndex > 0) {
                 cIndexSet.add(cIndex-1);
            }
            if(cIndex < (arr.length-1)) {
                cIndexSet.add(cIndex+1);
            }
            for(Integer cIndexChild : cIndexSet) {
                if (minSteps[cIndexChild] <= cMinValue) {
                    continue;
                } else {
                    minSteps[cIndexChild] = cMinValue;
                    popQ.add(cIndexChild);
                }
            }
        }
    }

    private int findMinPath(int[] arr,int pos) {
        if (minSteps[pos] != -1) return minSteps[pos];
        if(pos == (arr.length-1)) return 0;
        Set<Integer> setOfPossval = new HashSet<>();
        if (pos > 0) {
            setOfPossval.add(pos-1);
        }
        if (pos < (arr.length-1)) {
            setOfPossval.add(pos+1);
        }
        for(Integer sValue : posSet.get(arr[pos])) {
            if (sValue != pos) {
                setOfPossval.add(sValue);
            }
        }
        int minPath = Integer.MAX_VALUE;
        for(Integer posV : setOfPossval) {
            int cPath = findMinPath(arr,posV)+1;
            if (minPath > cPath) {
                minPath = cPath;
            }
        }
        minSteps[pos] = minPath;
        return minPath;

    }

    private void populateSet(int[] arr) {
        for(int i=0; i < arr.length;i++) {
            Set<Integer> setOfVal = posSet.get(arr[i]);
            if (setOfVal == null) {
                setOfVal = new HashSet<>();
            }
            setOfVal.add(i);
            posSet.put(arr[i],setOfVal);
        }
    }

    public static void main(String[] args) {
        JumpGameIV jm = new JumpGameIV();
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println(jm.minJumps(arr));
    }
}
