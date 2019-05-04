import java.util.concurrent.Exchanger;

/**
 * Created with IntelliJ IDEA.
 * Date: 19.5.4
 * Time: 18:36
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 用于线程间数据交换 如果第一个线程先执行exchange()方法,他会一直等待第二个线程也执行exchange()方法，
 * 当两个线程都达到同步点时，这两个线程就可以交换数据。
 */
public class ExchangerTest extends Thread {
	private Exchanger<String> exchanger;
	private String string;
	private String threadName;
	
	public ExchangerTest(Exchanger<String> exchanger, String string,
	                     String threadName) {
		this.exchanger = exchanger;
		this.string = string;
		this.threadName = threadName;
	}
	
	public void run() {
		try {
			System.out.println(threadName + ": " + exchanger.exchange(string));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Test {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
		ExchangerTest test1 = new ExchangerTest(exchanger, "string1",
				"thread-1");
		ExchangerTest test2 = new ExchangerTest(exchanger, "string2",
				"thread-2");
		
		test1.start();
		test2.start();
	}
}

