/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.3.30
  Time: 17:00
*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest extends Thread {
	
	public  static int index;
	
	
	Lock lock = new ReentrantLock();
	
	@Override
	public synchronized void run() {
		lock.lock();
		try{
			for (int i = 0;i  < 100; i ++) {
				System.out.println(getName() + ":" + index++);
				
			}
		}catch (Exception e){
		
		}finally {
			lock.unlock();
		}
		
	}
}
