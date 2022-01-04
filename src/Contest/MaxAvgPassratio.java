package Contest;
/*
1792. Maximum Average Pass Ratio
1792. Maximum Average Pass Ratio

    User Accepted: 1586
    User Tried: 2915
    Total Accepted: 1656
    Total Submissions: 5554
    Difficulty: Medium

There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.



Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.

Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485



Constraints:

    1 <= classes.length <= 105
    classes[i].length == 2
    1 <= passi <= totali <= 105
    1 <= extraStudents <= 105


 */
import java.util.*;
public class MaxAvgPassratio {
    class Fraction {
        double n; double d;int i;
        public Fraction(double n,double d,int i) {
            this.n = n;
            this.d = d;
            this.i = i;
        }
    }
    public double maxAverageRatioTwo(int[][] classes, int extraStudents) {
        PriorityQueue<Fraction> P = new PriorityQueue<>((x,y)->
                (Double.compare(((x.n/x.d) - (x.n+1)/(x.d+1)),((y.n/y.d) - (y.n+1)/(y.d+1)))));
        int len = classes.length;
        double ret = 0;
        for(int i = 0; i < len;i++) {
            int d = classes[i][1];
            if (d == classes[i][0]) {ret++;continue;}
            Fraction f = new Fraction(classes[i][0],classes[i][1],i);
            P.add(f);
        }
        if (P.isEmpty()) return 1;//safety and opti
        while (extraStudents-- >0) {
            Fraction f = P.poll();
            f.d++;f.n++;
            P.add(f);
        }
        while(!P.isEmpty()) {
            Fraction f = P.poll();
            ret += f.n/f.d;
        }
        return ret/len;
    }
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Integer>  PQ = new PriorityQueue<>();
        Map<Integer,List<Integer>>  M = new HashMap<>();//deno -> key, indexes
        int len = classes.length;
        for(int i = 0; i < len;i++) {
            int d = classes[i][1];
            if (d == classes[i][0]) continue;
            List<Integer> l = M.getOrDefault(d,new LinkedList<>());
            if (l.size() == 0) PQ.add(d);
            l.add(i);
            M.put(d,l);
        }
        while ( !PQ.isEmpty() && (extraStudents > 0)) {
            Integer i = PQ.poll();
            List<Integer> ll = M.get(i);
            for( int ii : ll) {
                int n  = classes[ii][0];
                int delta = i-n;
                if ( extraStudents >= delta) {
                    extraStudents -= delta;
                    classes[ii][0] = i;
                } else {
                    classes[ii][0] = delta+extraStudents;
                    extraStudents = 0;
                }
                if (extraStudents <= 0) break;
            }

        }// end of while
        double ret  = 0.0;
        for(int i = 0; i < len;i++) {
            ret += (classes[i][0]*1.0) /(classes[i][1]*1.0);
        }

        return ret/len;

    }

    public static void main(String[] args) {
           int[][] A = {{1,2},{3,5},{2,2}};
        MaxAvgPassratio ma = new MaxAvgPassratio();
         System.out.println(ma.maxAverageRatioTwo(A,2));
         double d = ( 6.0/8.0 + 3.0/9.0 + 4.0/5.0 + 1/5.0)/4.0;
        System.out.println(d);
    }
}
