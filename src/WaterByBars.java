
/*
  A set of bars of varying height along a line.
  Basically calculate how much water they can hold.
   The basic idea is that you find the highest and then next highest on left/right side of that. That will give you the volume.
   Each time divide the range into two.
   The other thing is in two pass(one either side) you figure out the highest to the left or right of the Bar.
 */

public class WaterByBars {

    /*
        The first is X coordinate and second is Y. Assuming its sorted by X coordinate.
     */
    double[][] bars;
    int[] hToTheRight;
    int[] hToTheLeft;


    private void caluclateMaxLeft(double[][] bars) {
        hToTheLeft = new int[bars.length];
       int indexOfMaxHToLeft = 0;
       double maxY = bars[0][1];
        hToTheLeft[0] = 0;
       for(int i = 1;i <bars.length;i++) {
           hToTheLeft[i] = indexOfMaxHToLeft;
           if (bars[i][1] > maxY) {
               maxY = bars[i][1];
               indexOfMaxHToLeft = i;
           }
       }
    }

    private void caluclateMaxRight(double[][] bars) {
        hToTheRight = new int[bars.length];
        int indexOfMaxHToRight = bars.length-1;
        double maxY = bars[bars.length-1][1];
        hToTheRight[bars.length-1] = bars.length-1;
        for(int i = bars.length-1;i >=0;i--) {
            hToTheRight[i] = indexOfMaxHToRight;
            if (bars[i][1] > maxY) {
                maxY = bars[i][1];
                indexOfMaxHToRight = i;
            }
        }
    }


    public double calculateVolume(double[][] bars) {
        if ( (bars == null) || (bars.length == 0)) {
            return 0;
        }

        caluclateMaxLeft(bars);//Calculating to the left
        caluclateMaxRight(bars);//Calculating to the right
        int maxGlobalIndex = hToTheRight[0];
        return (calculateVolumeLeft(bars,0,maxGlobalIndex) + calculateVolumeRight(bars,maxGlobalIndex,bars.length-1));
    }

    /*
        One call of the method calculate the volume between these two range left of X2

     */
    private double calculateVolumeLeft(double[][] bars,int x1,int x2) {
        if (x1 >= x2) return 0;
        int indexLeftHighestofX2 = hToTheLeft[x2];
        double v = (bars[indexLeftHighestofX2][1] *(bars[x2][0] - bars[indexLeftHighestofX2][0]));//Caluclated this volume and done for this portion.
        return v + calculateVolumeLeft(bars,x1,indexLeftHighestofX2);
    }

    /*
        One call of the method calculate the volume between these two range right of X1

     */
    private double calculateVolumeRight(double[][] bars,int x1,int x2) {
        if (x1 >= x2) return 0;
        int indexRightHighestOfX1 = hToTheRight[x1];
        double v = (bars[indexRightHighestOfX1][1] *(bars[indexRightHighestOfX1][0] - bars[x1][0]));//Caluclated this volume and done for this portion.
        return v + calculateVolumeRight(bars,indexRightHighestOfX1,x2);
    }

    public static void main(String[] args) {
        double[][] hAA = new double[][] {{0,1},{1,3}};
        double[][] histArray = new double[][] { {2,4},{5,6},{8,3},{10,5},{12,1}  };
        //double[][] histArray = new double[][] {{0,0},{1,0},{2,4},{3,0},{4,0},{5,6},6,7,8,9,10,11,12,13,14,15}}
        WaterByBars wBars = new WaterByBars();
        System.out.println(wBars.calculateVolume(histArray));
    }

}
