/*
1317. Convert Integer to the Sum of Two No-Zero Integers

    User Accepted: 3510
    User Tried: 3798
    Total Accepted: 3584
    Total Submissions: 6251
    Difficulty: Easy

Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.

Return a list of two integers [A, B] where:

    A and B are No-Zero integers.
    A + B = n

It's guarateed that there is at least one valid solution. If there are many valid solutions you can return any of them.


 */
public class NonZeroIntegerSum {
    public int[] getNoZeroIntegers(int n) {
           int f = 1; int s = n-1;
           while (!valid(f) || !valid(s)) {
               f++;s--;
           }
           int[] ret = {f,s};
           return ret;
    }

    private boolean valid(int n) {
        while (n > 0) {
            if (n%10 == 0) return false;
            n = n/10;
        }
        return true;
    }

}
