/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 18.11.13
  Time: 20:14
  Info:
*/

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	
	
	
	
	public static void main(String a[]) throws Exception{
	
		MyService myService = new MyService();
		
		new Thread(() -> {
			System.out.println("消费开始");
			while (true){
				myService.consumer();
			}
		},"消费者1").start();
		
		Thread.sleep(1000);

		Thread t = new Thread(() -> {
				System.out.println("生产1开始");
				while (true){
					myService.producer();
				}
		},"生产者1");
		t.start();
		t.setUncaughtExceptionHandler(( thread , e) -> {
			e.printStackTrace();
		});
		
		
		new Thread(() -> {
			System.out.println("生产2开始");
			while (true){
				myService.producer();
			}
		},"生产者2").start();
		
//		Thread.sleep(1000);
//
//		new Thread(() -> {
//			System.out.println("消费开始");
//			while (true){
//				myService.consumer();
//			}
//		}).start();
		
	}
	
	
	
}
