import java.util.LinkedList;
import java.util.Queue;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

        Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

        Example 1:

        Input: 2, [[1,0]]
        Output: true
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0. So it is possible.

        Example 2:

        Input: 2, [[1,0],[0,1]]
        Output: false
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0, and to take course 0 you should
        also have finished course 1. So it is impossible.

        Note:

        The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
        You may assume that there are no duplicate edges in the input prerequisites.

Topological Sort -->
*/
public class TakeCourse {
    /* [1,0] means 1 is dependent upon 0. */

    //Map<Integer,Set<Integer>> depMap = new HashMap<>();
    //Ith row is saying what are the courses one can finish before going to i.
    //Ith column saying  I is a depndency for the ones blow.
    int[][] adjMatrix = null;
    boolean[] allreadProcessed = null;

    private void populateDep(int numCourses,int[][] preq) {
        adjMatrix = new int[numCourses][numCourses];
        for(int i = 0;i < preq.length;i++) {
            adjMatrix[preq[i][0]][preq[i][1]] =1;
        }
        allreadProcessed = new boolean[numCourses];
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if ((prerequisites == null) || (prerequisites.length == 0)) {
            return true;
        }
        //Map<Integer,Integer> depMap = new HashMap<>();
        populateDep(numCourses,prerequisites);
        Queue<Integer> queueOfNoDep = new LinkedList<>();
        int nextNoDepCourse = findNoDepCouse();
        if (nextNoDepCourse != -1) {
            queueOfNoDep.add(nextNoDepCourse);
        }
        while(!queueOfNoDep.isEmpty()){
            nextNoDepCourse = queueOfNoDep.poll();
            for(int j = 0; j < adjMatrix.length;j++) {
                adjMatrix[j][nextNoDepCourse] = 0;//Removing dependency.
            }
            allreadProcessed[nextNoDepCourse] = true;
            nextNoDepCourse = findNoDepCouse();
            if (nextNoDepCourse != -1) {
                queueOfNoDep.add(nextNoDepCourse);
            }
        }
        for(boolean nodep : allreadProcessed) {
            if (!nodep) return false;
        }
        return true;
    }

    private int findNoDepCouse() {
        int retValue = -1;
        for(int i = 0;i < adjMatrix.length;i++) {
            if(allreadProcessed[i]) continue;
            boolean isNoDep = true;
            for(int j = 0; j < adjMatrix.length;j++) {
                if (adjMatrix[i][j] != 0)  {
                    isNoDep = false; break;
                }
            }
            if (isNoDep) return i;
        }
        return retValue;
    }

    public static void main(String[] args) {
        //int[][] dep = {{0,1},{1,0}};
        //int[][] dep = {{1,0}};
        //int[][] dep = {{1,0},{1,3},{2,3},{3,4},{4,2}};
        int[][] dep = {{1,0},{1,3},{2,3},{3,4}};
        TakeCourse tc = new TakeCourse();
        System.out.println(tc.canFinish(5,dep));
    }
}
