import java.util.concurrent.*;

public class SynchronizedTest {

    private static Account account = new Account();
    
    private static final Integer times = 100;
    
    public static void main(String[] args) {
        ThreadPoolExecutor cachedThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0;i < times;++i){
            cachedThreadPool.execute(new AddTask());
        }
        cachedThreadPool.shutdown();
        while(!cachedThreadPool.isTerminated());
        System.out.println("cachedThreadPool all task complete! result is " + account.getBalance());
    }
    
    
    
    private static class AddTask implements Runnable{
        @Override
        public void run() {
            account.deposit(1);
        }
    }
    
    //计数类 同时让该线程休眠
    private static class Account{
        private double balance = 0;
        public double getBalance() {
            return balance;
        }
        public void setBalance(int balance) {
            this.balance = balance;
        }
        
        //d对象锁
        public  synchronized  void deposit(double amount){
//            System.out.println("加一 线程名 " + Thread.currentThread().getName() + " 线程id " + Thread.currentThread().getId());
            double newBalance = balance + amount;
            try{
                //模拟处理花费10ms
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
            balance = newBalance;
        }
    }
}

