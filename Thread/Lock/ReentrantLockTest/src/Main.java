import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author justdj
 * @create 2020/3/1 10:12 下午
 */
public class Main {


    public static void main(String[] args) {
        ReentrantLock reentrantLock  = new ReentrantLock();


        Thread thread  = new TestThread("thread1", reentrantLock);
        thread.start();

    }

    static class TestThread extends Thread{

        private ReentrantLock reentrantLock;

        public TestThread(String name, ReentrantLock reentrantLock) {
            super(name);
            this.reentrantLock = reentrantLock;
        }

        @Override
        public void run() {
            try {
                reentrantLock.lock();
                System.out.println(this.getName());
            }finally {
                reentrantLock.unlock();
            }

        }
    }

}
