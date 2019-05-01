/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.3.30
  Time: 16:56
*/

public class ThreadImpl implements Runnable {
	public  static int index;
	
	@Override
	public void run() {
		synchronized (this){
			for (int i = 0;i  < 100; i ++){
				System.out.println( ":" + index++);
			}
		}
	}
}
