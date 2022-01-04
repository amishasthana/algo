package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {

    private final int tCapacity;
    private final int[] eArray;
    private volatile int head = 0;
    private volatile int tail = 0;
    private volatile int cSize = 0;
    private final Lock bLock;
    private final Condition bEmpty;
    private final Condition bFull;

    public BoundedBlockingQueue(int capacity) {
        //cap = new AtomicInteger(capacity);
        tCapacity = capacity;
        eArray = new int[capacity];
        bLock = new ReentrantLock();
        bEmpty = bLock.newCondition();
        bFull = bLock.newCondition();
    }

    /*
        Enqueue element at head.
     */
    public void enqueue(int element) throws InterruptedException {
        bLock.lock();
        try {
            while (cSize == tCapacity) {
                bFull.await();
            }
            head = head%tCapacity;
            eArray[head] = element;
            head++;
            cSize++;
            bEmpty.signalAll();
        }finally {
            bLock.unlock();
        }
    }

    /*
        Dequeue from tail.
     */
    public int dequeue() throws InterruptedException {
        bLock.lock();
        try {
            while (cSize == 0) {
                bEmpty.await();
            }
            tail = tail%tCapacity;
            int elem = eArray[tail];
            tail++;
            cSize--;
            bFull.signalAll();
            return elem;
        }finally {
            bLock.unlock();
        }
    }

    public int size() {
         return cSize;
    }
}

