/*
1299. Replace Elements with Greatest Element on Right Side

    User Accepted: 1441
    User Tried: 1495
    Total Accepted: 1475
    Total Submissions: 1965
    Difficulty: Easy

Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.



Example 1:

Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]



Constraints:

    1 <= arr.length <= 10^4
    1 <= arr[i] <= 10^5


 */
public class ReplaceElement {
    public int[] replaceElements(int[] arr) {
        int gSoFar = -1;
       for(int i = arr.length-1; i >= 0;i--) {
           int temp = arr[i];
           arr[i] = gSoFar;
           gSoFar = Math.max(gSoFar,temp);
       }
       return arr;
    }
}
