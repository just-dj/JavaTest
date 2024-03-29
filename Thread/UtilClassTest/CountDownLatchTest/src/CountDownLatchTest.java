import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * Date: 19.5.4
 * Time: 13:15
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 感觉和join差不多 比join更强大一些
 * 等待指定数目的线程到达指定点后执行countDown方法，当所有线程都countDown后，执行await()的线程可以继续往下执行
 * 应用程序主线程启动的时候 等待数据库 缓存线程启动完成后再继续运行
 *
 */
public class CountDownLatchTest {
	
	/**
	 * 计数器，用来控制线程
	 * 传入参数2，表示计数器计数为2
	 */
	private final static CountDownLatch mCountDownLatch = new CountDownLatch(2);
	
	/**
	 * 示例工作线程类
	 */
	private static class WorkingThread extends Thread {
		private final String mThreadName;
		private final int mSleepTime;
		public WorkingThread(String name, int sleepTime) {
			mThreadName = name;
			mSleepTime = sleepTime;
		}
		
		@Override
		public void run() {
			System.out.println("[" + mThreadName + "] started!");
			try {
				Thread.sleep(mSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mCountDownLatch.countDown();
			System.out.println("[" + mThreadName + "] end!");
		}
	}
	
	/**
	 * 示例线程类
	 */
	private static class SampleThread extends Thread {
		
		@Override
		public void run() {
			System.out.println("[SampleThread] started!");
			try {
				// 会阻塞在这里等待 mCountDownLatch 里的count变为0；
				// 也就是等待另外的WorkingThread调用countDown()
				mCountDownLatch.await();
			} catch (InterruptedException e) {
			
			}
			System.out.println("[SampleThread] end!");
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 最先run SampleThread
		new SampleThread().start();
		// 运行两个工作线程
		// 工作线程1运行5秒
		new WorkingThread("WorkingThread1", 5000).start();
		// 工作线程2运行2秒
		new WorkingThread("WorkingThread2", 2000).start();
	}
	
}
