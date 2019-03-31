/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 18.11.4
  Time: 10:38
  Info:
*/

public class MyThread implements Runnable {
	
	
	private static int i = 0;
	
	private static Object object = new Object();
	
	private Worker worker;
	
	MyThread() {
	}
	
	MyThread(Worker worker) {
		this.worker = worker;
	}
	
	@Override
	public void run() {
//		worker.work();
//		synchronized (object){
//			++i;
//			System.out.println(	Thread.currentThread().getName()  + "当前值 "+ i);
//			try {
//				Thread.sleep(1000);
//			}catch (Exception e){
//
//			}



//		try {
//			Thread.sleep(1000);
//		}catch (Exception e){
//
//		}
		
		while (true)
		{
		
		}
		
	}
	
}
