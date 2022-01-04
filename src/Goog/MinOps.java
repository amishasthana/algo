package Goog;
/*
Given a positive integer n and 3 operations on n:

    n - 1
    n / 2 (if n is even)
    n / 3 (if n % 3 == 0)

Find the minimum number of above operations to reduce n to 1.

Example 1:

Input: n = 9
Output: 2
Explanation:
Step 1: 9 / 3 = 3
Step 2: 3 / 3 = 1

Example 2:

Input: n = 8
Output: 3
Explanation:
Step 1: 8 / 2 = 4
Step 2: 4 / 2 = 2
Step 3: 2 - 1 = 1

 */
public class MinOps {

    public int minOps(int i) {
        int minOps = 0;
        while ( i != 1) {
            if (i%2 == 0) {
               i =  i>>1; minOps++;
            } else if (i%3 == 0){
                i = i/3;  minOps++;
            } else {
                i = i-1; minOps++;
            }
        }
        return minOps;
    }

    public static void main(String[] args) {
        MinOps mo = new MinOps();
        System.out.println(mo.minOps(9));
    }

}
