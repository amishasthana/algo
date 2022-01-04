import java.util.LinkedList;
import java.util.List;

/*
1248. Count Number of Nice Subarrays
Medium

Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.



Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.

Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Beautiful answer :
/*
public int numberOfSubarrays(int[] A, int k) {
        return atMost(A, k) - atMost(A, k - 1);
    }

    public int atMost(int[] A, int k) {
        int res = 0, i = 0, n = A.length;
        for (int j = 0; j < n; j++) {
            k -= A[j] % 2;
            while (k < 0)
                k += A[i++] % 2;
            res += j - i + 1;
        }
        return res;
    }

I republished this post.
The original one is deleded by Leetcode without any notification or information.
The only reason that, I included my youtube channel link.
Excusem me, What the HACK!?

Intuition:

First you may have feeling of using sliding window.
Then this idea get stuck in the middle.

This problem will be a very typical sliding window,
if it asks the number of subarrays with at most K distinct elements.

Just need one more step to reach the folloing equation:
exactly(K) = atMost(K) - atMost(K-1)

Explanation

    Write/copy a helper function of sliding window,
    to get the number of subarrays with at most K distinct elements.
    Done.

Complexity:

Time O(N) for two passes.
Space O(K) at most K elements in the counter

Of course, you can merge 2 for loops into one, if you like.

Java:

    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }
    int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < A.length; ++j) {
            if (count.getOrDefault(A[j], 0) == 0) K--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) K++;
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }

 */
public class NiceSubArrays {
    public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> oddList = new LinkedList<>();
        for(int i = 0; i < nums.length; i++) {
            if (nums[i]%2 == 1) oddList.add(i);
        }//End of for.
        if (oddList.size() < k)  return 0;
        int[] oddInd =  new int[oddList.size()];
        for(int i = 0; i < oddInd.length; i++) {
            oddInd[i] = oddList.get(i);
        }
        int nArray = 0;
        /*
             1,1,2,1,1 -> 3 (l = 5)
             0,1,3,4
             l = 1
             r = 1
         */
        for(int i = 0, j = i+k-1;j <  oddInd.length;i++,j++ ) {
            int lSide = (i == 0) ? (oddInd[i]+ 1) : (oddInd[i]  - oddInd[i-1]);

            int rSide = (j == (oddInd.length -1))? (nums.length - oddInd[j] )
                    : ( oddInd[j+1] - oddInd[j] );
            nArray += Math.max(1,lSide) * Math.max(1,rSide);
        }

        return nArray;

    }


    public static void main(String[] args) {
        int[] a = {1,1,2,1,1};
        int[] a1 = {2,2,2,1,2,2,1,2,2,2};
        NiceSubArrays nsa = new NiceSubArrays();
        System.out.println(nsa.numberOfSubarrays(a,3));
        System.out.println(nsa.numberOfSubarrays(a1,2));
    }

}
