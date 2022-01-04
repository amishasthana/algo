package DP;

public class DunjeonPractice {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] d = dungeon;
        int  l = dungeon.length;
        int w = dungeon[0].length;
        for( int i = l-1;i >= 0; i--) {
            for(int j = w-1;j >= 0;j--) {
                int rWeight = getRWeight(d,i,j);
                int dWeight = getDWeight(d,i,j);
                if (( (j+1) >= d[0].length) && ((i+1)>=d.length) ) {
                    d[i][j] = -1*d[i][j];
                } else if(( (j+1) >= d[0].length)) {
                    d[i][j] = dWeight-d[i][j];
                } else if (((i+1)>=d.length)) {
                    d[i][j] = rWeight-d[i][j];
                } else {
                    d[i][j] = Math.min(rWeight,dWeight)-d[i][j];
                }

                d[i][j] = (d[i][j] <= 0)?0:d[i][j];
            }//end of inner for
        }// end of outer for
        return d[0][0]+1;
    }

    private int getRWeight(int[][] D,int i , int j) {
        if ( (j+1) >= D[0].length) return 0;
        return D[i][j+1];

    }

    private int getDWeight(int[][] D,int i , int j) {
        if ( (i+1) >= D.length) return 0;
        return D[i+1][j];
    }

    public static void main(String[] args) {
        DunjeonPractice dg = new DunjeonPractice();
        int[][] A = {
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}
        };
        int[][] A1 = { {1,-2,3},
                {2,-2,-2}};
        int[][] A3 = {{0}};
        System.out.println(dg.calculateMinimumHP(A3));
    }

}
