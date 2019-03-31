package top.justdj;

public class Main {
    private int num = 10; // ���õ�ǰ��Ʊ��
    private Object synObj = new Object();
    private static boolean isRun = true;
    
    public Main() {
        // �����߱���Ʊ���ܵ������
        Sell sellRunnable = new Sell();
        Refund refundRunnable = new Refund();
        
       
        Thread sellA = new Thread(sellRunnable, "SellA");
        Thread sellB = new Thread(sellRunnable, "SellB");
        Thread sellC = new Thread(sellRunnable, "SellC");
        Thread sellD = new Thread(sellRunnable, "SellD");

        Thread refundA = new Thread(refundRunnable,"RefundA");
        Thread refundB = new Thread(refundRunnable,"RefundB");
        Thread refundC = new Thread(refundRunnable,"RefundC");
        Thread refundD = new Thread(refundRunnable,"RefundD");

        sellA.start();
        sellB.start();
//        sellC.start();
//        sellD.start();
        refundA.start();
        refundB.start();
        refundC.start();
        refundD.start();
    }
    
    /**
     * ��ʾ��Ʊ�߳���(�ڲ���)
     * @author Administrator
     */
    private class Sell implements Runnable {
        
        @Override
        public void run() {
            while (isRun) {
                synchronized (synObj) {
                    // �����Ʊ����
                    if (num > 0) {
                        // ����һ��Ʊ
                        System.out.println(Thread.currentThread().getName() + "-tickets leaving: " + --num);
                        synObj.notifyAll();
                    }
                    else{ // ���ûƱ����������Ʊ����ע���������Ʊ�߳̾Ͳ�����ô�����ˣ�
                        System.out.println(Thread.currentThread().getName() + "---wait ");
                        //票卖光啦 等会看看有没有人退票
                        try{
                            synObj.wait();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                // ����һ��Ʊ����Ϣ0.1��
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private class Refund implements Runnable{
    
        @Override
        public void run() {
            while (isRun) {
                synchronized (synObj) {
                    // �����Ʊ����
                    if (num >= 10) {
                        // ����һ��Ʊ
                        try{
                            System.out.println(Thread.currentThread().getName() + "---wait");
                            //没有票被卖出
                            synObj.notify();
                        }catch (Exception e){
                        
                        }
                    }
                    else{ // 退票
                        System.out.println(Thread.currentThread().getName() + "-tickets leaving: " + ++num);
                        //退票了 叫醒休眠的买票程序
                        try{
                            synObj.wait();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                // 线程休眠100毫秒
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Main t = new Main(); // ʵ���������
        // 5���ر�Ӧ�ó���
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        isRun = false;
        // ����Ӧ�ó���
        
        System.out.println("Done");
        System.exit(0);
        
    }
    
}