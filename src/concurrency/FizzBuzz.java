package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/*
1195. Fizz Buzz Multithreaded
Medium

Write a program that outputs the string representation of numbers from 1 to n, however:

    If the number is divisible by 3, output "fizz".
    If the number is divisible by 5, output "buzz".
    If the number is divisible by both 3 and 5, output "fizzbuzz".

For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.

Suppose you are given the following code:

class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}

Implement a multithreaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed to four different threads:

    Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
    Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
    Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
    Thread D will call number() which should only output the numbers.


 */
public class FizzBuzz {
    private int n;
    private final Lock printLock;
    private final Condition fizzPrint;
    private final Condition buzzPrint;
    private final Condition fizzBuzzPrint;
    private final Condition nPrint;
    private volatile int cIndex = 1;


    public FizzBuzz(int n) {
        this.n = n;
        printLock = new ReentrantLock();
        fizzPrint = printLock.newCondition();
        buzzPrint = printLock.newCondition();
        fizzBuzzPrint = printLock.newCondition();
        nPrint = printLock.newCondition();

    }

    // printFizz.run() outputs "fizz". by 3
    public void fizz(Runnable printFizz) throws InterruptedException {
        printLock.lock();
        try {
            for(int i = 3;i <= n;i +=3) {
                while (cIndex%3 != 0) {
                    fizzPrint.await();
                }
                System.out.println(cIndex+"fizz");
                printFizz.run();
                cIndex++;
                if (cIndex%5 == 0) {
                    buzzPrint.signalAll();
                } else {
                    nPrint.signalAll();
                }
            }
        } finally {
            printLock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz". By 5
    public void buzz(Runnable printBuzz) throws InterruptedException {
        printLock.lock();
        try {
            for(int i = 5;i <= n;i +=5) {
                while (cIndex%5 != 0) {
                    buzzPrint.await();
                }
                System.out.println(cIndex+"buzz");
                printBuzz.run();
                cIndex++;
                if (cIndex%3 == 0) {
                    fizzPrint.signalAll();
                } else {
                    nPrint.signalAll();
                }
            }
        } finally {
            printLock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz". 3 and 5 divisible.
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        printLock.lock();
        try {
            for(int i = 15;i <= n;i +=15) {
                while (cIndex%15 != 0) {
                    fizzBuzzPrint.await();
                }
                System.out.println(cIndex+"fizzbuzz");
                printFizzBuzz.run();
                cIndex++;
                nPrint.signalAll();
            }
        } finally {
            printLock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        printLock.lock();
        try {
            for(int i = 1;i <= n;i++) {
                while (!isNormalNumber(cIndex)) {
                    nPrint.await();
                }
                i = cIndex;
                System.out.println(i+" n number");
                if (isNormalNumber(cIndex)) {
                    System.out.println(i+" print n number");
                    printNumber.accept(i);
                }
                cIndex++;
                if (cIndex%3 == 0) {
                    fizzPrint.signalAll();
                } else if(cIndex%5 == 0) {
                    buzzPrint.signalAll();
                } else if(cIndex%15 == 0)  {
                    fizzBuzzPrint.signalAll();
                }
            }
        } finally {
            printLock.unlock();
        }
    }

    private boolean isNormalNumber(int x) {
        return  !((cIndex%15 == 0)
                || (cIndex%3 == 0)
                || (cIndex%5 == 0));
    }
}
