import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

public class TtlRunableTest {

    public static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        AtomicReference atomicReference = new AtomicReference(new String("atomicReference测试"));

        threadLocal.set(new String("纳尼"));

        testChildThred();

        testTtl();
    }

    /**
     * 子任务提交任务到线程池
     */
    private static void testChildThred() {
        // holder本质上也是inheritAbleThreadLocal,会被赋值给子线程 这里间接地和holder产生了关联
        // holder 是一个特殊的inheritAbleThreadLocal 保存的是一个weakHashMap 持有所有ttlThreadLocal的引用
        new Thread(() -> {
            System.out.println("子线程内部值 ： " + threadLocal.get());
            executorService.submit(() -> {
                System.out.println("子线程提交任务到线程池 获取到的值 ： " + threadLocal.get());
            });
        }).start();
    }

    /**
     * 直接生成任务到线程池
     */
    private static void testTtl() {
        TtlRunnable runnable = TtlRunnable.get(new Runnable() {
            private String name = "任务";

            @Override
            public void run() {
                System.out.println("线程内部值 ： " + threadLocal.get());
            }
        });
        threadLocal.set("啊啊啊");
        executorService.submit(runnable);
    }
}
