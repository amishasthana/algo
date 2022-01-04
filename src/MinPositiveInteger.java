/*
The problem is pretty interesting.
    Given an array of positive integer/negative integer(An integer can be repeated)
    Figure out the smallest positive integer starting from 1 which is missing.
    Do note its not a sequence.
    So [7,8,9,10]--> missing 1 as we start looking from 1 and its not there.
    O(N) and No extra space :)

    Algo : Number which is greater than X put as zero.
    Next step : Try to put number at their designated position. Initialize to zero if pos.
       While putting if not zero(Sum and negative).
       While getting if not zero :
          Pos : Simple put.
          Negative : ABS - Index is the number. Again try to put it.
          If allready there(equal to index or negative discard.)
          For one index only one put possible.

 */
public class MinPositiveInteger {
    public int minPossible(int[] arr) {
        int maxMissing = arr.length+1;
        //Basically remove unwanted number. Using 0 to mark indices which are empty.
        for(int i = 0; i < arr.length;i++) {
            if ((arr[i] >= maxMissing) ||(arr[i] <= 0)) arr[i] = 0;
        }

        for(int i = 0; i < arr.length;i++) {
            int cVal = arr[i];
            if (cVal == 0 ) {
                continue;
            } else if (cVal < 0) { //Negative.
                int indexToUpdate = (cVal*-1)-(i+1);
                arr[i] = i+1;
                update(arr,indexToUpdate);
            } else { //pos
                int indexToUpdate = arr[i];
                arr[i] = 0;
                update(arr,indexToUpdate);
            }
        }

        for(int i = 0; i < arr.length;i++) {
            if (arr[i] == 0) return i+1;
        }
        return  maxMissing;
    }

    private void update(int[] arr,int val) {
        int cVal = arr[val-1];
        if (cVal == 0) {
            arr[val-1] = val;
        } else if (cVal < 0) {//Allready updated
            return;
        } else {//pos
            arr[val-1] = -1*(arr[val-1]+val);
        }
    }


    public static void main(String[] args) {
        MinPositiveInteger mpi = new MinPositiveInteger();
        //int[] arr = {1,1,3,2,4,5,7,8,2,9,15};
        //int[] arr = {1,1,3,4,5};
        //int[] arr = {2,3,1,2,1,3};
        int[] arr = {2,0,3,-1,2,1,3,5};
        System.out.println(mpi.minPossible(arr));
    }
}
