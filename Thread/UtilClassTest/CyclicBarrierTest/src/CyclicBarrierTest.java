import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * Date: 19.5.4
 * Time: 13:25
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 线程屏障 ，先到达的线程被阻塞，等待所有线程到达后继续运行，
 */

//	原文：https://blog.csdn.net/qq_38293564/article/details/80558157

public class CyclicBarrierTest {
	
	// 自定义工作线程
	private static class Worker extends Thread {
		private CyclicBarrier cyclicBarrier;
		
		public Worker(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
		public void run() {
			super.run();
			
			try {
				System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
				cyclicBarrier.await();
				System.out.println(Thread.currentThread().getName() + "开始执行");
				// 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
				Thread.sleep(4000);
				System.out.println(Thread.currentThread().getName() + "执行完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		int threadCount = 3;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
		

		for (int i = 0; i < threadCount; i++) {
			System.out.println("创建工作线程" + i);
			Worker worker = new Worker(cyclicBarrier);
			worker.start();
		}
	
	}

	



}
