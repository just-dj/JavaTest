/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.3.31
  Time: 16:03
  Info:
*/

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.3.31
 * Time: 16:03
 */
public class Sort {
	
	public static void main(String[] args){
		
		int[] arr = new int[10];
		for (int i = 0; i < 10;++i){
			arr[i] = new Random().nextInt(100);
		}
//		bubSort(arr);
		insertSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
	}
	
	static void bubSort(int[] arr){
		for(int i = 0;i < arr.length ; i++) {
			for(int j = 0;j < arr.length - i - 1; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	static void insertSort(int[] arr){
		for (int i = 0; i <arr.length - 1 ; i++) {
			int position = i;
			for (int j = i + 1; j <arr.length ; j++) {
				if (arr[j] > arr[position] && position!= 0){
					int temp = arr[j];
					arr[j] = arr[position];
					arr[position] = temp;
					position --;
				}
			}
		}
		
	}
	
	
	
	
}
