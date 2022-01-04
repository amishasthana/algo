package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLock {
    private final Lock centralLock;
    private final Condition writeCondition;
    private final Condition readCondition;
    private volatile int rCount = 0;
    private volatile boolean isWriteLockHeld = false;
    private final InternalLockThread lockThread;


    static class InternalLockThread implements Runnable {



        @Override
        public void run() {

        }
    }

    public ReadWriteLock() {
        centralLock = new ReentrantLock();
        writeCondition = centralLock.newCondition();
        readCondition = centralLock.newCondition();
        lockThread = new InternalLockThread();
        lockThread.run();
    }

    /*
        Check if any read or write lock is held.
        If No hold the lock.
        Other threads for this lock should just wait.
     */
    public void getWriteLock() throws InterruptedException {
        /*
            Basically in loop we will wait.  If signal, we will check that its possible to get out.
            This should generally allow only one write thread to go to lock.
            Please note its not 100% gurantee.
            The final line of defence is lock method itself, which will allow only one write lock.
         */
        while ((rCount != 0) && !isWriteLockHeld) {
            writeCondition.await();
        }
        centralLock.lock();
        isWriteLockHeld = true;
    }

    /*
     Release write lock.
     Signal all who might be waiting for read or write lock.
     Do we need to check if someone holds the lock? Because otherwise anyone can call this method to release it.
 */
    public void releaseWriteLock() {
        try {
            if (isWriteLockHeld) {
                centralLock.unlock();
            }
            readCondition.signalAll();
            writeCondition.signalAll();
        } catch(IllegalMonitorStateException  e) {
            System.out.println("This thread dont hold the lock. Most probably nobody does");
        }
    }

    /*
          Check if any one has write lock. If yes, block.
          If not, need to acquire one.(The thread which acquire lock need to hold it too. So what to do?
             a) Dont acquire, but somehow block.
             b) Have a seperate thread.
          Increment lock count.
     */
    public void getReadLock() throws InterruptedException {
        while (isWriteLockHeld) {
            readCondition.await();
        }
        if (rCount == 0) {
            centralLock.lock();
        }
        rCount++;
        readCondition.signalAll();
    }

    /*
       Decrement the lock
     */
    public void releaseReadLock() throws InterruptedException {
        rCount--;
        readCondition.await();
        if (rCount == 0) {
            //writeLock.unlock();
            writeCondition.signalAll();
        }
        readCondition.signalAll();
    }





}
