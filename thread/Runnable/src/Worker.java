/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 18.11.4
  Time: 14:42
  Info:
*/

public class Worker {
	
	public void work(){
		
		for (int i = 0; i < 100; i++) {
			System.out.println("异步 " + Thread.currentThread().getName() + "  " + i);
		}
		
		synchronized (this){
			for (int i = 0; i <50 ; i++) {
				System.out.println( "同步 " + Thread.currentThread().getName() + "  " + i );
			}
		}
		
	}
	
}
