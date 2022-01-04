import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
1094. Car Pooling
Medium

You are driving a vehicle that has capacity empty seats initially available for passengers.
The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location]
contains information about the i-th trip: the number of passengers that must be picked up,
and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true

Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true

Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true





Constraints:

    trips.length <= 1000
    trips[i].length == 3
    1 <= trips[i][0] <= 100
    0 <= trips[i][1] < trips[i][2] <= 1000
    1 <= capacity <= 100000



 */
public class CarPooling {
    static class Trip implements  Comparable<Trip> {
        boolean start;
        int nOfPeople;
        int pos;

        @Override
        public int compareTo(Trip t) {
            if (this.pos == t.pos) {
                if (!this.start) return -1;
                if (!t.start) return 1;
            }
            return this.pos - t.pos;
        }

        public Trip(boolean s,int n,int p) {
            start = s;
            nOfPeople = n;
            pos = p;
        }
    }

    public boolean carPooling(int[][] trips, int capacity) {
          List<Trip> nList = new ArrayList<>();
          for(int[] iA : trips) {
              Trip t1 = new Trip(true,iA[0],iA[1]);
              Trip t2 = new Trip(false,iA[0],iA[2]);
              nList.add(t1);
              nList.add(t2);
          }
          Collections.sort(nList);
          int totalSoFar = 0;
          for(Trip t : nList) {
              System.out.println(t.start + " "+t.nOfPeople+" "+t.pos);
              if (!t.start)  {//Depart
                  totalSoFar -= t.nOfPeople;
              } else {//Get on
                  totalSoFar += t.nOfPeople;
                  if (totalSoFar > capacity) return false;
              }
          }
          return true;
    }

    public static void main(String[] args) {
        int[][] trips = {{2,1,5},{3,3,7}};
        CarPooling cp = new CarPooling();
        cp.carPooling(trips,4);
    }
}

