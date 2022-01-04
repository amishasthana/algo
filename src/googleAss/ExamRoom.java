package googleAss;

import java.util.PriorityQueue;
import java.util.TreeSet;

/*
855. Exam Room
Medium

In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.



Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
1 2 3 4 5 6 7 8 9 10

seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.

​​​​​​​

Note:

    1 <= N <= 10^9
    ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
    Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.


 */
public class ExamRoom {

    class Interval implements Comparable<Interval> {
        int s; int e; int d;
        boolean valid = true;
        public Interval(int st,int ed) {
            s = st; e = ed;d = (e - s);
        }

        public int compareTo(Interval i) {
            if ( Math.abs(this.d - i.d) <= 1) {
                return -1*(this.s - i.s);
            }
            return -1*(this.d - i.d);
        }
    }

    PriorityQueue<Interval> PQ;
    TreeSet<Integer> exist;
    int N;

    public ExamRoom(int N) {
        PQ = new PriorityQueue<>();
        exist = new TreeSet<>();
        this.N = N;
    }

    private void createQ(int s,int e) {
        Interval i = new Interval(s,e);
        PQ.add(i);
    }

    public int seat() {
        int ret  = 0;
        boolean found = false;
        while (!PQ.isEmpty()) {
            Interval i = PQ.poll();
            if (exist.contains(i.s) && exist.contains(i.e)) {//valid PQ found
                ret = i.s+ (i.e - i.s) / 2;
                found = true;
                Interval i1 = new Interval(i.s, ret);
                Interval i2 = new Interval(ret,i.e);
                PQ.add(i1);
                PQ.add(i2);
                exist.add(ret);
                break;
            }
        }
        if (!found) {
            if (exist.isEmpty()) {
                exist.add(0);
                return ret;
            }
            PQ.add(new Interval(0, N - 1));
            if (exist.contains(N-1) ) {
                exist.add(0);
            } else {
                exist.add(N-1);
                ret = N-1;
            }
        }

        return ret;
        }

        public void leave(int p) {

            Integer min = exist.lower(p);
            Integer max = exist.higher(p);
            if ( (min != null) && (max != null)) {
                PQ.add(new Interval(min,max));
            }
            exist.remove(p);
        }

        public static void main(String[] args) {
            ExamRoom em = new ExamRoom(10);
            em.seat();
            em.seat();
            em.seat();
            em.seat();
            em.leave(1);
            em.leave(3);
            System.out.println(em.seat());
        }

    }
