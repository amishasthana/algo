package Goog;
/*
An integer is said to be self-descriptive if it has the property that, when digit positions are labeled 0 to N-1, the digit in each position is equal to the number of times that this digit appears in the number. Write a function that will check whether a given positive integer is self-descriptive.

Example 1:

Input: 2020
Output: true
Explanation:
Position 0 has value 2 and there are two 0s in the number
Position 1 has value 0 and there are no 1s in the number
Position 2 has value 2 and there are two 2s
Position 3 has value 0 and there are zero 3s

Example 2:

Input: 3211000
Output: true
Explanation:
Position 0 has value 3 and there are three 0s in the number
Position 1 has value 2 and there are two 1s in the number
Position 2 has value 1 and there is one 2 in the number
Position 3 has value 1 and there is one 3 in the number
Position 4 has value 0 and there are zero 4s
Position 5 has value 0 and there are zero 5s
Position 6 has value 0 and there are zero 6s

Follow-up:
Generate all self-descriptive numbers that will fit in a 32-bit integer. There are 5 such integers:

1210
2020
21200
3211000
42101000

 */
public class SelfDescriptiveNum {
    public boolean selfDesc(int num) {
        String s = num+"";
        //Map<Integer,Integer> fMap = new HashMap<>();
        int[] fA = new int[10];
        for(char c : s.toCharArray()) {
            fA[c-'0']++;
        }
        for(int i = 0; i < s.length();i++) {
           if ( ((int)(s.charAt(i) - '0')) !=  fA[i] ) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SelfDescriptiveNum sdn = new SelfDescriptiveNum();
        System.out.println(sdn.selfDesc(3211000));
        System.out.println(sdn.selfDesc(1210));
        System.out.println(sdn.selfDesc(234452));
    }
}
