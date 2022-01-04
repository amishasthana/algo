import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PageRank {
    class Page {
        String v;
        double rank;
        double prevRank;
        Set<String> incoming = new HashSet<>();
        int outgoing;
    }

    Map<String,Page> pRank = new HashMap<>();
    double damp = 0.85;

    public void calculatePageRank() {
        for(String key : pRank.keySet()) {
            Page cPage = pRank.get(key);
            cPage.prevRank = cPage.rank;
            cPage.rank = 0;
            for(String keyToL : cPage.incoming) {
                Page lPage = pRank.get(keyToL);
                cPage.rank += damp*(lPage.prevRank/lPage.outgoing);
            }
            cPage.rank += 0.15;
        }
    }

    /*
        A --> B.
        So A is incoming to B.
     */
    public void init(String[][] pages) {
        for(int i = 0; i < pages.length;i++) {
            Page incoming = pRank.get(pages[i][0]);
            Page outgoing = pRank.get(pages[i][1]);
            if (incoming == null) {
                incoming = new Page();
                incoming.v = pages[i][0];
                pRank.put(incoming.v,incoming);
            }
            if (outgoing == null) {
                outgoing = new Page();
                outgoing.v = pages[i][1];

                pRank.put(outgoing.v,outgoing);
            }
            incoming.outgoing++;
            outgoing.incoming.add(pages[i][0]);

        }
        for(Page p : pRank.values() ) {
            p.rank = 1.0/pRank.size();
            p.prevRank = p.rank;
        }
    }

    public void calRank(int i,double tol) {
        for (int j= 0; j < i;j++) {
            calculatePageRank();
            if (withinTol(tol)) break;
        }
        printRank();
    }

    private boolean withinTol(double tol) {
        for(Page p : pRank.values()) {
            if (Math.abs(p.prevRank-p.rank) > tol) {
                return false;
            }
        }
        return true;
    }

    private void printRank() {
        double debug = 0;
        for(Page p : pRank.values()) {
            System.out.println("Page "+p.v+" rank = "+p.rank);
            debug += p.rank;
        }
        System.out.println("Page rank "+debug);
    }

    public static void main(String[] args) {
        PageRank pr = new PageRank();
        String[][] pages = {{"A","B"},{"A","C"},{"B","D"},{"C","D"},{"C","A"},{"C","B"},{"D","A"}};
        pr.init(pages);
        pr.calRank(100,0.01);
    }
}
