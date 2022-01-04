import java.util.Arrays;
import java.util.Stack;

/*
503. Next Greater Element II
Medium

Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:

Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.


 */
public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        if ( (nums == null) || (nums.length == 0)) return nums;
        Stack<Integer> S = new Stack<>();
        S.push(0);
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res,-1);
        for(int i = 0; i < 2*n; i++) {
            int cElem = nums[i%n];
            while (!S.isEmpty() && nums[S.peek()] < cElem) {
                res[S.pop()] = cElem;
            }
            S.push(i%n);
        }
        return res;

    }



    public static void main(String[] args) {
        NextGreaterElement2 ng = new NextGreaterElement2();
        int[] A = {1,2,1};
        System.out.println(Arrays.toString(ng.nextGreaterElements(A)));
    }

}
