import java.util.*;

/*Given number M and N intervals in the form [a, b] (inclusive) where for every interval -M <= a <= b <= M, create a program that returns a point where the maximum number of intervals overlap.

        Example:

        M: 10
        N: 4
        Intervals:
        [-3, 5]
        [0, 2]
        [8, 10]
        [6, 7]

        A correct answer would be either 0 ,1 or 2 since those points are found where 2 intervals overlap and 2 is the maximum number of overlapping intervals.
        */
public class OverlapInterval {

        /*
             2*N space
             Complexity : N + M
         */
        public void printMaxDigitInOverlap(int m,int[][] interval) {
             if (!validate(m,interval)) {
                 System.out.println("Not correct input");
            }
            Map<Integer,Integer> mStart = new HashMap<>();
            Map<Integer,Integer> mEnd = new HashMap<>();

            for(int i = 0; i < interval.length;i++) {
                int start = interval[i][0];
                int cLower = getSafeValue(mStart,start);
                mStart.put(start,1+cLower);
                int end = interval[i][1];
                int cHigher = getSafeValue(mEnd,end);
                if ((end+1) <= m) {
                    mEnd.put(end + 1, 1 + cHigher);
                }
            }
            int maxInteger = -m;
            int maxRepeat = 0;
            int cRepeat = 0;
            for(int i = -m; i <= m;i++) {
                cRepeat += getSafeValue(mStart,i);
                cRepeat -= getSafeValue(mEnd,i);
                if (cRepeat > maxRepeat) {
                    maxRepeat = cRepeat;
                    maxInteger = i;
                }
            }
            System.out.println("Maximum repeat integer is "+maxInteger+" and repeated "+maxRepeat);
        }

        private int getSafeValue(Map<Integer,Integer> map,int i) {
            return (map.get(i) == null)?0:map.get(i).intValue();
        }

        private boolean validate(int m,int[][] interval) {
            return true;
        }

        class Interval implements Comparable<Interval>{
            int pos;
            int type; // -1 end and 0 is begin.

            @Override
            public int compareTo(Interval i) {
                if (this.pos < i.pos) {
                    return -1;
                } else if (this.pos > i.pos) {
                    return 1;
                } else if (this.type < i.type) {
                    return 1;
                } else if (this.type > i.type) {
                    return -1;
                }
                return 0;
            }
        }

        /*
           Sort way.
           Basically create a class Interval and sort.
           Then go through the min Heap and figure out the delta. Use priority queue.
           N + log2N ->Complexity
           2n Objects....
         */

        public void printMaxDigitInOverlapUsingHeap(int m,int[][] interval) {
            if (!validate(m,interval)) {
                System.out.println("Not correct input");
            }
            PriorityQueue<Interval> pQueue = new PriorityQueue<>();
            for(int i = 0; i < interval.length;i++) {
                Interval inv1 = new Interval();
                inv1.pos = interval[i][0];  inv1.type = 0;
                Interval inv2 = new Interval();
                inv2.pos = interval[i][1];  inv2.type = -1;
                pQueue.add(inv1); pQueue.add(inv2);
            }
            int maxInteger = -8;
            int maxRepeat = 0;
            int cRepeat = 0;
            while(!pQueue.isEmpty()) {
                Interval inv = pQueue.poll();

                if (inv.type == -1) {
                    cRepeat--;
                } else {
                    cRepeat++;
                }

                if (cRepeat > maxRepeat) {
                    maxRepeat = cRepeat;
                    maxInteger = inv.pos;
                }
            }
            System.out.println("using heap Maximum repeat integer is "+maxInteger+" and repeated "+maxRepeat);

        }

        public static void main(String[] args) {
            OverlapInterval overLap = new OverlapInterval();
            int[][] fInt = {{-10,10},{10,10}};
            overLap.printMaxDigitInOverlap(10,fInt);
            overLap.printMaxDigitInOverlapUsingHeap(10,fInt);
        }

    class Solution {
        public int numUniqueEmails(String[] emails) {
            Set<String> uniqueSet = new HashSet<>();
            if ((emails == null) || (emails.length == 0)) {
                return 0;
            }
            for(String s : emails) {
                String[] strA = s.split("@");
                StringBuilder strB = new StringBuilder(strA[1]);
                for(char c : strA[0].toCharArray()) {
                    if (c == '.') continue;
                    if (c == '+') break;
                    strB.append(c);
                }
                uniqueSet.add(strA.toString());
            }//End of for
            return uniqueSet.size();
        }
    }
}


