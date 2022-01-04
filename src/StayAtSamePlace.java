/*
ou start at index 0 in an array with length 'h'. At each step, you can move to the left, move to the right, or stay in the same place
(Note! Stay in the same place also takes one step). How many possible ways are you still at index 0 after you have walked 'n' step?

For each step s <= n --> maintain a set. (Only unique element.)
Go from each step to next only with unique ones. Do note unique is the whole chain.
At end anything which is at start will be counted.

Is there another way

 */
public class StayAtSamePlace {
    private int finishIndex = 0;
    private int totalSteps = 0;
    private int arraySize = 0;
    public int findNumberOfCombination(int arraySize,int cIndex,int steps) {
        this.arraySize = arraySize;
        finishIndex = cIndex;
        totalSteps = steps;
        int retV = findDistinctPath(cIndex,steps);

        System.out.println("Total ways are "+retV);
        return retV;
    }

    public int findDistinctPath(int cIndex,int stepRemaining) {
         if ((stepRemaining == 1) && (Math.abs(cIndex-finishIndex) <=1)) {
             return 1;
         }
         if (stepRemaining == 1)   return 0;
         if((cIndex < 0) || (cIndex >= arraySize)) return 0;
         int cnt = 0;

        cnt += findDistinctPath(cIndex,stepRemaining-1);
        cnt += findDistinctPath(cIndex-1,stepRemaining-1);
        cnt += findDistinctPath(cIndex+1,stepRemaining-1);

        return cnt;

    }

    public static void main(String[] args) {
        StayAtSamePlace sas = new StayAtSamePlace();
        sas.findNumberOfCombination(3,0,3);
    }

}
