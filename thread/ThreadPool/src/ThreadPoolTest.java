/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.4.13
  Time: 10:43
  Info:
*/

import java.io.Serializable;
import java.util.Collections;
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
	
	private static int produceTaskSleepTime = 2;
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
				Thread.sleep(produceTaskSleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("main 线程结束 ");
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
			Thread.sleep(consumeTaskSleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadPoolTaskData = null;
	}
	
	public Object getTask() {
		return this.threadPoolTaskData;
	}
}
