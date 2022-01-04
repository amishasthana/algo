import java.util.Comparator;
import java.util.PriorityQueue;

/*
95. Find Median from Data Stream
Hard

Median is the middle value in an ordered integer list.
 If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
For example,

[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.



Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2



Follow up:

    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?


 */
public class MedianStream {
    final PriorityQueue<Integer> pMin;
    final PriorityQueue<Integer> pMax;


    /** initialize your data structure here. */
    public MedianStream() {
        pMin = new PriorityQueue<>();
        pMax = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer x1, Integer x2) {
                return x2.intValue() - x1.intValue();
            }
        });
    }

    public void addNum(int num) {
        Integer highElem = pMin.poll();
        Integer lowElem = pMax.poll();
        if ((highElem == null) && (lowElem == null)) {
            pMax.add(num);
            return;
        } else if (highElem == null) {
            if (num >= lowElem.intValue()) {
                pMin.add(num);
                pMax.add(lowElem);
            } else {
                pMin.add(lowElem);
                pMax.add(num);
            }
            return;
        } else if (lowElem == null) {
            if (num >= highElem.intValue()) {
                pMax.add(highElem);
                pMin.add(num);
            } else {
                pMax.add(num);
                pMin.add(highElem);
            }
            return;
        }
        int lSize = pMax.size();
        int rSize = pMin.size();
        if (lSize >= rSize) {
            pMin.add(highElem);
            if(num >= lowElem) {
                pMin.add(num);
                pMax.add(lowElem);
            } else {
                pMin.add(lowElem);
                pMax.add(num);
            }
        } else {
            pMax.add(lowElem);
            if(num >= highElem) {
                pMax.add(highElem);
                pMin.add(num);
            } else {
                pMax.add(num);
                pMin.add(highElem);
            }
        }
    }

    public double findMedian() {
        if (pMin.size() > pMax.size()) {
            return pMin.peek();
        } else if (pMin.size() < pMax.size()) {
            return pMax.peek();
        } else {
            return ((pMin.peek()+pMax.peek())/2.0);
        }
    }
}
