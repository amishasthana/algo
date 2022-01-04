/*


 */
public class Obstacles {


    public static void main(String[] args) {
        Obstacles pbs = new Obstacles();
        int INF = 2147483647;
        int[][] A = {
                {INF,-1,0,INF},
        {INF,INF,INF,-1},
                {INF,-1,INF,-1},
                {0,-1,INF,-INF}
        };
        //pbs.wallsAndGates(A);
        System.out.print(A);
    }


}
