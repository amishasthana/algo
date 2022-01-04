package concurrency;
/*
1116. Print Zero Even Odd
Medium

Suppose you are given the following code:

class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // constructor
  public void zero(printNumber) { ... }  // only output 0's
  public void even(printNumber) { ... }  // only output even numbers
  public void odd(printNumber) { ... }   // only output odd numbers
}

The same instance of ZeroEvenOdd will be passed to three different threads:

    Thread A will call zero() which should only output 0's.
    Thread B will call even() which should only ouput even numbers.
    Thread C will call odd() which should only output odd numbers.

Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.



Example 1:

Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.

Example 2:

Input: n = 5
Output: "0102030405"

Easier to do using queue.

 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.function.IntConsumer;
//import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;
    //AtomicInteger atL = new AtomicInteger(1);
    private final Lock aLock = new ReentrantLock();
    private final Condition printZero = aLock.newCondition();
    private final Condition printEven = aLock.newCondition();
    private final Condition printOdd = aLock.newCondition();

    private  volatile boolean pOdd = false;
    private  volatile boolean pEven = false;
    private  volatile boolean pZero = true;
    private volatile boolean lastPrintedOdd = false;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        aLock.lock();
        //System.out.println(" zero "+atL.get()+pOdd.get()+ pEven.get());
        try {
            //System.out.println("In print zero "+atL.get());


            for (int i = 0; i < n; i++) {
                while(!pZero) {// Even index starting with 0 is when we print.
                    printZero.await();
                }
                printNumber.accept(0);
                if(lastPrintedOdd) {
                    lastPrintedOdd = false;
                    pZero = false;
                    pEven = true;
                    printEven.signalAll();
                } else {
                    lastPrintedOdd = true;
                    pZero = false;
                    pOdd = true;
                    printOdd.signalAll();
                }
            }
        }finally {
            aLock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        aLock.lock();
        //System.out.println(" even "+atL.get()+pEven.get());
        try {

            for(int i=2;i<=n;i=i+2) {
                while(!pEven) {
                    printEven.await();
                }
                printNumber.accept(i);
                pEven = false;
                pOdd = false;
                pZero = true;
                printZero.signalAll();
            }
        }finally {
            aLock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        aLock.lock();
        try {

            for(int i=1;i<=n;i=i+2) {
                while(!pOdd){
                    printOdd.await();
                }

                printNumber.accept(i);
                pEven = false;
                pOdd = false;
                pZero = true;
                printZero.signalAll();
            }
        }finally {
            aLock.unlock();
        }
    }


    static class IntConsumer {
        public void accept(int n) {
            System.out.println("*** "+n);
        }
    }


    static class TestOne implements  Runnable {

        private final String type;
        private final ZeroEvenOdd z;
        private final IntConsumer ic;

        public TestOne(String t,ZeroEvenOdd zeo,IntConsumer ic) {
            type = t;
            z = zeo;
            this.ic = ic;
        }


        @Override
        public void run()  {
            try {
                if ("ZERO".equals(type)) {
                    z.zero(ic);
                } else if ("EVEN".equals(type)) {
                    z.even(ic);
                } else {
                    z.odd(ic);
                }
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd z = new ZeroEvenOdd(6);
        IntConsumer ic = new IntConsumer();
        TestOne zThread = new TestOne("ZERO",z,ic);
        TestOne oThread = new TestOne("ODD",z,ic);
        TestOne eThread = new TestOne("EVEN",z,ic);
        new Thread(zThread).start();
       new Thread(oThread).start();
        new Thread(eThread).start();

    }
}
