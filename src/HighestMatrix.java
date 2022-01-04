import java.util.concurrent.ThreadLocalRandom;

/*
  Given a matrix of +ve/-ve values, find the matrix(not necessary square which has highest sum.
  The matrix has +ve/-ve integers.
  Solution :
  Select a row combination, R1 to R2.
  Between this you are trying to find the conntinuos set of columns which has the highest values.
  Basically the complexity is :
  Combination of rows : R*R
  Figuring out the column sum --> C
  Figuring out the set of column which has the highest value --> Again C(Same as Max subarray)
  So R*R *2*C = R*R*C
 */
public class HighestMatrix {

    private int maxSumSoFar = Integer.MIN_VALUE;
    private  int rLowest;
    private  int rHighest;
    private int cLeft;
    private int cRight;

    class Highest {
        int sum;
        int start;
        int end;
    }

    public Highest getHighest(int[] iA) {
        Highest hObj  = new Highest();
        int cSum = iA[0]; int start = 0;int end = 0;
        hObj.sum = cSum; hObj.start = 0; hObj.end = 0;
        for(int i = 1; i < iA.length;i++) {
            if ((cSum + iA[i] ) > 0) {
                cSum += iA[i];
            } else {
                cSum = iA[i];
                start = i;
            }
            end = i;
            if (cSum > hObj.sum) {
                hObj.sum = cSum;
                hObj.start = start;
                hObj.end = end;
            }
        }//End of if.
        return hObj;
    }

    private boolean validate(int[][] mArray) {
        if ((mArray == null) || (mArray.length == 0) || (mArray[0].length == 0)) {
            return false;
        }
        return true;
    }

    private int[] getColumnarSumArray(int r1,int r2,int[][] mArray) {
            int[] cSum = new int[mArray[0].length];
            for(int j = 0; j < mArray[0].length;j++) {
                     for( int r = r1;r <= r2;r++) {
                           cSum[j] +=  mArray[r][j];
                     }
            }
            return cSum;
    }

    public void calculateHighest(int[][] mArray) {
        if (!validate(mArray)) {
            System.out.println("Array is null or empty, returning");
        }
        for(int i = 0;i < mArray.length;i++) {
            for(int j = i+1;j < mArray.length;j++) {
                int[] cSumArray = getColumnarSumArray(i,j,mArray);
                Highest hObj = getHighest(cSumArray);
                if (hObj.sum > maxSumSoFar) {
                    maxSumSoFar = hObj.sum;
                    rLowest = i;
                    rHighest = j;
                    cLeft = hObj.start;
                    cRight = hObj.end;
                }
            }
        }//Outer row loop
        System.out.println("The initial array is  ");
        printArray(mArray,0,mArray.length-1,0,mArray[0].length-1);
        System.out.println("The highest sum is "+maxSumSoFar+" with rows "+rLowest+" and "+rHighest+" col "+cLeft+" and "+cRight);

        printArray(mArray,rLowest,rHighest,cLeft,cRight);
    }

    private void printArray(int[][] mArray,int sRow,int eRow,int sCol,int eCol) {
        for(int i = sRow; i <= eRow;i++) {
            for(int j = sCol;j <= eCol;j++) {
                System.out.print(mArray[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public int[][] initArray(int r,int c) {
        if ((r == 0) || (c == 0)) {
            throw new IllegalArgumentException(" rows "+r+ " columns "+c+" should be greater then zero ");
        }

        int[][] mArray = new int[r][c];
        for(int i = 0;i < r; i++) {
            for(int j = 0; j < c;j++) {
                mArray[i][j] = ThreadLocalRandom.current().nextInt(-10, 10 + 1);
            }
        }
        return mArray;
    }

    public int[][] getHCoded() {
        int[][] nArray = {{-8, 9, 7, -6, 2}, {2, -1, -5, 1, -8}, {-5, 10, 6, 7, 1}};
        return nArray;
    }

    public static void main(String[] args) {
        HighestMatrix hm = new HighestMatrix();
        int[][] mArray = hm.getHCoded();//hm.initArray(3,5);
        hm.calculateHighest(mArray);
    }


}