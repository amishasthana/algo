package googleAss;

import java.util.PriorityQueue;

public class MinWage {
    class W implements Comparable<W>{
        int q; int w; double cq;
        public W(int q,int w,double cq) {
            this.q = q;
            this.w = w;
            this.cq = cq;
        }

        public int compareTo(W w) {
            return Double.compare(this.cq,w.cq);
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int[] q = quality;
        int l = wage.length;;
        double[] cq = new double[l];
        PriorityQueue<W> P = new PriorityQueue<>();
        double maxSoFar = 0.0;
        for(int i = 0; i < l;i++) {
            cq[i] = (quality[i]*1.0)/(wage[i]*1.0);//Higher the better
            P.add( new W(q[i],wage[i],cq[i]));
            while (P.size() > K) P.poll();
        }
        double t = 0.0;
        double max = P.peek().cq;

        while (K-- > 0) {
            W cw = P.poll();
            t += (cw.cq/max)*cw.w;
        }
        return t;
    }

    public static void main(String[] args) {
        MinWage mw  = new MinWage();
        int[] q = {10,20,5};
        int[] w = {70,50,30};

        System.out.println(mw.mincostToHireWorkers(q,w,2));
    }
}
