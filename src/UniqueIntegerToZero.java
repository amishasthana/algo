/*
1304. Find N Unique Integers Sum up to Zero

    User Accepted: 3060
    User Tried: 3135
    Total Accepted: 3170
    Total Submissions: 4042
    Difficulty: Easy

Given an integer n, return any array containing n unique integers such that they add up to 0.



Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].

Example 2:

Input: n = 3
Output: [-1,0,1]

Example 3:

Input: n = 1
Output: [0]



Constraints:

    1 <= n <= 1000


 */
public class UniqueIntegerToZero {
    public int[] sumZero(int n) {
        int[] newA = new int[n];
        int v = 1;
        int sIndex = 0; int eIndex = 0;
        if (n%2 == 0) {
            eIndex = n-1;
        } else {
            eIndex = n-2;
            newA[n-1] = 0;
        }
        while (sIndex < eIndex) {
            newA[sIndex++] = v;
            newA[eIndex--] = -1*v;
            v++;
        }
        return newA;
    }
}
