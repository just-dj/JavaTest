package top.justdj;

public class Main {
    
    private int num;
    
    ThreadLocal<Integer> threadLocal = new ThreadLocal (){
        @Override
        protected Object initialValue() {
            return 0;
        }
    };
    
    public int getNum(){
        threadLocal.set(threadLocal.get() + 1);
        return threadLocal.get();
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        new Thread(new TestThread(main)).start();
        new Thread(new TestThread(main)).start();
        
    }
}

class TestThread implements Runnable{
    
    private Main main;
    
    public TestThread(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100 ; ++ i){
            System.out.println("thread["+Thread.currentThread().getName()+"]"+main.getNum());
        }
    }
}
