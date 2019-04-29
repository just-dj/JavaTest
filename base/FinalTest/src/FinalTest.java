/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.4.13
  Time: 11:36
  Info:
*/

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.4.13
 * Time: 11:36
 * @DESC: 在final修饰的方法参数中，如果修饰的是基本类型，那么在这个方法的内部，基本类型的值是不能够改变的，但
 * 是如果修饰的是引用类型的变量，那么就需要注意了，引用类型变量所指的引用是不能够改变的，
 * 但是引用类型变量的值是可以改变的。
 */
public class FinalTest {
	public static void main(String hh[]) {
		int i = 1;
		System.out.println(i);
		checkInt(i);
		System.out.println(i);
	}
	
	/**
	 * 对于基本类型，基本类型的值在方法内部是不能够改变的
	 *
	 * @param i
	 */
	public static void checkInt(final int i) {
		
		//编译不通过，final修饰的局部变量i的值是不能够改变的
//		    i=10;
	
	}
}
