import java.util.*;

/*
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections
forming a network where connections[i] = [a, b] represents a connection between servers a and b.
Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.



Example 1:

Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

 */
public class Server1 {

    class Graph {
        int v;
        Set<Graph> connected = new HashSet<>();
        boolean visit = false;
    }

    Map<Integer,Graph>  graph = new HashMap<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
             createGraph(n,connections);
                List<List<Integer>> criticalSection = new ArrayList<>();
                for(List<Integer> cConn : connections) {
                    //boolean isCritical = dfs(cConn,n);
                    boolean isCritical = optiDfs(cConn,n);
                    if (!isCritical) {
                        criticalSection.add(cConn);
                    }
                }
              return criticalSection;
    }

    /*
        You have connection say 1--3.
        Take 1 and see if you can reach 3 without using 1-3. If yes its not a crtical section.
        This thing return TRUE if its a critical section.
     */
    private boolean optiDfs(List<Integer> cConn,int size) {
        Graph g = graph.get(cConn.get(0));
        int valueToReach = cConn.get(1);
        if (canReach(g,valueToReach)) {
            return false;
        }

        return true;
    }

    /*
       If we can still reach
     */
    private boolean canReach(Graph g,int v) {
        Stack<Graph> stackOfG = new Stack<>();
        stackOfG.push(g);
        boolean firstOrder = true;
        while(!stackOfG.isEmpty()) {
            Graph cGG = stackOfG.pop();
            if (cGG.visit) {
                continue;
            }
            cGG.visit = true;
            for(Graph child : cGG.connected) {
                if (firstOrder && (v == child.v)) {
                    continue;
                }
                if ((v == child.v)) return true;
                stackOfG.push(child);
            }
            firstOrder = false;
        }
        for(Graph ggg : graph.values()) {
            ggg.visit = false;
        }
        return false;
    }


    private boolean dfs(List<Integer> cConn,int size) {
        boolean canReach = true;
        for(Graph g : graph.values()) {
            Set<Integer> setOfreached = new HashSet<>();
            setOfreached = findSet(g,cConn);
            if (setOfreached.size() < size) {
                canReach = false;
                break;
            }
        }
        return canReach;
    }

    private Set<Integer> findSet(Graph g, List<Integer> cConn) {
        Set<Integer> retVal = new HashSet<>();

        Set<Integer> fVal = new HashSet<>();
        fVal.add(cConn.get(0));
        fVal.add(cConn.get(1));
        Stack<Graph> stackOfG = new Stack<>();
        stackOfG.push(g);
        while (!stackOfG.isEmpty()) {
             Graph cGG = stackOfG.pop();
             if (cGG.visit) {
                 continue;
             }
            cGG.visit = true;
            retVal.add(cGG.v);
            for(Graph child : cGG.connected) {
                if (fVal.contains(cGG.v) && fVal.contains(child.v)) {
                    continue;
                }
                stackOfG.push(child);
            }
        }
        for(Graph ggg : graph.values()) {
            ggg.visit = false;
        }
        return retVal;
    }

    private void createGraph(int n, List<List<Integer>> connections) {
        for(int i = 0; i < n;i++) {
            List<Integer> path = connections.get(i);
            int fNode = path.get(0);
            int sNode = path.get(1);
            Graph g1 = graph.get(fNode);
            Graph g2 = graph.get(sNode);
            if (g1 == null) {
                g1 = new Graph();
                graph.put(fNode,g1);
            }
            if (g2 == null) {
                g2 = new Graph();
                graph.put(sNode,g2);
            }
            g1.v = fNode; g2.v = sNode;
            g1.connected.add(g2);
            g2.connected.add(g1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(0);list1.add(1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);list2.add(1);
        List<Integer> list3 = new ArrayList<>();
        list3.add(2);list3.add(0);
        List<Integer> list4 = new ArrayList<>();
        list4.add(3);list4.add(0);
        List<Integer> list5 = new ArrayList<>();
        list5.add(3);list5.add(4);
        connections.add(list1);
        connections.add(list2);
        connections.add(list3);
        connections.add(list4);
        connections.add(list5);
        Server1 ss = new Server1();

        List<List<Integer>> bConn =  ss.criticalConnections(5,connections);
        for(List<Integer> ll : bConn) {
            System.out.println(ll.get(0)+","+ll.get(1));
        }
    }


}
