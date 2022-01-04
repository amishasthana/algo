package algoplan;
/*
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.



Example 1:

Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]



Constraints:

    2 <= n <= 105
    n - 1 <= connections.length <= 105
    0 <= ai, bi <= n - 1
    ai != bi
    There are no repeated connections.

We will try Trajans algorithm.

Baiscally if we can find SCC, we can say then go through all connection
and say if there is a connection acriss two lows that is critical connection.

LOW --> Lowest
Stack -->
visited.

WORKS --> BUT Out of memory.

 */

import java.util.*;
public class CriticalConnection {
    int[][] ADJ;
    int[] LOW;
    int[] DISC;
    BitSet VIS;
    BitSet OnStack;
    Stack<Integer> S;
    int time;


    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ADJ = new int[n][n];
        for (List<Integer> c : connections) {
            ADJ[c.get(0)][c.get(1)] = 1;
            ADJ[c.get(1)][c.get(0)] = 1;
        }
        Set<Integer> nLoop = new HashSet<>();
        LOW = new int[n];
        for (int i = 0; i < n; i++) LOW[i] = -1;
        DISC = new int[n];
        VIS = new BitSet(n);
        OnStack = new BitSet(n);
        S = new Stack<>();
        List<List<Integer>> ANS = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!VIS.get(i)) dfs(i,nLoop);
        }

        // There are two loops. if there is more then one link across them then its not an issue.
        // Otherwise critical section.
        Map<String,List<Integer>> mapOfCrossSCC = new HashMap<>();
        for (int i  = 0; i < connections.size();i++) {
            List<Integer> c = connections.get(i);
            int low1 = LOW[c.get(0)];
            int low2 = LOW[c.get(1)];
            //Basically if even one has lOW value not in loop.
            if (!(nLoop.contains(low1) && nLoop.contains(low2))) {
                ANS.add(c);
            } else if (low1 != low2){//two different scc.
                String key = Math.min(low1,low2)+"_"+Math.max(low1,low2);
                List<Integer> ll = mapOfCrossSCC.getOrDefault(key,new ArrayList<>(4));
                ll.add(c.get(0));
                ll.add(c.get(1));
                mapOfCrossSCC.put(key,ll);
                //ANS.add(new ArrayList<Integer>(Set.of(c.get(0),c.get(1))));
            }
        }
        for(List<Integer> list : mapOfCrossSCC.values()) {
            if (list.size() > 2) continue;
            ANS.add(list);
        }

        return ANS;

    }



    //i is not visited.
    public void dfs(int parent,Set<Integer> nLoop) {
        VIS.set(parent);
        S.push(parent);
        LOW[parent] = time;
        DISC[parent] = time;
        time++;
        OnStack.set(parent);
        for (int childI = 0; childI < ADJ.length; childI++) {
            if (ADJ[parent][childI] == 1) {
                if (!VIS.get(childI)) {
                    ADJ[parent][childI] = 0;
                    ADJ[childI][parent] = 0;
                    dfs(childI,nLoop);
                    LOW[parent] = Math.min(LOW[childI], LOW[parent]);
                } else  {//Loop this child allready visited. However its
                    if (OnStack.get(childI)) {
                        //Basically IS before the current parent. LOW should be fine.
                        LOW[parent] = Math.min(LOW[childI], LOW[parent]);
                    } else {
                        //case when its visited but not on stack. NOOPS
                    }
                }
            }
        }

        // ALL DFS. Time to start taking it out.
        // The one which goes on STACK but dont lead to loop will not have their low changed.
        if (LOW[parent] == DISC[parent]) {
            while (!S.isEmpty()) {
                int cV = S.pop();
                OnStack.flip(cV);
                if (LOW[cV] < DISC[cV]) nLoop.add(LOW[cV]);
                if (cV == parent) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        CriticalConnection cc = new CriticalConnection();
        //int[][] connections = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
        //int[][] connections = {{0, 1}};
        //int[][] connections = {{1,0},{2,0},{3,2},{4,2},{4,3},{3,0},{4,0}};
        // 6 [[1,3]]
        int[][] connections = {{0,1},{1,2},{2,0},{1,3},{3,4},{4,5},{5,3}};


        List<List<Integer>> conn = new ArrayList<>();
        for (int[] a : connections) {
            List<Integer> l = new ArrayList<>();
            l.add(a[0]);
            l.add(a[1]);
            conn.add(l);
        }
        List<List<Integer>>  ans = cc.criticalConnections(6, conn);
        for(List<Integer> l : ans) {
            System.out.println(l);
        }
    }
}//end




