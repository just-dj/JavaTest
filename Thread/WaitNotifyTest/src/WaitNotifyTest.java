/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.5.2
  Time: 13:38
  Info:
*/

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.5.2
 * Time: 13:38
 */


//作者：CoderWang648
//来源：CSDN
//原文：https://blog.csdn.net/weixin_43184769/article/details/89599632

public class WaitNotifyTest {
	
	public static void main(String[] args) {
		
//		testOne();
		
//		testTwo();
		
		testThree();
	}
	
	
	/**
	 * 简单测试wait notify
	 */
	static void testOne(){
		ThreadA t1 = new ThreadA("t1");
		
		//main首先获取了ti
		synchronized(t1) {
			try {
				// 启动“线程t1”
				System.out.println(Thread.currentThread().getName()+" start t1");
				t1.start();
				// 主线程等待t1通过notify()唤醒。
				System.out.println(Thread.currentThread().getName()+" wait()");
				t1.wait();
				System.out.println(Thread.currentThread().getName()+" continue");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 简单测试2
	 */
	static void testTwo(){
		ThreadB demo = new ThreadB("我自己的测试线程");
		demo.start();
		//这里睡100毫秒是为了让出cpu执行时间给t1
		try {
			Thread.sleep(100);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		synchronized(ThreadB.obj) {
			System.out.println(Thread.currentThread().getName()+" start");
			try {
				//模拟耗时5秒 让处于等待池中的demo线程多等一会
				Thread.sleep(5000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" end");
		}
	}
	
	
	/**
	 *
	 */
	static void testThree(){
		ThreadC demo1 = new ThreadC("demoThread1");
		ThreadC demo2 = new ThreadC("demoThread2");
		ThreadC demo3 = new ThreadC("demoThread3");
		demo1.start();
		demo2.start();
		demo3.start();
		
		try {
			Thread.sleep(100);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		synchronized(ThreadC.obj) {
			System.out.println(Thread.currentThread().getName()+" start");
			System.out.println(Thread.currentThread().getName()+" notify");
			ThreadC.obj.notify();
			try {
				Thread.sleep(2000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" end");
		}
	}
}




class ThreadA extends Thread{
	
	public ThreadA(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+" call notify()");
			// 唤醒当前的wait线程
			notify();
		}
	}
}


class ThreadB extends Thread {
	public static final Object obj = new Object();
	
	public ThreadB(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		synchronized (obj) {
			try {
				System.out.println(Thread.currentThread().getName()+" start");
				//这里demo线程让出锁，进入等待池
				obj.wait(1000);
				System.out.println(Thread.currentThread().getName()+" continue");
				System.out.println(Thread.currentThread().getName()+" end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


class ThreadC extends Thread {
	public static final Object obj = new Object();
	
	public ThreadC(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		synchronized (obj) {
			try {
				System.out.println(Thread.currentThread().getName()+" start");
				obj.wait();
				System.out.println(Thread.currentThread().getName()+" continue");
				System.out.println(Thread.currentThread().getName()+" end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
