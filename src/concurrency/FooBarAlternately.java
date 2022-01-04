package concurrency;
/*
1115. Print FooBar Alternately
Medium

Suppose you are given the following code:

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}

The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.



Example 1:

Input: n = 1
Output: "foobar"
Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.

Example 2:

Input: n = 2
Output: "foobarfoobar"
Explanation: "foobar" is being output 2 times.


 */

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooBarAlternately {
    private int n;
    AtomicLong atL = new AtomicLong(0);
    private final Lock aLock = new ReentrantLock();
    private final Condition fooCanPrint = aLock.newCondition();
    private final Condition barCanPrint = aLock.newCondition();



    public FooBarAlternately(int n) {
        this.n = n;

    }

    public void foo(Runnable printFoo) throws InterruptedException {


        for (int i = 0; i < n; i++) {
            aLock.lock();
            try {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                while (atL.get() % 2 == 1) {
                    fooCanPrint.await();
                }
                printFoo.run();
                atL.getAndIncrement();
                barCanPrint.signalAll();
            } finally {
                aLock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            aLock.lock();
            try {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                while (atL.get() % 2 == 0) {
                    barCanPrint.await();
                }
                printBar.run();
                atL.getAndIncrement();
                fooCanPrint.signalAll();
            } finally {
                aLock.unlock();
            }

        }
    }
}
