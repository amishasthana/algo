import java.util.*;

public class OddEvenJump {

    /*
    Start 10:30 AM
      We will have to sort too , both
      Input: [10,13,12,14,15]
Output: 2
Explanation:
From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can't jump any more.
From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.
From starting index i = 3, we can jump to i = 4, so we've reached the end.
From starting index i = 4, we've reached the end already.
In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.

         15 --> h = 15, l = 15, t,t
         14 --> h = 15, l = 15; t,f
         12 --> h = 15, l = 14 ; f,f
         13 --> h = 15, l = 12; f,


         BAsically at each position you need to know what is the smallest, largest and greatest, smallest

    */
    class SolPerPos {
        int pos;
        int v;
        boolean oddJ;
        boolean evenJ;
        SolPerPos minGreatest;
        SolPerPos maxSmallest;
    }

    //Map<Integer,SolPerPos> mapOfSol = new HashMap<>();
    PriorityQueue<SolPerPos> minHeap = new PriorityQueue<>((x,y) -> (x.v == y.v)?(x.pos - y.pos):(x.v-y.v));
    TreeMap<Integer,Integer> treeMap = new TreeMap<>();

    public int oddEvenJumps(int[] A) {
        int possibleSol = 0;
        if ((A == null) || (A.length == 0)) {
            return possibleSol;
        }
        int len = A.length -1;
        boolean[] ceilingOK = new boolean[len+1];
        boolean[] floorOK = new boolean[len+1];
        ceilingOK[len] = true;
        floorOK[len] = true;
        treeMap.put(A[len],len);
        possibleSol++;
        for (int i = len-1; i >= 0; i--) {
            Map.Entry ceilingEntry = treeMap.ceilingEntry(A[i]);//Ceiling aka greater and smallest
            Map.Entry floorEntry = treeMap.floorEntry(A[i]);//Floor aka smaller and greatest.
            if(ceilingEntry != null) {
                ceilingOK[i] = floorOK[(int)ceilingEntry.getValue()];// Using index of specific entry. Index is the value.
            }
            if(floorEntry != null) {
                floorOK[i] = ceilingOK[(int)floorEntry.getValue()];
            }
            if (ceilingOK[i]) possibleSol++;
            treeMap.put(A[i],i);
        }
        return possibleSol;
    }

    /*
    public int oddEvenJumps(int[] A) {
        int possibleSol = 0;
        if ((A == null) || (A.length == 0)) {
            return possibleSol;
        }
        for (int i = A.length - 1; i >= 0; i--) {
            SolPerPos solPos = new SolPerPos();
            solPos.pos = i;
            solPos.v = A[i];
            solPos.minGreatest = getMinGreatest(solPos);
            solPos.maxSmallest = getMaxSmallest(solPos);
            minHeap.add(solPos);
            if(minHeap.size() == 1) {//First element
                possibleSol++;
                solPos.evenJ = true; solPos.oddJ = true;
                solPos.minGreatest = solPos; solPos.maxSmallest = solPos;
                continue;
            }
            if (solve(solPos)) {
                possibleSol++;
            }
        }

        return possibleSol;
    }

    private boolean solve(SolPerPos sol) {
        SolPerPos solMaxSmallest = sol.maxSmallest;
        if ((solMaxSmallest != null) && (solMaxSmallest.evenJ)) {
            sol.oddJ  = true;
        }

        //For even case.
        if ((sol.minGreatest != null) && (sol.minGreatest.oddJ)) {
            sol.evenJ = true;
        }
        if(sol.oddJ) System.out.println(sol.v);
       return sol.oddJ;
    }
*/
    /*
       Get minimum which is greater then current value and nearest to it.
       Min heap is sufficient. Continue to take element
       as soon as its equal or greater return it.
       In heap element of equal value are by nearest to current pos.
     */
    /*
    private SolPerPos getMinGreatest(SolPerPos sol) {
        SolPerPos minGreatest = null;
        List<SolPerPos> solPerPosCollection = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            SolPerPos cPos = minHeap.poll();
            solPerPosCollection.add(cPos);
            if (cPos.v <= sol.v) {
                if( (minGreatest != null) && ( minGreatest.v == cPos.v) ) {
                    continue;
                }
                minGreatest = cPos;
            } else {
                break;
            }
        }
        minHeap.addAll(solPerPosCollection);

        return minGreatest;
    }
*/
    /*
       Get Maximum which is smaller then or equal to current value.
       Min heap is sufficient. Continue to take element
       as soon as its equal or smaller return it.
     */
    /*
    private SolPerPos getMaxSmallest(SolPerPos sol) {

        SolPerPos maxSmallest = null;

        List<SolPerPos> solPerPosCollection = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            SolPerPos cPos = minHeap.poll();
            solPerPosCollection.add(cPos);
            if (cPos.v >= sol.v) {
                maxSmallest = cPos;
                break;

            }
        }
        minHeap.addAll(solPerPosCollection);

        return maxSmallest;
    }
    */

    public static void main(String[] args) {
        OddEvenJump sol = new OddEvenJump();
        //int[] iA = {10,13,12,14,15};//--> 2
        //int[] iA = {2,3,1,1,4};//-> 3
        //int[] iA = {5,1,3,4,2};//-->3
        //int[] iA = {81,54,96,60,58};//--> 2
        int[] iA = {2,96,18,12,21,80,93,2,42,10,25,22,64,35,18,50,3,10,98,19};//--> 15
        System.out.println(sol.oddEvenJumps(iA));
    }


}
