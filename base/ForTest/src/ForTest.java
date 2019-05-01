/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.5.1
  Time: 15:33
  Info:
*/

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.5.1
 * Time: 15:33
 *  循环要外小内大 循环次数特别多时 可以把变量在循环外声明
 *  嵌套循环应该遵循“外小内大”的原则，这就好比你复制很多个小文件和复制几个大文件的区别。
 *  下面的代码并不能证明上面的观点 具体原因待查
 *  https://blog.csdn.net/liushuijinger/article/details/41546347
 */
public class ForTest {
	
	public static void main(String[] args){
		
		long start = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10_000_000; j++) {

			}
		}
		long end = System.nanoTime();
		System.out.println("外小内大  " +( end - start));

		

//		int i ,j;
		start = System.nanoTime();
		for ( int i = 0; i < 10_000_000; i++) {
			for ( int j = 0; j < 10; j++) {

			}
		}
	    end = System.nanoTime();
		System.out.println("外大内小 " +( end - start));
		
	}
	
}
