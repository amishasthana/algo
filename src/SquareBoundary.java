/*
Given a square with black and white pixels. Find the largest square such that the boundary is all black.
Solution:
Precalculate two things for each square:
a) The number of continous black square to your right. If you are white then its zero, otherwise its 1 + what is on right of you.
b) The number of contonius black square below you. Same logic.
c) At any square its min of two is the potential. If more then current max, then  figure the right + min(below) and below + min(right) is ok, then you are good.
d) If ok set it to max.


*/

import java.util.concurrent.ThreadLocalRandom;

public class SquareBoundary {

    int mr = 0;//Nothing.
    int mc = 0; //Nothing.
    int mSize = 0;

    public int[][] initializeSquare(int s) {
        if (s == 0) {
            throw new IllegalArgumentException(" size is zero, not allowed ");
        }

        int[][] mArray = new int[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                mArray[i][j] = ThreadLocalRandom.current().nextInt(0, 2);//0-> Black, 1 --> White.
            }
        }
        return mArray;

    }

    private int[][] calculateRight(int[][] a) {
        int s = a.length;
        int[][] pR = new int[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = s - 1; j >= 0; j--) {
                if (a[i][j] == 1) {
                    pR[i][j] = 0;
                } else {
                    if (j < s - 1) {
                        pR[i][j] = 1 + pR[i][j + 1];
                    } else {
                        pR[i][j] = 1;
                    }

                }
            }
        }

        return pR;
    }

    private int[][] calculateDown(int[][] a) {
        int s = a.length;
        int[][] pD = new int[s][s];
        for (int j = 0; j < s; j++) {
            for (int i = s - 1; i >= 0; i--) {
                if (a[i][j] == 1) {
                    pD[i][j] = 0;
                } else {
                    if (i < s - 1) {
                        pD[i][j] = 1 + pD[i + 1][j];
                    } else {
                        pD[i][j] = 1;
                    }

                }
            }
        }

        return pD;
    }

    //Assume square and not null etc.
    public void printBiggestSquare(int[][] a) {
        int[][] pRight = calculateRight(a);
        int[][] pDown = calculateDown(a);
        int s = a.length;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                int cPotSize = Math.min(pRight[i][j], pDown[i][j]);
                    int k = cPotSize - 1;//Index
                    while ((k >= 1) && (k > (mSize-1))) {
                        if ((pDown[i][j + k] >= (k+1)) &&
                                (pRight[i + k][j] >= (k+1))) {
                            mSize = cPotSize;
                            mr = i;
                            mc = j;// is it valid
                            break;
                        }
                        k--;
                    }
            }//Column
        }//row
        System.out.println("The main array is ");
        printArray(a, 0, s - 1, 0, s - 1);
        /*
        System.out.println("The pRight ");
        printArray(pRight, 0, s - 1, 0, s - 1);
        System.out.println("The pDown is ");
        printArray(pDown, 0, s - 1, 0, s - 1);
        */
        System.out.println("The small array starting from row " + mr + " and col " + mc + " size " + mSize);
        printArray(a, mr,mr+mSize-1,mc,mc+mSize-1);
    }//end of method



    private void printArray(int[][] mArray, int sRow, int eRow, int sCol, int eCol) {
        for (int i = sRow; i <= eRow; i++) {
            for (int j = sCol; j <= eCol; j++) {
                System.out.print(mArray[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        SquareBoundary sb = new SquareBoundary();
        int[][] a = sb.initializeSquare(10);
        //int[][] a = {{1,0,1,0,0},{1,0,1,1,0},{0,1,0,0,1},{0,0,1,1,0},{0,0,1,0,0}};
        sb.printBiggestSquare(a);
    }


}//End of class
