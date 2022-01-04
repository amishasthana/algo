public class BikeShare {
    private int[] cache = null;
    private boolean[] usedBikes = null;
    private int[] assign = null;

    /*
    public int findBestCost(int[][] bikes,int[][] person)  {
        //Do some basic checks.
        init(bikes,person);
        int min = findBest(bikes,person,0);
        return min;
    }
    */


    private void init(int[][] bikes,int[][] person) {
        cache = new int[1<<bikes.length];
        usedBikes = new boolean[bikes.length];
        assign = new int[person.length];
        for(int i = 0; i < assign.length;i++) {
            assign[i] = -1;
        }
    }

    public int[] findBestCostAssign(int[][] bikes,int[][] person)  {
        //Do some basic checks.
        init(bikes,person);
        int min = findBest(bikes,person,0);
        System.out.println(" Min cost is "+min);
        for(int i = 0; i < assign.length;i++) {
            System.out.println("Person "+i+" assigned "+assign[i]);
        }
        return assign;
    }



    private int findBest(int[][] bikes,int[][] person,int pIndex) {
        int minCost = Integer.MAX_VALUE;
        int assignForPIndex = -1;
        for(int i = 0; i < bikes.length;i++) {
            if (!usedBikes[i]) {
                int cost = 0;
                cost += findCost(person[pIndex],bikes[i]);
                usedBikes[i] = true;
                int key = findKey(usedBikes);
                if (pIndex < (person.length-1)) {
                    if (cache[key] != 0) {
                        cost += cache[key];
                    } else {
                        cost += findBest(bikes,person,pIndex+1);
                    }
                }
                if (minCost > cost) {
                    minCost = cost;
                    cache[key] = minCost;
                    assignForPIndex = i;
                }
                usedBikes[i] = false;
            }
        }

        assign[pIndex] = assignForPIndex;
        usedBikes[pIndex] = true;

        return minCost;
    }

    private int findCost(int[] x,int[] y) {
        return Math.abs(x[0]-y[0])+Math.abs(x[1]-y[1]);
    }

    private int findKey(boolean[] usedBike){
        int key = 0;
        for(int i = 0; i < usedBike.length;i++) {
            if(usedBike[i]) {
                key+= (1<<i);
            }
        }
        return key;
    }

    public static void main(String[] args) {
        BikeShare bikeShare = new BikeShare();
     /*
      * workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
      * is [0,2,1].
      */
        int[][] per = {{0,0},{1,1},{2,0}};
        int[][] bikes = {{1,0},{2,2},{2,1}};
        bikeShare.findBestCostAssign(bikes, per);
    }

}
