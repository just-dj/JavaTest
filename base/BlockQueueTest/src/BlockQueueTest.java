import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueTest {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<Object> queue = new LinkedBlockingQueue<>();


        new Thread(() -> {
            System.out.println("抢占线程1");
            test();
        }, "抢占锁线程1").start();

        Thread.sleep(10000);

        new Thread(() -> {
            System.out.println("抢占线程2， 这个县城应该被block住");
            test();
        }, "抢占锁线程2 block").start();


        new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "阻塞线程 wating").start();

        new Thread(() -> {
            while (true) {
                for (int i = 0; i < 10000000; i++) {
                    System.out.println(i);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "工作线程").start();
    }

    public static void test(){
        synchronized (BlockQueueTest.class){
            System.out.println("抢到锁");
            try {
                Thread.sleep(200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
