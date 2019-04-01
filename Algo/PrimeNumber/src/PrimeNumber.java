/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.3.31
  Time: 15:24
  Info:
*/

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.3.31
 * Time: 15:24
 */
public class PrimeNumber {
	
	static final int size = 1000000;
	
	public static void main(String[] a){
	
		long start = System.currentTimeMillis();
		for (int i = 1;i < size;++i){
			if (isPrime(i)){
//				System.out.println(i);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	static boolean isPrime(int num){
		if (num == 1){
			return true;
		}
		int sqrtNum = (int)Math.sqrt(num);
		for (int i = 2; i <= sqrtNum;++i){
			if (num % i == 0){
				return false;
			}
		}
		return true;
	}
	
}
