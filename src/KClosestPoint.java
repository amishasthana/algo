import java.util.Arrays;
import java.util.Comparator;

/*
. K Closest Points to Origin
Medium

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)



Note:

    1 <= K <= points.length <= 10000
    -10000 < points[i][0] < 10000
    -10000 < points[i][1] < 10000


 */
public class KClosestPoint {
    public int[][] kClosest(int[][] points, int K) {
         Arrays.sort(points, new Comparator<int[]>() {
             @Override
             public int compare(int[] o1, int[] o2) {
                 double d = (Math.pow(o1[0],2)+Math.pow(o1[1],2)) - (Math.pow(o2[0],2)+Math.pow(o2[1],2));
                 if (d == 0.0) {
                     return 0;
                 } else if (d > 0) {
                     return 1;
                 }
                 return -1;
             }
         });
         int[][] kClose = new int[K][2];
         for(int i = 0; i < K;i++) {
             kClose[i][0] = points[i][0];
             kClose[i][1] = points[i][1];
         }
         return kClose;
    }

    public static void main(String[] args) {
        int[][] intA = {{1,2},{-3,-4},{4,-1}};
        KClosestPoint kcp = new KClosestPoint();
        int[][] kClose = (kcp.kClosest(intA,2));
        for(int i = 0; i < kClose.length;i++) {
            System.out.print(kClose[i][0]);
            System.out.println(" "+kClose[i][1]);
        }
    }
}
