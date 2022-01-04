import java.util.*;
import java.util.function.Supplier;

/*
480. Sliding Window Median
Hard

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
Examples:

[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6

Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
Answers within 10^-5 of the actual value will be accepted as correct.

 */
public class SlidingWindowMedian {
    /*
Two TreeSet. Basically use as heap.
You add to them.
Once you have the next element you remove from the tree that element.
removal from TreeSet is logK

*/

    public double[] AmedianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> right = new TreeSet<>(comparator);

        Supplier<Double> median = (k % 2 == 0) ?
                () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
                () -> (double) nums[right.first()];

        // balance lefts size and rights size (if not equal then right will be larger by one)
        Runnable balance = () -> { while (left.size() > right.size()) right.add(left.pollFirst()); };

        double[] result = new double[nums.length - k + 1];

        //for (int i = 0; i < k; i++) left.add(i);
        balance.run(); result[0] = median.get();

        for (int i = k, r = 1; i < nums.length; i++, r++) {
            // remove tail of window from either left or right
            if(!left.remove(i - k)) right.remove(i - k);

            // add next num, this will always increase left size
            right.add(i); left.add(right.pollFirst());

            // rebalance left and right, then get median from them
            balance.run(); result[r] = median.get();
        }

        return result;
    }

    class VAndIndex implements  Comparable<VAndIndex> {
        int index;
        double v;
        public VAndIndex(int i,double val) {
            index = i;
            v = val;
        }

        @Override
        public int compareTo(VAndIndex v) {
            double vv = (this.v == v.v)?(this.index - v.index):(this.v - v.v);
            if (vv == 0) return 0;
            if (vv < 0) return -1;
            return 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            VAndIndex v = (VAndIndex)o;
            return (compareTo(v) == 0);
        }

    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if ((nums == null) || (nums.length == 0) || (k > nums.length) )  return null;
        int len = nums.length;

       //Comparator<VAndIndex> comp = (i, j)->(i.v == j.v)?(i.index-j.index):(i.v - j.v);
        //Comparator<Integer> comp = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;

        TreeSet<VAndIndex> leftSet = new TreeSet<>();
        TreeSet<VAndIndex> rightSet = new TreeSet<>();
        Map<Integer,VAndIndex> setElem = new HashMap<>();

        double[] mCal  = new double[len-k+1];// 5 - 2 + 1 = 4 --> i = k-1
        for(int i = 0; i < k; i++) {
            setElem.put(i,new VAndIndex(i,nums[i]));
            pop(leftSet,rightSet,setElem.get(i));
        }
        mCal[0] = getMedian(leftSet,rightSet);
        for(int i = 1; i < mCal.length; i++) {
            removeElem(leftSet,rightSet,i-1,setElem);
            int newElemIndex = i+k-1;
            setElem.put(newElemIndex,new VAndIndex(newElemIndex,nums[newElemIndex]));
            pop(leftSet,rightSet,setElem.get(newElemIndex));
            mCal[i] = getMedian(leftSet,rightSet);
        }

        return mCal;

    }// End of method.

    private double getMedian(TreeSet<VAndIndex> leftSet,TreeSet<VAndIndex> rightSet) {
        double med = 0.0;
        if (leftSet.size() > rightSet.size() ) {
            med = leftSet.first().v;
        } else {
            med = (leftSet.first().v + rightSet.last().v ) / 2.0;
        }
        return med;
    }

    private void pop(TreeSet<VAndIndex> leftSet,TreeSet<VAndIndex> rightSet ,VAndIndex elem) {
        leftSet.add(elem);
        rightSet.add(leftSet.pollFirst());
        if (rightSet.size() > leftSet.size() ) {
            leftSet.add(rightSet.pollLast());
        }
    }

    private void removeElem(TreeSet<VAndIndex> leftSet,TreeSet<VAndIndex> rightSet ,int index,Map<Integer,VAndIndex> setElem) {
        if (!leftSet.remove(setElem.get(index)) ) rightSet.remove(setElem.get(index));
    }

    public static void main(String[] args) {
        int[] a = {2147483647,2147483647};


        SlidingWindowMedian swm = new SlidingWindowMedian();
        System.out.println(Arrays.toString(swm.medianSlidingWindow(a,2)));
    }


}
