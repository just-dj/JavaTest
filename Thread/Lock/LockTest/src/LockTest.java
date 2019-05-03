/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.5.2
  Time: 21:11
  Info:
*/

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.5.2
 * Time: 21:11
 * ReentrantLock 实现了Lock接口
 */
public class LockTest {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	//注意这个地方 声明为全局变量保证了各个线程竞争的是同一把锁
	private Lock lock = new ReentrantLock();

	public static void main(String[] args)  {
		//测试Lock  unLock
//		testLock();
		//测试 tryLock
		tesTryLock();
	}
	
	


	
	
	static void testLock(){
		final LockTest test = new LockTest();
		
		new Thread(){
			@Override
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
	}
	
	static void tesTryLock(){
		final LockTest test = new LockTest();
		
		new Thread(){
			@Override
			public void run() {
				test.insertWithTryLock(Thread.currentThread());
			};
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				test.insertWithTryLock(Thread.currentThread());
			};
		}.start();
	}
	
	public void insert(Thread thread) {
		//注意一定要是同一把锁
		lock.lock();
		
//	   tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
//  如果获取失败（即锁已被其他线程获取），则返回false，
//  也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
		try {
			System.out.println(thread.getName()+"得到了锁");
			for(int i=0;i<5;i++) {
				arrayList.add(i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			System.out.println(thread.getName()+"释放了锁");
			lock.unlock();
		}
	}
	
	public void insertWithTryLock(Thread thread) {
//	   tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
//  如果获取失败（即锁已被其他线程获取），则返回false，
//  也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
		if (lock.tryLock()){
			try {
				System.out.println(thread.getName()+"得到了锁");
				for(int i=0;i<5;i++) {
					arrayList.add(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				System.out.println(thread.getName()+"释放了锁");
				lock.unlock();
			}
		}else {
			System.out.println(thread.getName()+"获取锁失败");
		}
	
	}
	
}
