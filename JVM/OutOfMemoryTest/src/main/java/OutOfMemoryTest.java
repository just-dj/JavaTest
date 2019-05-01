/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 18.10.21
  Time: 11:33
  Info:
*/

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryTest {
	
	public static void main(String[] args){
		List<Object> list = new ArrayList<Object>();
		int i = 0;
		System.out.println("start");
		while(true){
			System.out.println(i++);
			int[] index = new int[1_0000_0000];
			list.add(index);
			
		}
	}
}

