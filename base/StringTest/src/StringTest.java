/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.4.29
  Time: 18:22
  Info:
*/

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.4.29
 * Time: 18:22
 */
public class StringTest {
	
	public static void main (String[] args){
		
		Integer a = 127;
		Integer b = 127;
		System.out.println(a == b);
		
		
		String s = "string";
		String t = new String("string");
		System.out.println(s == t);
		
		
		System.out.println(Math.round(-2.5));
		
		
		String str3 = new String("abcd");
		String str4 = new String("abcd");
		System.out.println(str3 == str4);
		
	}
	
}
