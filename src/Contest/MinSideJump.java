package Contest;

public class MinSideJump {
    public int minSideJumps(int[] obstacles) {
        int l = obstacles.length;
        int[] O = obstacles;
        long[][] A = new long[l][3];
        if (O[l-1] != 0) {
            A[l-1][O[l-1] - 1]  = Integer.MAX_VALUE;
        }

        for(int i = l-2; i >= 0;i--) {
            if (O[i] != 0) {
                A[i][O[i] - 1]  = Integer.MAX_VALUE;
            }
            long a1 = Math.max(A[i][0],1);
            long b1 = Math.max(A[i][1],1);
            long c1 = Math.max(A[i][2],1);
            A[i][0] = Math.min(Math.min(A[i+1][0],b1+A[i+1][1]),c1+A[i+1][2]);
            A[i][1] = Math.min(Math.min(A[i+1][1],a1+A[i+1][0]),c1+A[i+1][2]);
            A[i][2] = Math.min(Math.min(A[i+1][2],a1+A[i+1][0]),b1+A[i+1][1]);
            if (O[i] != 0) {
                A[i][O[i] - 1]  = Integer.MAX_VALUE;
            }
        }
        return (int)A[0][1];
    }

    public static void main(String[] args) {
        MinSideJump msj = new MinSideJump();
        int[] O = {0,1,2,3,0};
        System.out.println(msj.minSideJumps(O));
    }

}
