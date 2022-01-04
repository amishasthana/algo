package Contest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
1649. Create Sorted Array through Instructions

    User Accepted: 340
    User Tried: 769
    Total Accepted: 368
    Total Submissions: 1573
    Difficulty: Hard

Given an integer array instructions, you are asked to create a sorted array from the elements in instructions. You start with an empty container nums. For each element from left to right in instructions, insert it into nums. The cost of each insertion is the minimum of the following:

    The number of elements currently in nums that are strictly less than instructions[i].
    The number of elements currently in nums that are strictly greater than instructions[i].

For example, if inserting element 3 into nums = [1,2,3,5], the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3, element 5 is greater than 3) and nums will become [1,2,3,3,5].

Return the total cost to
Output: 1
Explanation: Begin with nums = [].
Insert 1 with cost min(0, 0) = 0, now nums = [1].
Insert 5 with cost min(1, 0) = 0, now nums = [1,5].
Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].
Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].
The total cost is 0 + 0 + 0 + 1 = 1.

Example 2:

Input: instructions = [1,2,3,6,5,4]
Output: 3
Explanation: Begin with nums = [].
Insert 1 with cost min(0, 0) = 0, now nums = [1].
Insert 2 with cost min(1, 0) = 0, now nums = [1,2].
Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].
Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].
Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].
Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].
The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.

Example 3:

Input: instructions = [1,3,3,3,2,4,2,1,2]
Output: 4
Explanation: Begin with nums = [].
Insert 1 with cost min(0, 0) = 0, now nums = [1].
Insert 3 with cost min(1, 0) = 0, now nums = [1,3].
Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].
Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].
Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].
Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].
​​​​​​​Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].
​​​​​​​Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].
​​​​​​​Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].
The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.



Constraints:

    1 <= instructions.length <= 105
    1 <= instructions[i] <= 105


 */
public class InstructionSortedArray {
    public int createSortedArray(int[] instructions) {
        long T = 0l;
        TreeSet<Integer> tSet = new TreeSet<>();
        Map<Integer,Integer> hMap = new HashMap<>();
        //tSet.add(instructions[0]);

        for(int i = 0;i < instructions.length;i++) {
            int c = instructions[i];
            Set<Integer> lSet = tSet.headSet(c);
            Set<Integer> rSet = tSet.tailSet(c,false);
            int low = 0; int high = 0;
            for(int ii : lSet) {
                low += hMap.getOrDefault(ii,0);
            }
            for(int ii : rSet) {
                high += hMap.getOrDefault(ii,0);
            }
            T += Math.min(low,high);
            tSet.add(c);
            hMap.put(c,hMap.getOrDefault(c,0)+1);
        }
        T = T % 1000000007;
        return (int) T;
    }

    public static  void main(String[] args) {
        InstructionSortedArray is = new InstructionSortedArray();
        int[] iA = {1,3,3,3,2,4,2,1,2};
        System.out.println(is.createSortedArray(iA));
    }
}
