public class Main {

    public static void main(String[] args) throws Exception{
        
        House house = new House("大鹏","会飞");
        System.out.println(house);
//        方法1
//        new MyThread().start();
//        new MyThread().start();
//        new MyThread().start();


//        方法2
//        Runnable runnable = new ThreadImpl();
//        new Thread(runnable).run();
//        new Thread(runnable).run();
//        方法3
        new LockTest().run();
        new LockTest().run();
        
    }
}
