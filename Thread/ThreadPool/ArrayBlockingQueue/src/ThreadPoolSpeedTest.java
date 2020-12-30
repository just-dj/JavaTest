import java.util.concurrent.*;

public class ThreadPoolSpeedTest {

    public static void main(String[] args) {

        try {
            synchronized (""){
                "".wait(2000);
            }
        }catch (Exception e){

        }
        System.out.println("main");

        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(5) {
            @Override
            public boolean offer(Runnable runnable) {
                try {
                    put(runnable);
                    return true;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS, queue, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my thread");
            }
        });

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    synchronized (""){
                        System.out.println("第"+ finalI);
                        //                    Thread.sleep(1000);
                        "".wait(1000);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

}