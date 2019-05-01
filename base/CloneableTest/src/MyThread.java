/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.3.30
  Time: 15:39
*/

public class MyThread extends  Thread {
		public  static int index;
	
	public static Object ob = new Object();
	
	@Override
	public synchronized void run() {
		
		synchronized (MyThread.class){
			for (int i = 0;i  < 100; i ++){
				System.out.println(getName() + ":" + index++);
			}
		}
	}
}
