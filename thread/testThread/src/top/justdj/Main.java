package top.justdj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        Runnable printA = new PrintChar('a',100);
//        Runnable printB = new PrintChar('b',100);
//        Runnable print100 =new PrintNum(100);
//
//        Thread t1 =new Thread(printA);
//        Thread t2 = new Thread(printB);
//        Thread t3 = new Thread(print100);
//
//        t1.start();
//        t2.start();
//        t3.start();
    
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new PrintChar('a',100));
        executorService.execute(new PrintChar('b',100));
        executorService.execute(new PrintNum(100));
        executorService.shutdown();
    }
}

class PrintChar implements Runnable{
    private char charToPrint;
    private int times;
    
    public PrintChar(char c,int t) {
        charToPrint = c;
        times  = t;
    }
    
    @Override
    public void run() {
        for (int i = 0;i <times;i++)
            System.out.println(charToPrint);
    }
}
class PrintNum implements Runnable{
    private int times;
    
    public PrintNum(int t) {
        times  = t;
    }
    
    @Override
    public void run() {
        for (int i = 0;i <times;i++)
            System.out.println(" " + times);
    }
}
