import java.util.*;

/*
939. Minimum Area Rectangle
Medium

Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2



Note:

    1 <= points.length <= 500
    0 <= points[i][0] <= 40000
    0 <= points[i][1] <= 40000
    All points are distinct.

      x1,y2            x2,y2
      x1,y1            x2,y1

Sort by X.
Now for each X there is a set of keys.
For that keys, first create a kind of map : Y1_Y2--> X
Next find of map find the area. Also update Y1_Y2 --> X1.
As smallest so you want to reduce X distance.

 */
public class MinrectangleArea {

    public int minAreaRect(int[][] points) {
        TreeMap<Integer,List<Integer>> XM = new TreeMap<>();
        for(int i = 0 ; i < points.length; i++) {
            List<Integer> ySet = XM.get(points[i][0]);
            if (ySet == null ) {
                ySet = new LinkedList<Integer>();
                XM.put(points[i][0],ySet);
            }
            ySet.add(points[i][1]);
        }
        Map<String,Integer> mYToX = new HashMap<>();
        int mArea = Integer.MAX_VALUE;
        boolean fArea = false;
        for(Map.Entry<Integer,List<Integer>> map : XM.entrySet()) {
               Integer fX = map.getKey();
               List<Integer> yVal = map.getValue();
               for(int i = 0; i < yVal.size();i++) {
                   for(int j = i+1; j < yVal.size();j++) {
                       String yKey = null;
                       if (yVal.get(i) <= yVal.get(j)) {
                           yKey = yVal.get(i)+"_"+yVal.get(j);
                       } else {
                           yKey = yVal.get(j)+"_"+yVal.get(i);
                       }
                       if (mYToX.get(yKey) != null) {
                           mArea = Math.min(mArea,Math.abs((fX-mYToX.get(yKey))*(yVal.get(i) - yVal.get(j))));
                           fArea = true;
                       }
                       mYToX.put(yKey,fX);
                   }
               }

        }//End of outer for
        if (!fArea) return 0;
        return mArea;
    }// ENd of method

    public static void main(String[] args) {
        MinrectangleArea ma = new MinrectangleArea();
        int[][] iA1 = {{1,1},{1,3},{3,1},{3,3},{2,2}};
        int[][] iA2 = {{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}};
        int[][] iA3 = {{0,1},{3,2},{5,5},{4,5},{4,4},{2,0},{2,3},{2,2},{1,0},{5,1},{2,5},{5,2},{2,4},{4,0}};
        System.out.println(ma.minAreaRect(iA1));
        System.out.println(ma.minAreaRect(iA2));
        System.out.println(ma.minAreaRect(iA3));
    }

}
