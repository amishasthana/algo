import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
354. Russian Doll Envelopes
Hard

You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

ANOTHER WAY


    Sort the array. Ascend on width and descend on height if width are same.
    Find the longest increasing subsequence based on height.

    Since the width is increasing, we only need to consider height.
    [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]



// binary search: O(nlogn)
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;

        for(int num: nums) {
            // binary search
            int left = 0, right = size, middle = 0;     // right = size
            while(left < right) {
                middle = left + (right - left) / 2;
                if(dp[middle] < num) left = middle + 1;
                else right = middle;
            }

            // left is the right position to 'replace' in dp array
            dp[left] = num;
            if(left == size) size++;
        }
        return size;
    }
}

 */
public class RussianDoll {
    Map<Integer, Integer> maxCal = new HashMap<>();

    public int maxEnvelopes(int[][] envelopes) {
        if ((envelopes == null) || (envelopes.length == 0) || (envelopes[0].length == 0)) return 0;
        int[][] G = createGraph(envelopes);
        Queue<Integer> Q = getHighestEnv(G);
        int max = 1;
        while (!Q.isEmpty()) {
            int cMax = findMax(Q.poll(), G);
            max = Math.max(cMax, max);
        }
        return max;
    }

    /* Graph is from ith Row I > Col J.  */
    public int findMax(int CE, int[][] G) {
        int cMax = maxCal.getOrDefault(CE, 0);
        if (cMax != 0) return cMax;
        cMax = 0;
        for (int j = 0; j < G.length; j++) {
            if (G[CE][j] == 1) {// CE can consume j
                cMax = Math.max(cMax, findMax(j, G));
            }
        }//End of for.
        cMax++;//Adding self.
        maxCal.put(CE, cMax);
        return cMax;
    }

    public Queue<Integer> getHighestEnv(int[][] G) {
        Queue<Integer> q = new LinkedList<>();
        for (int j = 0; j < G.length; j++) {
            boolean largest = true;
            for (int i = 0; i < G.length; i++) {
                if (G[i][j] == 1) {
                    largest = false;
                    break;
                }
            }
            if (largest) q.add(j);
        }
        return q;

    }


    public int[][] createGraph(int[][] env) {
        int[][] G = new int[env.length][env.length];
        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env.length; j++) {
                if ((env[i][0] > env[j][0]) && (env[i][1] > env[j][1])) G[i][j] = 1;
            }
        }
        return G;
    }

    public static void main(String[] args) {
        RussianDoll rd = new RussianDoll();
        int[][] A = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(rd.maxEnvelopes(A));
    }

}
