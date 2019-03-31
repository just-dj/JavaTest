/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 18.11.4
  Time: 10:45
  Info:
*/

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	
	public static  void main(String args[]) throws Exception{
	
//		Worker worker = new Worker();
//		MyThread myThread1 = new MyThread(worker);
//		MyThread myThread2 = new MyThread(worker);
//
//		Thread thread1 = new Thread(myThread1,"A");
//		Thread thread2 = new Thread(myThread2,"B");
//
//		thread1.start(); 5
//		thread2.start();
//		MyThread myThread = new MyThread();
//		for (int i = 0; i < 5; i++) {
//			Thread thread = new Thread(myThread,"thread" + i );
//			thread.start();
//		}


		Lock lock = new ReentrantLock();
		Condition a = lock.newCondition();
		Condition b = lock.newCondition();
		
		a.wait();
		
		new Thread().join();
		System.out.printf("");
		byte[] bytes = "A".getBytes("utf-8");
		String string = new String(bytes,"utf-8");
		System.out.println("A".getBytes("utf-8")[0]);
		
	}
	
	
}
