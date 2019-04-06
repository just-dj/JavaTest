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
			arr[i] = 10 - i;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("\n");
//		bubSort(arr);
//		insertSort(arr);
//		selectSort(arr);
		quickSort(arr,0,arr.length - 1);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
	
	/**
	 * 稳定的 最好 - 最坏 - 平均 O(n) - O(n^2) - O(n^2)
	 * @param arr
	 */
	static void bubSort(int[] arr){
		for(int i = 0;i < arr.length ; i++) {
			for(int j = 0;j < arr.length - i - 1; j++) {
				if(arr[j] > arr[j+1]) {
					//只有当前元素比下一个数大才交换
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	/**
	 * 插入排序是稳定的 最好 - 最坏 - 平均 O(n) - O(n^2) - O(n^2)
	 * @param arr
	 */
	static void insertSort(int[] arr){
		if (null == arr || arr.length < 2){
			return;
		}
		//第0个元素已经排好序了
		for (int i = 1; i <arr.length; i++) {
			for (int j = i ; j > 0 ; --j) {
				if (arr[j] < arr[j - 1]){
					//如果前一个数字比现在指向的大 交换
					int temp = arr[j];
					arr[j] = arr[j - 1] ;
					arr[j - 1] = temp;
				}else {
					//大于等于就停止 所以是稳定的
					break;
				}
			}
		}
		
	}
	
	
	/**
	 * 选择排序是不稳定的 最好O(n^2) - 最坏O(n^2) - 平均O(n^2)
	 * 5 4 5 2 7 第一个5会被置换到2那里所以是不稳定的
	 * @param arr
	 */
	static void selectSort(int[] arr){
		if (null == arr || arr.length < 2){
			return;
		}
		for (int i = 0; i <arr.length  ; ++i) {
			int position = i ;
			for (int j = position; j < arr.length; ++j){
				if (arr[position] > arr[j]){
					position = j;
				}
			}
			if (position != i){
				int temp = arr[position];
				arr[position] = arr[i];
				arr[i] = temp;
			}
		}
	}
	
	
	/**
	 * 快速排序是不稳定的 分治+递归  最好O(nlgn) - 最坏O(n^2) - 平均O(nlgn)
	 *
	 * @param arr
	 */
	static void quickSort(int[] arr,int left,int right){
		if (left < right){
			int mid =  partition(arr,left,right);
			quickSort(arr,left,mid - 1);
			quickSort(arr,mid + 1,right);
		}
	}
	
	
	static int partition(int[] arr,int left, int right){
		int a = arr[left];
		while(left < right){
			while(arr[right] > a && right >left ){
				--right;
			}
			arr[left] = arr[right];
			while(arr[left] < a && left < right){
				++ left;
			}
			arr[right] = arr[left];
		}
		arr[left] = a;
		System.out.println("left " + left  +" right " + right);
		return left;
	}
	
	
	
}
