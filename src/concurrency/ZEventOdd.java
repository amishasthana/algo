package concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZEventOdd {
    private int n;

    private final Semaphore pZ;
    private final Semaphore pO;
    private final Semaphore pE;
    private volatile boolean lastPrintedOdd = false;

    public ZEventOdd(int n) {
        this.n = n;
        pZ = new Semaphore(0);
        pO = new Semaphore(0);
        pE = new Semaphore(0);
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                pZ.acquire();
            }
            printNumber.accept(0);
            if(lastPrintedOdd) {
                lastPrintedOdd = false;
                pE.release();
            } else {
                lastPrintedOdd = true;
                pO.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            pO.acquire();
            printNumber.accept(i);
            pZ.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            pE.acquire();
            printNumber.accept(i);
            pZ.release();
        }
    }

}
