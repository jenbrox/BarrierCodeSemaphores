import java.util.concurrent.Semaphore;


class TestThread extends Thread {
    static Semaphore barrierSem = new Semaphore(0);
    static Semaphore countMutix = new Semaphore(1);
    static int count = 0;
    int tID;
}