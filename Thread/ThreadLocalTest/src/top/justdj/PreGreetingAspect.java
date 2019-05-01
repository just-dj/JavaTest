/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.4.13
  Time: 17:05
*/

package top.justdj;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PreGreetingAspect {
	
	@Before("execution(* greetTo(..))")
	public void beforeGreeting(){
		System.out.println("How are you!");
	}
}
