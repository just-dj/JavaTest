/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.5.3
  Time: 9:54
  Info:
*/

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.5.3
 * Time: 9:54
 */
public class ReadWriteLockTest {
	
	
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public static void main(String[] args)  {
		final ReadWriteLockTest test = new ReadWriteLockTest();
		
		new Thread(){
			public void run() {
				test.get(Thread.currentThread());
			};
		}.start();
		
		new Thread(){
			public void run() {
				test.get(Thread.currentThread());
			};
		}.start();
		
	}
	
	public void get(Thread thread) {
//		要注意的是，如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。
//　　如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁。
		rwl.readLock().lock();
		try {
			long start = System.currentTimeMillis();
			
			while(System.currentTimeMillis() - start <= 1) {
				System.out.println(thread.getName()+"正在进行读操作");
			}
			System.out.println(thread.getName()+"读操作完毕");
		} finally {
			rwl.readLock().unlock();
		}
	}


}
