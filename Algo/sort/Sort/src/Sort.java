/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.3.31
  Time: 16:03
  Info:
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

    public static void main(String[] args) {

        int[] arr = new int[10];
        for (int i = 0; i < 10; ++i) {
            arr[i] = i;
        }
        print(arr);
        System.out.print("\n");
        //		bubSort(arr);
        //		insertSort(arr);
        //		selectSort(arr);
        //        quickSort(arr, 0, arr.length - 1);
        stackSort(arr);
        print(arr);
    }

    /**
     * 稳定的 最好 - 最坏 - 平均 O(n) - O(n^2) - O(n^2)
     *
     * @param arr
     */
    static void bubSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    //只有当前元素比下一个数大才交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序是稳定的 最好 - 最坏 - 平均 O(n) - O(n^2) - O(n^2)
     *
     * @param arr
     */
    static void insertSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        //第0个元素已经排好序了
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; --j) {
                if (arr[j] < arr[j - 1]) {
                    //如果前一个数字比现在指向的大 交换
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    //大于等于就停止 所以是稳定的
                    break;
                }
            }
        }

    }

    /**
     * 选择排序是不稳定的 最好O(n^2) - 最坏O(n^2) - 平均O(n^2)
     * 5 4 5 2 7 第一个5会被置换到2那里所以是不稳定的
     *
     * @param arr
     */
    static void selectSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; ++i) {
            int position = i;
            for (int j = position; j < arr.length; ++j) {
                if (arr[position] > arr[j]) {
                    position = j;
                }
            }
            if (position != i) {
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
    static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = partition(arr, left, right);
            quickSort(arr, left, mid - 1);
            quickSort(arr, mid + 1, right);
        }
    }

    static int partition(int[] arr, int left, int right) {
        int a = arr[left];
        while (left < right) {
            while (arr[right] > a && right > left) {
                --right;
            }
            arr[left] = arr[right];
            while (arr[left] < a && left < right) {
                ++left;
            }
            arr[right] = arr[left];
        }
        arr[left] = a;
        System.out.println("left: " + left + " right: " + right + "  ");
        return left;
    }

    public static void stackSort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            buildStack(arr, i);
            adjust(arr, 0, i - 1);
        }
    }

    static void buildStack(int[] arr, int length) {
        for (int i = length / 2 - 1; i >= 0; --i) {
            if (i * 2 + 2 < length && arr[i * 2 + 2] > arr[i]) {
                adjust(arr, i, i * 2 + 2);
            }

            if (arr[i * 2 + 1] > arr[i]) {
                adjust(arr, i, i * 2 + 1);
            }
        }
        System.out.print("start： " + 0 + " end： " + length + "   ");
        print(Arrays.copyOfRange(arr, 0, length));
    }

    public static void adjust(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void buildStack(int[] arr) {
        int length = arr.length;
        for (int i = length / 2 - 1; i >= 0; --i) {
            if (i * 2 + 2 < length && arr[i * 2 + 2] > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[i * 2 + 2];
                arr[i * 2 + 2] = temp;
            }

            if (arr[i * 2 + 1] > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[i * 2 + 1];
                arr[i * 2 + 1] = temp;
            }
        }
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
