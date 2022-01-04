package Goog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
465. Optimal Account Balancing
Hard

A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

    A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
    Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.

Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

 */
public class OptimalAccountBalance {
    public int minTransfers(int[][] transactions) {
        Map<Integer,Integer> D =  new HashMap<>();
        for(int i = 0 ; i < transactions.length; i++) {
            int P1 = transactions[i][0];
            int P2 = transactions[i][1];
            int V = transactions[i][2];
            D.put(P1 , D.getOrDefault(P1,0)+V);
            D.put(P2 , D.getOrDefault(P2,0)-V);
        }
        List<Integer> V = new ArrayList<>( D.values());
        return solve(0 , V);
    }


    private int solve(int start,List<Integer> list) {

        while ( (start < list.size()) && (list.get(start)  == 0)) start ++;
        if ( start == list.size() ) return 0;
        int mT = Integer.MAX_VALUE;
        for( int i = start+1; i < list.size(); i++ ) {
            if ( list.get(i) * list.get(start)  < 0) {
                list.set(i,   list.get(i)  +  list.get(start) );
                mT = Math.min(mT,1 + solve(start+1, list) ) ;
                list.set(i,   list.get(i)  - list.get(start) );
            }
        }
        return mT;
    }

    public static void main(String[] args) {
        OptimalAccountBalance oab = new OptimalAccountBalance();
        int[][] A = {{0,1,10}, {1,0,1}, {1,2,5}, {2,0,5}};
        System.out.println(oab.minTransfers(A));
    }

}
