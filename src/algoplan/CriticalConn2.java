package algoplan;

import java.util.*;

public class CriticalConn2 {
    List<Integer>[] ADJ;
    int[] LOW;
    int[] DISC;
    BitSet VIS;
    BitSet OnStack;
    Stack<Integer> S;
    int time;
    List<List<Integer>> ANS = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ADJ = new ArrayList[n];
        for(int i = 0; i < n;i++) {
            ADJ[i] = new ArrayList<>();
        }
        for (List<Integer> c : connections) {
            int f = c.get(0); int s = c.get(1);
            ADJ[f].add(s);
            ADJ[s].add(f);
        }
        Set<Integer> nLoop = new HashSet<>();
        LOW = new int[n];
        for (int i = 0; i < n; i++) LOW[i] = -1;
        DISC = new int[n];
        VIS = new BitSet(n);
        OnStack = new BitSet(n);
        S = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!VIS.get(i)) dfs(i,nLoop,i);
        }

        /*
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

         */

        return ANS;

    }



    //i is not visited.
    public void dfs(int parent,Set<Integer> nLoop,int par) {
        VIS.set(parent);
        S.push(parent);
        LOW[parent] = time;
        DISC[parent] = time;
        time++;
        OnStack.set(parent);
        for (int childInd = 0; childInd < ADJ[parent].size(); childInd++) {
            int childI = ADJ[parent].get(childInd);
            if (childI == par) continue; //ignore edge back to parent through which we just came.
            if (!VIS.get(childI)) {
                dfs(childI,nLoop,parent);
                LOW[parent] = Math.min(LOW[childI], LOW[parent]);
                if (LOW[childI] > DISC[parent]) {
                    // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                    ANS.add(Arrays.asList(childI, parent));
                }
            } else  {//Loop this child allready visited. However its
                if (OnStack.get(childI)) {
                    //Basically IS before the current parent. LOW should be fine.
                    LOW[parent] = Math.min(LOW[childI], LOW[parent]);
                } else {
                    //case when its visited but not on stack. NOOPS
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
        CriticalConn2 cc = new CriticalConn2();
        int[][] connections = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
        //int[][] connections = {{0, 1}};
        //int[][] connections = {{1,0},{2,0},{3,2},{4,2},{4,3},{3,0},{4,0}};
        // 6 [[1,3]]
        //int[][] connections = {{0,1},{1,2},{2,0},{1,3},{3,4},{4,5},{5,3}};


        List<List<Integer>> conn = new ArrayList<>();
        for (int[] a : connections) {
            List<Integer> l = new ArrayList<>();
            l.add(a[0]);
            l.add(a[1]);
            conn.add(l);
        }
        List<List<Integer>>  ans = cc.criticalConnections(4, conn);
        for(List<Integer> l : ans) {
            System.out.println(l);
        }
    }
}
