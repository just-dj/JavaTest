import java.util.concurrent.*;

public class ThreadPoolSpeedTest {

    private static Account account = new Account();
    
    private static final Integer times = 100;
    
    public static void main(String[] args) throws Exception{
      //这里比较一下两种线程池的速度
        //times 100 task sleep 100ms 正常耗时约为10000

        /*
        cached thread pool
         */
        //开启100个线程 耗时119ms左右
//        testCachedThreadPool();

//        Thread.sleep(100_000_000);
        /*
            fixed thread pool
         */
        //开启100个线程 耗时110ms左右
//        testFixedThreadPool(100);

        //开启20个线程 耗时505ms左右
//        testFixedThreadPool(20);

        /*
        自建thread pool
         */
        //开启4个线程 耗时2588ms左右
        testThreadPool();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                100,
                100,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue <Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("自己创建的线程 " + System.currentTimeMillis());
                        return thread;
                    }
                });

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        Thread.sleep(3000L);
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(100_000_000);
    }
    
    
    //times 为 100的时候 cachedThreadPool基本上创建了100个线程
   static void testCachedThreadPool(){
        long start = System.currentTimeMillis();
        ThreadPoolExecutor cachedThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0;i < times;++i){
            cachedThreadPool.execute(new AddTask());
        }
       System.out.println("cachedThreadPool 已完成task 数目 " + cachedThreadPool.getCompletedTaskCount());
        cachedThreadPool.shutdown();
        while(!cachedThreadPool.isTerminated());
        System.out.println("cachedThreadPool 执行时间 " + (System.currentTimeMillis() - start));
       System.out.println("cachedThreadPool 所有完成task 数目 " + cachedThreadPool.getCompletedTaskCount());
        System.out.println("cachedThreadPool all task complete!");
    }
    
    //times 为 100的时候 cachedThreadPool创建了5个线程
   static void testFixedThreadPool(int n){
        long start = System.currentTimeMillis();
        ThreadPoolExecutor fixedService = (ThreadPoolExecutor) Executors.newFixedThreadPool(n);
        for (int i = 0; i < times; i++) {
            fixedService.execute(new AddTask());
        }
        
       System.out.println("fixedThreadPool 已完成task 数目 " + fixedService.getCompletedTaskCount());
        fixedService.shutdown();
        while(!fixedService.isTerminated());
        System.out.println("fixedThreadPool 执行时间 " + (System.currentTimeMillis() - start));
       System.out.println("fixedThreadPool 所有完成task 数目 " + fixedService.getCompletedTaskCount());
        System.out.println("fixedThreadPool all task complete! " );
    }
    
    //自己创建线程池
    static void testThreadPool(){
        long start = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                4,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue <Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("自己创建的线程 " + System.currentTimeMillis());
                        return thread;
                    }
                });
        for (int i = 0; i < times; i++) {
            threadPoolExecutor.execute(new AddTask());
        }
        System.out.println("threadPool 已完成task 数目 " + threadPoolExecutor.getCompletedTaskCount());
        threadPoolExecutor.shutdown();
        while(!threadPoolExecutor.isTerminated());
        System.out.println("threadPool 执行时间 " + (System.currentTimeMillis() - start));
        System.out.println("threadPool 所有完成task 数目 " + threadPoolExecutor.getCompletedTaskCount());
        System.out.println("threadPool all task complete! " );
    }
    
    private static class AddTask implements Runnable{
        @Override
        public void run() {
            account.deposit(1);
        }
    }
    
    //计数类 同时让该线程休眠
    private static class Account{
        public   void deposit(double amount){
//            System.out.println("加一 线程名 " + Thread.currentThread().getName() + " 线程id " + Thread.currentThread().getId());
            try{
                //模拟处理花费10ms
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

