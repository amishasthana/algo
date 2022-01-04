import java.util.*;

/*

174th contest
Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.



Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.

Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.

Example 3:

Input: arr = [1,9]
Output: 1

Example 4:

Input: arr = [1000,1000,3,7]
Output: 1

Example 5:

Input: arr = [1,2,3,4,5,6,7,8,9,10]
Output: 5


*/
public class ReduceArraySizeHalf {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {

            return -Integer.compare(o1,o2);
        }
    });

    public int minSetSize(int[] arr) {
        Map<Integer,Integer> freqMap = new HashMap<>();
        for(int i =0;i< arr.length;i++) {
            int cElem = arr[i];
            Integer val = freqMap.get(cElem);
            if (val == null) {
                val = 0;
            }
            freqMap.put(cElem,val+1);
        }
        for(Integer vals : freqMap.values()) {
            maxHeap.add(vals);
        }
        int total = 0;
        int index = 0;
        while (total < arr.length/2) {
            total += maxHeap.poll();
            index++;
        }
        return index;
    }

    public static void main(String[] args) {
        int[] mat = {1000,1000,1,2};
        ReduceArraySizeHalf ra = new ReduceArraySizeHalf();
        ra.minSetSize(mat);
    }
}
