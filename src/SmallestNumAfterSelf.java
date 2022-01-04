import java.util.*;

/*
315. Count of Smaller Numbers After Self
Hard

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

 */
public class SmallestNumAfterSelf {
    class NumToIndex {
        int i;
        int v;
        int lowest;

        public NumToIndex(int index, int value) {
            i = index;
            v = value;
        }

    }//End of class.

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> retList = new LinkedList<>();
        if ((nums == null) || (nums.length == 0)) return retList;
        Comparator<NumToIndex> comp = (a, b) -> (a.v - b.v);
        TreeSet<NumToIndex> tSet = new TreeSet<>(comp);
        for (int i = (nums.length - 1); i >= 0; i--) {
            NumToIndex cNum = new NumToIndex(i, nums[i]);
            NumToIndex floor = tSet.floor(cNum);
            cNum.lowest = (floor == null) ? 0 : (floor.lowest + 1);
            tSet.add(cNum);
            retList.add(cNum.lowest);
        }// End of for
        Collections.reverse(retList);
        return retList;
    }

    public static void main(String[] args) {
        int[] iA = {5, 2, 6, 1};
        SmallestNumAfterSelf sm = new SmallestNumAfterSelf();
        List<Integer> list = sm.countSmaller(iA);
        list.forEach(x->System.out.print(x+" "));
    }
}