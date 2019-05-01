/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.4.13
  Time: 10:43
  Info:
*/

import java.io.Serializable;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.4.13
 * Time: 10:43
 */
public class ThreadPoolTest {
	
	private static int produceTaskSleepTime = 2 * 1000;
	private static int produceTaskMaxNumber = 10;
	
	public static void main(String[] args) {
		// 构造一个线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue <Runnable>(3), new ThreadPoolExecutor.AbortPolicy());
		for (int i = 1; i <= produceTaskMaxNumber; i++) {
			try {
				// 产生一个任务，并将其加入到线程池
				String task = "task@ " + i;
				System.out.println("put " + task);
				threadPool.execute(new ThreadPoolTask(task));
				// 便于观察，等待一段时间
//				Thread.sleep(produceTaskSleepTime / 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		for (int i = 0; i < 1000; i++) {
//			try {
//				Thread.sleep(1000);
//				System.out.println( i + " 当前活跃线程数 " + threadPool.getActiveCount());
//			}catch (Exception e){
//
//			}
//
//		}
		
		
		System.out.println("main 线程结束 ");
		
		while (true){
			Scanner scanner = new Scanner(System.in);
			int temp = scanner.nextInt();
			switch (temp){
				case 0:
					System.out.println("关闭线程池");
					threadPool.shutdown();
					break;
				case 1:
					System.out.println("添加新任务");
					threadPool.execute(new ThreadPoolTask("新任务 " + System.currentTimeMillis()));
					break;
				case 2:
					System.out.println("添加10个新任务");
					try {
						// 产生一个任务，并将其加入到线程池
						String task = "批量task@ " + System.currentTimeMillis();
						System.out.println("put " + task);
						threadPool.execute(new ThreadPoolTask(task));
						// 便于观察，等待一段时间
//				Thread.sleep(produceTaskSleepTime / 1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				
			}
		}
		
		
	}
}

/**
 * 线程池执行的任务
 */
class ThreadPoolTask implements Runnable, Serializable {
	private static final long serialVersionUID = 0;
	private static int consumeTaskSleepTime = 2000;
	// 保存任务所需要的数据
	private Object threadPoolTaskData;
	
	ThreadPoolTask(Object tasks) {
		this.threadPoolTaskData = tasks;
	}
	
	public void run() {
		// 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
		System.out.println("start .." + threadPoolTaskData + "   " + Thread.currentThread().getName());
		
		try {
			// //便于观察，等待一段时间
			Thread.sleep(consumeTaskSleepTime  );
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadPoolTaskData = null;
	}
	
	public Object getTask() {
		return this.threadPoolTaskData;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("线程结束  "  + Thread.currentThread().getName());
	}
}
