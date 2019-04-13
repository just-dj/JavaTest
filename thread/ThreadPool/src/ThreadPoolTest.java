/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.4.13
  Time: 10:43
  Info:
*/

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.4.13
 * Time: 10:43
 */
public class ThreadPoolTest {
	
	
	public static void main(String[] args ){
		
//		Collections.emptyList();
		ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
//		ThreadPoolExecutor;
//		ScheduledThreadPoolExecutor;
		for(int i =1;i<=5;i++){
			final int index=i ;
			try{
				Thread.sleep(1000);
			}catch(InterruptedException  e ) {
				e.printStackTrace();
			}
			cacheThreadPool.execute(new Runnable(){
				@Override
				public void run() {
					System.out.println("第" +index +"个线程" +Thread.currentThread().getName());
				}
			});
		}
		
		
	}
	
	
	
	
}
