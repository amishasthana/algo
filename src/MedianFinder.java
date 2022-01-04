import java.util.PriorityQueue;
import java.util.Queue;

/*
https://leetcode.com/problems/find-median-from-data-stream/discuss/74062/Short-simple-JavaC%2B%2BPython-O(log-n)-%2B-O(1)
Rocks :)
 */
public class MedianFinder {
    final Queue<Integer> pMin;// Lower elem
    final Queue<Integer> pMax;//Higher elem


    /** initialize your data structure here. */
    public MedianFinder() {
        pMin = new PriorityQueue<>((a,b)->(b-a));
        pMax = new PriorityQueue<>();
    }

    //pMax is always equal or greater
    public void addNum(int num) {
         pMax.add(num);
         pMin.add(pMax.poll());
         if (pMax.size() < pMin.size()) {
             pMax.add(pMin.poll());
         }
    }

    public double findMedian() {
        if  (pMax.size() == pMin.size()) {
            return ((pMax.peek()+pMin.peek())/2.0);
        }
        return pMax.peek();
    }

}
