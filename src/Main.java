import java.util.concurrent.Semaphore;
public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            TestThreadRunnable ttr = new TestThreadRunnable(i);
            Thread thread = new Thread(ttr);
            thread.start();

        }
    }
}