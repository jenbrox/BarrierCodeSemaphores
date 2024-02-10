import java.util.concurrent.Semaphore;

//using Implements instead of Extends Thread
public class TestThreadRunnable implements Runnable{

    //statically declared variables means it is shared by the entire class, all objects can edit and view this one variable
    //will stop threads and not let them pass until all are ready, we start it with zero permits
   static Semaphore barrierSem = new Semaphore(0);
   //mutually exclusive access to count, so only one thread can access at a time. One permit bc only one thread can access count
   static Semaphore countMutex = new Semaphore(1);
    //
   static int count = 0;
   //Unique to every thread and object
   int tID;

    public TestThreadRunnable(int ID){
        tID = ID;
    }

    @Override
    public void run(){
        // The current thread attempts to obtain the permit to access the count variable.
        // If the permit is already in use, put the thread in the wait queue until all previous threads
        // that requested access have accessed the mutex.
        // AKA:
        // Given a number of permits, if they are available, and returns immediately, reducing the number of
        // available permits by the given amount. If the current thread is interrupted while waiting for a permit then
        // it will continue to wait.
        countMutex.acquireUninterruptibly();
        // update the count of threads that have started
        count++;

        // if this thread is the final one to arrive, release the barrier permits equal to the number of threads
        // so that they can all begin executing at once.
        if (count == 5) {
            System.out.println("Thread" + tID + " is the last thread to arrive and will open the barrier.");
            barrierSem.release(5);

            // print that all the threads are not present yet
        } else {
            System.out.println("Thread " + tID + " has arrived and is waiting for the barrier to be opened.");
        }
        // The current thread releases the permit to the mutex that allows access to the count variable.
        countMutex.release();
        // The current thread attempts to pass the barrier. This occurs when the final thread has incremented the count
        // variable and releases all the permits.
        // barrierSem will only have a permit available once the above if was hit
        barrierSem.acquireUninterruptibly();
        System.out.println("Thread" + tID + " made it past the barrier!");

    }


}
