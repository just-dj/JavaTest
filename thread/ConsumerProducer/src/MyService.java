/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 18.11.13
  Time: 20:07
  Info:
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	
	private  Lock lock = new ReentrantLock(true);
	
	private Condition condition =  lock.newCondition();
	
	private volatile List<Integer> list = new ArrayList <>();
	
	public void producer (){
	
		try {
			lock.lock();
			while ( list.size() > 0){
//				System.out.println("生产等待");
				lock.wait();
			}
			Thread.sleep(1000);
			list.add(new Random().nextInt());
			System.out.println( Thread.currentThread().getName() + " 新生产一个产品 剩余" + list.size());
			lock.notify();
		}catch (Exception e){
		
		}finally {
			lock.unlock();
		}
		
	}
	
	
	public void consumer(){
		
		try {
			lock.lock();
			while (list.size() < 1){
//				System.out.println("消费等待");
				lock.wait();
			}
			Thread.sleep(1000);
			list.remove(0);
			System.out.println(Thread.currentThread().getName() + " 消费一个产品 剩余" + list.size());
			lock.notify();
		}catch (Exception e){
		
		}finally {
			lock.unlock();
		}
		
		
	}
	
}
