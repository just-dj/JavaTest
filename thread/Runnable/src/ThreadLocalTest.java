/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 18.11.13
  Time: 11:01
  Info:
*/

public class ThreadLocalTest {
	
//	public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	
	public static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal <>();
	
	public static void main(String args[]){
		threadLocal.set(new Integer(123));
		
		Thread thread = new MyThread();
		thread.start();
		
		System.out.println("main = " + threadLocal.get());
	}
	
	static class MyThread extends Thread{
		@Override
		public void run(){
			System.out.println("MyThread = " + threadLocal.get());
		}
	}
	
	
}
