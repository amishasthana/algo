/*
802 :
In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.


 */

import java.util.*;

public class EventualSafeState {

    int[][] gr = null;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer>  sol = new ArrayList<>();
        if ((graph == null) || (graph.length == 0)) {
            return sol;
        }
        gr = graph;

        Set<Integer> circularSet = new HashSet<>();
        for(int i = 0; i < graph.length;i++) {
            if (circularSet.contains(i)) continue;
            populateCircularSet(i,circularSet);
        }
        for(int i = 0; i < graph.length;i++) {
            if (!circularSet.contains(i)) {
                sol.add(i);
            }
        }


        return sol;
    }


    private void populateCircularSet(int i,Set<Integer> circularSet) {
        Stack<Integer> depthF = new Stack<>();
        Set<Integer> vSet = new HashSet<>();
        vSet.add(i);
        depthF.add(i);

        while(!depthF.isEmpty()) {
            int cNode = depthF.pop();

            int[] child = gr[cNode];
            for(int cc : child) {
                if (circularSet.contains(cc) || (cc == i)) {
                    circularSet.add(i);
                    circularSet.add(cNode);
                    return;
                }
                if (!vSet.contains(cc)) {
                    vSet.add(cc);
                    depthF.add(cc);
                }
            }
        }
    }


    public static void main(String[] args) {
        EventualSafeState ess = new EventualSafeState();
        //int[][] iA = {{1,2},{2,3},{5},{0},{5},{},{}};
        //int[][] iA = {{0},{2,3,4},{3,4},{0,4},{}};
        //int[][] iA = {{},{0,2,3,4},{3},{4},{}};
        int[][] iA = {{1,2,3,4},{1,2,3,4},{3,4},{4},{}};
        List<Integer> sol = ess.eventualSafeNodes(iA);
        for(int i : sol) {
            System.out.print(i+" ");
        }
    }



}
