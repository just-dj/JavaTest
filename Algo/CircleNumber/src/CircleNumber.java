/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.3.31
  Time: 15:41
  Info:
*/

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.3.31
 * Time: 15:41
 */
public class CircleNumber {
	
	public static void main(String[] args){
		for (int i = 1; i < 10000;++i){
			if (isCircleNumber(i)){
				System.out.println(i);
			}
		}
	}
	
	
	static boolean isCircleNumber(int num){
		if (num <= 10 ){
			return false;
		}
		int result = 0;
		int oldValue = num;
		while (num != 0){
			result = result * 10 + num % 10;
			num = num/10;
		}
		if (result == oldValue){
			return true;
		}else {
			return false;
		}
		
		
	}
	
}
