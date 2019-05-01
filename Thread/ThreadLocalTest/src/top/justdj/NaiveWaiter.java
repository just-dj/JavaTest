/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.4.13
  Time: 17:09
*/

package top.justdj;

public class NaiveWaiter implements Waiter {
	
	public void greetTo(String name){
		System.out.println("NaiveWaiter greet to " + name);
	}
	
	public void serverTo(String name){
		System.out.println("NaiveWaiter server to " + name);
	}
	
}
