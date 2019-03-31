/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.4.13
  Time: 17:13
*/

package top.justdj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class AspectJProxyTest {
	public static Waiter waiter = new NaiveWaiter();
	public static AspectJProxyFactory factory = new AspectJProxyFactory();
	
	public static void main(String[] args){
		factory.setTarget(waiter);
		factory.addAspect(PreGreetingAspect.class);
		Waiter proxy = factory.getProxy();
		proxy.greetTo("tom ");
		proxy.serverTo("tom");
	}
}
