import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
1306. Jump Game III

    User Accepted: 2346
    User Tried: 2579
    Total Accepted: 2579
    Total Submissions: 4414
    Difficulty: Medium

Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3

Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is:
index 0 -> index 4 -> index 1 -> index 3

Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.


 */
public class JumpThree {

    /*
    Start is the start index.
    0 is some value at some index.
    Starting at start index one need to reach at zero.
     */
    int[] arrG = null;

    public boolean canReach(int[] arr, int start) {
        arrG = arr;
        Queue<Integer> nQ = new LinkedList<>();
        Set<Integer> vSet = new HashSet<>();
        vSet.add(start);
        nQ.add(start);
        //add(nQ,arr[start]+start);
        //add(nQ,arr[start]-start);

        while (!nQ.isEmpty()) {
            int cIndex = nQ.poll();
            //System.out.println("Index = "+cIndex+" val "+arr[cIndex]);
            if (arr[cIndex] == 0) return true;
            int addIndex = arr[cIndex] + cIndex;
            if (!vSet.contains(addIndex)) {
                add(nQ, addIndex );
                vSet.add(addIndex);
            }
            int subIndex = cIndex - arr[cIndex];
            if (!vSet.contains(subIndex)) {
                add(nQ, subIndex);
                vSet.add(subIndex);
            }

        }
        return false;
    }

    private void add(Queue<Integer> nQ, int index) {
        if ((index < 0) || (index >= (arrG.length))) {
            return;
        }
        nQ.add(index);
    }

    public static void main(String[] args) {
        JumpThree jt = new JumpThree();
        int[] iA = {3,0,1,2};
        System.out.print(jt.canReach(iA,0));
    }
}