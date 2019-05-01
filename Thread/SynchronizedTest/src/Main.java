import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static Account account = new Account();
    
    public static void main(String[] args) {
	// write your code here
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        for (int i = 0;i < 100;++i){
            executorService.execute(new AddAPennyTast());
        }
        
        executorService.shutdown();
        while(!executorService.isShutdown());
        
        System.out.println("what id balance" + account.getBalance());
    }
    
    
    private static class AddAPennyTast implements Runnable{
        @Override
        public void run() {
//            synchronized (account){
                account.deposit(1);
//            }
            
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
        
        public  synchronized  void deposit(double amount){
            double newBalance = balance + amount;

            try{
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }

            balance = newBalance;
//            balance+=amount;
        }
    }
}

