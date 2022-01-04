/*
Given a rectange find the minimum number of square to cover it.
 */
public class RectangleSquareCoverage {
    int[][] cache = null;
    public int minSquare(int x,int y) {
        if (cache == null) {
            cache = new int[x][y];
        }
        if ((x == y)) {
            putInCache(x,y,1);
            getFromCache(x,y);
        }

        //System.out.println("x "+x+" y "+y);
        if (getFromCache(x,y) != 0) {
            return getFromCache(x,y);
        }
        int minV = Integer.MAX_VALUE; int minH = Integer.MAX_VALUE;

            for (int i = 1; i <= (y / 2); i++) {
                int cMinV = minSquare(x, i) + minSquare(x, y - i);
                if (minV > cMinV) minV = cMinV;
            }

            for (int j = 1; j <= x / 2; j++) {
                int cMinH = minSquare(j, y) + minSquare(x - j, y);
                if (minH > cMinH) minH = cMinH;
            }

        putInCache(x,y, Math.min(minV,minH));

        return getFromCache(x,y);
    }

    private void printCache() {
        for(int i = 0; i < cache.length;i++) {
            for(int j = 0; j < cache[0].length;j++) {
                System.out.print(cache[i][j]+" ");
            }
            System.out.println();
        }
    }

    private int getFromCache(int x, int y) {
        if (x == y) return 1;
        return cache[x-1][y-1];
    }

    private void putInCache(int x, int y,int v) {
        /*
        if((x == 3) && (y == 4)) {
            System.out.println("Value for 3 and 4 "+v);
        }
        */
        if ((cache[x-1][y-1] == 0) || (cache[x-1][y-1] > v)) {
            cache[x-1][y-1] = v;
        }

        if(((y-1) >= 0) && ((y-1) < cache.length) &&
                ((x-1) < cache[0].length) && ((x-1) >= 0)) {
            cache[y-1][x-1] = v;
        }
    }

    public static void main(String[] args) {
        RectangleSquareCoverage rsc = new RectangleSquareCoverage();
        System.out.println(rsc.minSquare(11,13));
        //System.out.println(rsc.minSquare(3,4));
        rsc.printCache();
    }

}
