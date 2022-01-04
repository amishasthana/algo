package Goog;

import java.util.Arrays;

/*
Given an array of positive integers (possibly with duplicates) such that the numbers have been sorted only by 28 most significant bits. Sort the array completely.

Example 1:

Input: [0, 15, 12, 17, 18, 19, 33, 32]
Output: [0, 12, 15, 17, 18, 19, 32, 33]
Explanation:
The integers in their binary representation are:
 0 = 0000 0000 0000 0000 0000 0000 0000 0000
15 = 0000 0000 0000 0000 0000 0000 0000 1111
12 = 0000 0000 0000 0000 0000 0000 0000 1100
17 = 0000 0000 0000 0000 0000 0000 0001 0001
18 = 0000 0000 0000 0000 0000 0000 0001 0010
19 = 0000 0000 0000 0000 0000 0000 0001 0011
33 = 0000 0000 0000 0000 0000 0000 0010 0001
32 = 0000 0000 0000 0000 0000 0000 0010 0000

In sorted order:
 0 = 0000 0000 0000 0000 0000 0000 0000 0000
12 = 0000 0000 0000 0000 0000 0000 0000 1100
15 = 0000 0000 0000 0000 0000 0000 0000 1111
17 = 0000 0000 0000 0000 0000 0000 0001 0001
18 = 0000 0000 0000 0000 0000 0000 0001 0010
19 = 0000 0000 0000 0000 0000 0000 0001 0011
32 = 0000 0000 0000 0000 0000 0000 0010 0000
33 = 0000 0000 0000 0000 0000 0000 0010 0001

Example 2:

Input: [100207, 100205, 100204, 100206, 100203]
Output: [100203, 100204, 100205, 100206, 100207]
Explanation:
The integers in their binary representation are:
100207 = 0000 0000 0000 0001 1000 0111 0110 1111
100205 = 0000 0000 0000 0001 1000 0111 0110 1101
100204 = 0000 0000 0000 0001 1000 0111 0110 1100
100206 = 0000 0000 0000 0001 1000 0111 0110 1110
100203 = 0000 0000 0000 0001 1000 0111 0110 1011

In sorted order:
100203 = 0000 0000 0000 0001 1000 0111 0110 1011
100204 = 0000 0000 0000 0001 1000 0111 0110 1100
100205 = 0000 0000 0000 0001 1000 0111 0110 1101
100206 = 0000 0000 0000 0001 1000 0111 0110 1110
100207 = 0000 0000 0000 0001 1000 0111 0110 1111

 */
public class SortArrayPartiallySorted {
    public int[] sortArray(int[] A) {
        int len = A.length;
        String[] B1 = new String[len];
        String[] B2 = new String[len];
        int index = 0;
        for(int i : A) {
            String cs = Integer.toBinaryString(i);
            if (cs.length() < 32) {
                StringBuilder strB = new StringBuilder();
                for(int ii = 32; ii > cs.length();ii--) strB.append("0");
                strB.append(cs);
                cs = strB.toString();
                B1[index] = cs.substring(0,28);
                B2[index++] = cs.substring(28,32);
            }
        }
        int[] res = new int[A.length];
        int start = -1; int end = -1;
        for(int i = 0; i < len; i++) {
            if ((i < (len-1)) && B1[i].equals(B1[i+1])) {
                if (start == -1) start = i;
                end = i+1;
            } else {
                if (start != -1) {
                    sortAndFill(res, B1, B2, start, end);
                    start = -1; end = -1;
                    continue;
                }

               res[i] = Integer.parseInt(B1[i]+B2[i],2);
            }

        }
        return res;

    }

    private void sortAndFill(int[] res,String[] B1,String[] B2,int start,int end) {
        String[] BB = new String[end-start+1];
        for(int i = start; i <= end;i++) {
            BB[i-start] = B1[i]+B2[i];
        }
        Arrays.sort(BB);
        for(int i = start; i <= end;i++) {
            res[i] = Integer.parseInt(BB[i-start],2);
        }
    }

    public static void main(String[] args) {
        SortArrayPartiallySorted sp = new SortArrayPartiallySorted();
        int[] A = {0, 15, 12, 17, 18, 19, 33, 32};
        int[] B = sp.sortArray(A);
        System.out.println(Arrays.toString(B));
    }

}
