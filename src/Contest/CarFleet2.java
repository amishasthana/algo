package Contest;

import java.util.Arrays;
import java.util.Stack;

/*
1776. Car Fleet II

    User Accepted: 144
    User Tried: 517
    Total Accepted: 154
    Total Submissions: 1177
    Difficulty: Hard

There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:

    positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
    speedi is the initial speed of the ith car in meters per second.

For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.

Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.



Example 1:

Input: cars = [[1,2],[2,1],[4,3],[7,2]]
Output: [1.00000,-1.00000,3.00000,-1.00000]
Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.

Example 2:

Input: cars = [[3,4],[5,4],[6,3],[9,1]]
Output: [2.00000,1.00000,1.50000,-1.00000]



Constraints:

    1 <= cars.length <= 105
    1 <= positioni, speedi <= 106
    positioni < positioni+1


SOLUTION --> Stack. Start from right.
The element in stack has 2 values speed and time its valid.
For a new element, check if its value is less then stack top--> continue to pop
else find time to merge, and if its less then t, then merge.
Otherwise pop and check the same with the next value.

ANOTHER VARIATION : Speed largest. In that case start from left.
 */
public class CarFleet2 {
    public class Car {
        int speed;
        int iPos;
        double time;

        public Car(int p, int s, double t) {
            speed = s;
            iPos = p;
            time = t;
        }
    }

    public double[] getCollisionTimes(int[][] cars) {
        Stack<Car> S = new Stack<>();
        double[] D = new double[cars.length];
        for (int i = cars.length - 1; i >= 0; i--) {
            Car cElem = new Car(cars[i][0], cars[i][1], -1);
            if (!S.isEmpty()) {
                while (!S.isEmpty()) {
                    Car nelem = S.peek();
                    if (nelem.speed >= cElem.speed) {
                        S.pop();
                    } else {
                        double t = ((nelem.iPos - cElem.iPos)*1.0) / (1.0*(cElem.speed - nelem.speed));
                        if ((nelem.time == -1) || (t <= nelem.time)) {
                            cElem.time = t;
                            break;
                        } else {
                            S.pop();
                        }
                    }//end of else
                }    //end of while
            }//end of if
            S.push(cElem);
            D[i] = cElem.time;
        }//end of for
        return D;
    }

    public static void main(String[] args) {
        CarFleet2 cf = new CarFleet2();
        int[][] A = {{3,4},{5,4},{6,3},{9,1}};
        System.out.println(Arrays.toString(cf.getCollisionTimes(A)));
    }
}