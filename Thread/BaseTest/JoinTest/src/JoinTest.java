/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.5.2
  Time: 14:32
  Info:
*/

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.5.2
 * Time: 14:32
 */
class JoinTester implements Runnable {
	
	private String name;
	
	public JoinTester(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.printf("%s begins: %s\n", name, new Date());
		try {
			Scanner scanner = new Scanner(System.in);
			scanner.next();
			TimeUnit.SECONDS.sleep(1000000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s has finished: %s\n", name, new Date());
	}
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new JoinTester("One"));
		thread1.setName("线程1");
		Thread thread2 = new Thread(new JoinTester("Two"));
		thread2.setName("线程2");
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		System.out.println("Main thread is finished");
	}
	
}
