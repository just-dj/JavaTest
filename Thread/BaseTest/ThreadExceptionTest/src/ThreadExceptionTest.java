/**
 * Created with IntelliJ IDEA.
 * Date: 19.5.3
 * Time: 16:33
 *
 * @author
 * @email top90982@gmail.com
 * @Desc  原文 https://blog.csdn.net/yizhenn/article/details/70284090
 * 如果一个线程没有显式的设置它的UncaughtExceptionHandler，JVM就会检查该线程所在的线程组是否设置了UncaughtExceptionHandler，如果已经设置，就是用该UncaughtExceptionHandler；否则查看是否在Thread层面通过静态方法setDefaultUncaughtExceptionHandler()设置了UncaughtExceptionHandler，如果已经设置就是用该UncaughtExceptionHandler；如果上述都没有找到，JVM会在对应的console中打印异常的堆栈信息
 *
 * 对于线程组和线程池的线程类异常处理还没测试
 */
public class ThreadExceptionTest {

//	public void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh) ;
	
	public static void main(String[] args) throws InterruptedException {
		//默认的ExceptionHandler
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("static thread exception handler -- " + t.getName());
			}
		});
		final Thread t1 = new Thread(new ThreadTaskWithHandler(), "t1");
		t1.start();
		
		final Thread t2 = new Thread(new ThreadTaskNoHandler(), "t2");
		t2.start();
		
	}
	
	private static final class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("explicit exception handler -- " + t.getName());
		}
	}
	
	private static final class ThreadTaskWithHandler implements Runnable {
		
		@Override
		public void run() {
			//线程指定的ExceptionHandler 会把默认的ExceptionHandler覆盖
			Thread.currentThread().setUncaughtExceptionHandler(new ThreadExceptionHandler());
			System.out.println(12 / 0);
		}
	}
	
	private static final class ThreadTaskNoHandler implements Runnable {
		@Override
		public void run() {
			System.out.println(12 / 0);
		}
	}

}
