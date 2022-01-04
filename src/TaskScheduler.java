import java.util.Arrays;

/*
621. Task Scheduler
Medium

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.



Note:

    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].


 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;
        /*
        The basic idea is that lets say max Freq is 4.
        There will be one column of 4.
         So there will be atleast : (4-1)*(n+1)
        the top row, how much will fill depend upon, how many have that the max freq.
         That is (25-i)

         */

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();
        char[] iA = {'A','A','A','B','B','B'};
        System.out.println(" iA "+ts.leastInterval(iA,2));
    }
}
