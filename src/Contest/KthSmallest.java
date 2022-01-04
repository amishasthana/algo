package Contest;

/*
1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows

    User Accepted: 615
    User Tried: 1078
    Total Accepted: 654
    Total Submissions: 2050
    Difficulty: Hard

You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.

You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.



Example 1:

Input: mat = [[1,3,11],[2,4,6]], k = 5
Output: 7
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.

Example 2:

Input: mat = [[1,3,11],[2,4,6]], k = 9
Output: 17

Example 3:

Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
Output: 9
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.

Example 4:

Input: mat = [[1,1,10],[2,2,9]], k = 7
Output: 12



Constraints:

    m == mat.length
    n == mat.length[i]
    1 <= m, n <= 40
    1 <= k <= min(200, n ^ m)
    1 <= mat[i][j] <= 5000
    mat[i] is a non decreasing array.



 */

import java.util.Collections;
import java.util.PriorityQueue;

class Element implements  Comparable<Element> {
     int i;
     int v;
     public Element(int ii,int vv) {
         this.i = ii;
         this.v = vv;
     }

     @Override
     public int compareTo(Element e) {
         return this.v - e.v;
     }
}

public class KthSmallest {


    public int kthSmallest(int[][] mat, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        pq.add(0);
        for (int[] row: mat){
            PriorityQueue<Integer> next=new PriorityQueue<>(Collections.reverseOrder());
            for (int prev: pq) {
                for (int cur : row) {
                    next.add(prev + cur);
                }
            }
            while (next.size()>k) next.poll();
            pq=next;
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        int[][] m = {{1,10,10},{1,4,5},{2,3,6}};
        KthSmallest kSmall = new KthSmallest();
        System.out.println(" kSmal "+kSmall.kthSmallest(m,7));
    }
}
