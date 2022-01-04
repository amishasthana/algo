package concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzzAlt {
    private int n;
    Semaphore tSem = new Semaphore(0);
    Semaphore fSem = new Semaphore(0);
    Semaphore fifSem = new Semaphore(0);
    Semaphore nSem = new Semaphore(1);
    private volatile int index = 1;

    public FizzBuzzAlt(int n) {
        this.n = n;

    }

    // printFizz.run() outputs "fizz". by 3
    public void fizz(Runnable printFizz) throws InterruptedException {
        tSem.acquire();
        if(index > n) return;
        index++;
        printFizz.run();
        nSem.release(1);
    }

    // printBuzz.run() outputs "buzz". By 5
    public void buzz(Runnable printBuzz) throws InterruptedException {
        fSem.acquire();
        if(index > n) return;
        index++;
        printBuzz.run();
        nSem.release(1);
    }

    // printFizzBuzz.run() outputs "fizzbuzz". 3 and 5 divisible.
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        fifSem.acquire();
        if(index > n) return;
        index++;
        printFizzBuzz.run();
        nSem.release(1);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        //for(int i = 1; i <= n;i++) {
        while(index <= n) {

             nSem.acquire();
             if (index%15 == 0) {
                 fifSem.release(1);
             if (index%3 == 0) {
                 tSem.release(1);
             } else if(index%5 == 0)
                 fSem.release(1);
             } else {
                 printNumber.accept(index);
                 index++;
                 nSem.release(1);
             }
        }
        fifSem.release(1);
        tSem.release(1);
        nSem.release(1);

    }


}
