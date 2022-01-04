import java.util.HashMap;
import java.util.Map;

/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.



Example 1:

Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation:
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.

Example 2:

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation:
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.



Note:

    0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
    All worker and bike locations are distinct.
    1 <= workers.length <= bikes.length <= 10

Solution 1 : Create an n * m array with cost for each worker to bike.
Then say min nth = either min(n-1) + min(n) if possible. If not (conflict) figure out which conflict, and
 */
public class MinimumCost {

    Map<Integer,Integer> mapOfCost = new HashMap<>();
    public int assignBikes(int[][] workers, int[][] bikes) {
        if((workers == null) || (workers.length == 0)) {
            return 0;
        }
        boolean[] bikeTaken = new boolean[bikes.length];
        int minSoFar = findMin(workers,bikes,0,bikeTaken);
        return minSoFar;
    }

    /*
        Basically do DFS kind of search. So for each worker, we call it. We mark which bike he has, and try to find min.
     */
    private int findMin(int[][] workers, int[][] bikes,int cWorker,boolean[] bikeTaken) {
        if (cWorker >= (workers.length)) return 0;
        int key = createKey(bikeTaken);
        // Basically a map saying that with the same X number of bikes taken, what will be the min value. Also note if its 5 bike taken then it will be always sixth worker.
        if (mapOfCost.containsKey(key)) return mapOfCost.get(key);
        int costSoFar = Integer.MAX_VALUE;
        for(int i = 0; i < bikes.length;i++) {// For each bike.
           if (!bikeTaken[i]) {//This bike is not taken
               bikeTaken[i] = true;
               //Current worker cWorker took bike I which is not assigned to anyone.
               int cost = findDistance(workers[cWorker][0],bikes[i][0],workers[cWorker][1],bikes[i][1]) + findMin(workers, bikes,  cWorker+1, bikeTaken);
               if (cost < costSoFar) {
                   costSoFar = cost;
               }
               //Calling for next worker. he/she will choose a bike from not chosen one.
               bikeTaken[i] = false;
           }
        }
        mapOfCost.put(key,costSoFar);
        return costSoFar;
    }

    private int findDistance(int x1,int x2,int y1,int y2) {
        return (Math.abs(x1-x2)+Math.abs(y1-y2));
    }


    private int createKey(boolean[] visited) {
        int key = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                key |= 1 << i;
            }
        }
        return key;
    }

    public static void main(String[] args) {
        MinimumCost minC = new MinimumCost();
        //int[][] bikes = {{1,2},{3,3}};
        int[][] bikes = {{1,0},{2,2},{2,1}};
        int[][] worker = {{0,0},{1,1},{2,0}};
        System.out.println(minC.assignBikes(worker,bikes));

        System.out.println(1<<2);
        System.out.println(1<<3);
        System.out.println(1<<4);
    }

    /* BEST UNDERSTAND 1 << bikes.length

    Basically what its saying is that consider an array of bikes.
    The solution to the problem is some element in array is 1 and some zero.
    So one can think of the solution as binary numebr and number of solution possible is 2^(bikes.length) = N
    So create an array of length N. Each index correspond to an unique combination.
    Now the final solution will be among combination where N2 = workers.length has some value. At each stage say N2 = 2, you are finding the min value among the possible set.


    class Solution {
    public int assignBikes(int[][] workers, int[][] bikes) {
        int[] dp = new int[1 << bikes.length];
        int res = dfs(workers, bikes, 0, 0, dp);
        return res;
    }

    private int dfs(int[][] workers, int[][] bikes, int used, int count, int[] dp) {
        if (count == workers.length) return 0;
        if (dp[used] > 0) return dp[used];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            if ((used & (1 << i)) == 0) {
                used |= (1 << i); //set i th bit
                min = Math.min(min, dist(workers[count], bikes[i]) + dfs(workers, bikes, used, count+1, dp));
                used &= ~(1 << i); //unset i th bit
            }

        }
        return dp[used] = min;
    }

     */
}
