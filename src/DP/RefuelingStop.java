package DP;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
871. Minimum Number of Refueling Stops
Hard

A car travels from a starting position to a destination which is target miles east of the starting position.

Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.

When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.



Example 1:

Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.

Example 2:

Input: target = 100, startFuel = 1, stations = [[10,100]]
Output: -1
Explanation: We can't reach the target (or even the first gas station).

I reached a particular station.
 Two states : Stopped -> Pull all oil.  State+1
                     Did not stop --> State. < No oil>

Approach 1: 1D DP, O(N^2)

dp[t] means the furthest distance that we can get with t times of refueling.

So for every station s[i],
if the current distance dp[t] >= s[i][0], we can refuel:
dp[t + 1] = max(dp[t + 1], dp[t] + s[i][1])

In the end, we'll return the first t with dp[i] >= target,
otherwise we'll return -1.


public int minRefuelStops(int target, int cur, int[][] s) {
        Queue<Integer> pq = new PriorityQueue<>();
        int i = 0, res;
        for (res = 0; cur < target; res++) {
            while (i < s.length && s[i][0] <= cur)
                pq.offer(-s[i++][1]);
            if (pq.isEmpty()) return -1;
            cur += -pq.poll();
        }
        return res;
    }



 */
public class RefuelingStop {
    /*
          First way D[t] --> Furthest we can go with T refueling.
          D[T] --> 0 to stations.length
          DT = D[T-1] --> Any station we can
     */
    public int minRefuelStopsNSq(int target, int startFuel, int[][] stations) {
        long[] D = new long[stations.length+1];// ith mean there have been i refuel. 0 ean no refuel.
        Arrays.fill(D,startFuel);//Basically saying that startfuel is always there.

        for( int i = 0; i < stations.length;i++ ) { //For stations loop.
            for(int j = i; j >= 0 && D[j] >= stations[i][0] ;j-- ) {
                /*
                With ith stattion there can be maximum i refuel.
                So we will update from i to zero all Ds considering if it can reach station at i.
                 So the idea is use S[i] to update to higher value if possible.
                  Look at the statement D[j]+stations[i][1]. D[j] is calculated for stations[1-1] at this point of time.
                  So assuming D[j] is calculated at using just [1-1] and for D[j+1] if we use s[i] can we improve D[j+1]
                  Next loop we will improve D[j] using D[j-1]
                 */
                D[j+1] = Math.max(D[j+1],D[j]+stations[i][1]);
            }
        }
        for(int i = 0; i < D.length;i++) {
            if (D[i] >= target) return i;
        }
        return -1;
    }

    /*
       With a given range try to fill from station which has max Fuel.
       If you have reached target you are done.
       Otherwise populate the queue with other potential station in range.
       Repeat.
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> qMaxFuel = new PriorityQueue<>();//increasing order, so we will populate using negative, so highest goes first.
        int rFreq = 0; long rSoFar = 0;
        qMaxFuel.add(-1*startFuel);
        while (!qMaxFuel.isEmpty()) {
            int maxF = qMaxFuel.poll()*-1;
            if ((maxF+rSoFar) >= target) return rFreq;
            rFreq++;
            for(int i = 0; i < stations.length ;i++) {//new stations.
                if ((stations[i][0] > rSoFar) && (stations[i][0] <= (maxF+rSoFar))) {
                    qMaxFuel.add(-1 * stations[i][1]);
                }
            }
            rSoFar  = maxF+rSoFar;
        }
        return -1;

    }



    public static void main(String[] args) {
        RefuelingStop RS  = new RefuelingStop();
        int[][] S = {{10,60},{20,30},{30,30},{60,40}};
        int[][] S1 = {{13,21},{26,115},{100,47},{225,99},{299,141},{444,198},{608,190},{636,157},{647,255},{841,123}};
        //int[][] S = {{25,30}};
        System.out.println(RS.minRefuelStops(100,10,S));
    }



}
