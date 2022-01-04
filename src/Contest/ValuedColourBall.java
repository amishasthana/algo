package Contest;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return - Integer.compare(o1, o2);
    }
});

1648. Sell Diminishing-Valued Colored Balls

    User Accepted: 607
    User Tried: 1653
    Total Accepted: 627
    Total Submissions: 3605
    Difficulty: Medium

You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.

The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).

You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own. You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.

Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.



Example 1:

Input: inventory = [2,5], orders = 4
Output: 14
Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
The maximum total value is 2 + 5 + 4 + 3 = 14.

Example 2:

Input: inventory = [3,5], orders = 6
Output: 19
Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.

Example 3:

Input: inventory = [2,8,4,10,6], orders = 20
Output: 110

Example 4:

Input: inventory = [1000000000], orders = 1000000000
Output: 21
Explanation: Sell the 1st color 1000000000 times for a total value of 500000000500000000. 500000000500000000 modulo 109 + 7 = 21.



Constraints:

    1 <= inventory.length <= 105
    1 <= inventory[i] <= 109
    1 <= orders <= min(sum(inventory[i]), 109)


 */
public class ValuedColourBall {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return - Integer.compare(o1, o2);
        }
    });

    public int maxProfit(int[] inventory, int orders) {
        long ret = 0;
        for(int i : inventory) {
            maxHeap.add(i);
        }
        while (orders > 0) {
            int c = maxHeap.poll();
            if ( c  >= orders) {
                ret += findSum(c,orders);
                orders = 0;// all done.
            } else {
                ret += findSum(c,0);
                orders -= c;
            }
            ret = ret % 1000000007;
        }

        return (int)ret;
    }

    /*
    public int maxProfit(int[] inventory, int orders) {
        long ret = 0;
        for(int i : inventory) {
            maxHeap.add(i);
        }
        while (orders-- > 0) {
            int c = maxHeap.poll();
            ret += c--;
            int cNext = 0;
            if ( !maxHeap.isEmpty()) {
                cNext  = maxHeap.peek();
            }
            int delta = (c - cNext);
            if (delta >= 0) {
                delta = Math.min(delta,orders);
                ret += findSum(c,delta);
                c -= delta;
                orders -= delta;
            }
            maxHeap.add(c);
            ret = ret % 1000000007;
        }

        return (int)ret;
    }
     */

    private long findSum(long c, long d) {
        long sum1 = (c*(c+1))/2l;
        long sum2 = ((c-d)*(c-d+1))/2l;
        long sum3 = sum1 - sum2;
        return sum3;
    }

    public static  void main(String[] args) {
        int[] inventory = {497978859,167261111,483575207,591815159};
        int orders = 836556809;
        ValuedColourBall vcb = new ValuedColourBall();
        System.out.println(vcb.maxProfit(inventory,orders));
        /*
        [497978859,167261111,483575207,591815159]
836556809


         */
    }
}
