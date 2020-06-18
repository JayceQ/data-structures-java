import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockAndTryLock {
    private ReentrantLock rlock = new ReentrantLock();

    private void lockTest() {
        long currentTime = System.currentTimeMillis();
        try {
            rlock.lock();

            while (System.currentTimeMillis() - currentTime <= 5000) {
                //assume do something
            }
            System.out.println("lockTest----current thread get the lock: " + Thread.currentThread().getName());
        } finally {
            rlock.unlock();
            System.out.println("lockTest----current thread release the lock:  " + Thread.currentThread().getName());
        }
    }

    private void tryLockTest() {

        long currentTime = System.currentTimeMillis();

//        while (System.currentTimeMillis() - currentTime <= 1000) {
//            //assume do something
//        }

        try {
            if (rlock.tryLock(4L, TimeUnit.SECONDS)) {
                try {
                    System.out.println("tryLockTest----current thread get the lock: " + Thread.currentThread().getName());

                } finally {
                    rlock.unlock();
                    System.out.println("tryLockTest----current thread release the lock: " + Thread.currentThread().getName());
                }

            } else {
                System.out.println("tryLockTest----current thread CAN NOT get the lock: " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            System.out.println("tryLockTest----current thread interrupt: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {

        TestLockAndTryLock lockAndTryLock = new TestLockAndTryLock();

        Thread lockThread = new Thread(
                () -> lockAndTryLock.lockTest(), "Lock-Thread");

        Thread tryLockThread = new Thread(
                () -> lockAndTryLock.tryLockTest(), "TryLock-Thread");

        tryLockThread.start();
        lockThread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "is interrupted now. ");
        }

        tryLockThread.interrupt();
    }

}
