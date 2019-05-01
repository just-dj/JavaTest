package top.justdj;

import java.util.*;

public class Main {
	//  pop peek push
    public static void main(String[] args) {
    	//队列
	    //默认长度为10 扩展1.5倍
        ArrayList<String> arrayList = new ArrayList <>();
		System.out.println(arrayList.size());
	
		//向量类 其实是线程安全的LinkedList 好像是在方法前加上synchronized修饰符
	    //不需要同步的应用程序应使用ArrayList ，效率更高
	    Vector<Integer> vector = new Vector <>();
	    //栈
	    //Vector类的子类 实现了Stack结构
		Stack<Integer> stack = new Stack <>();
	 
		
		//链表
		//Deque<Integer> 双端队列接口 继承自Queue接口
		//LinkedList实现了Deque接口 所以 链表非常适合实现队列
	    LinkedList<Object> linkedList = new LinkedList <>();
	    linkedList.add(1);
	    linkedList.add("test");
	    ListIterator<Object> listIterator = linkedList.listIterator();
	    while (listIterator.hasNext())
		    System.out.print(listIterator.next() + " ");
	    
	    
	    //迭代器 能够更方便的对线性表等进行遍历
	    System.out.println();
	    listIterator = linkedList.listIterator(linkedList.size());
	    while (listIterator.hasPrevious())
		    System.out.print(listIterator.previous() + " ");
	
		
	    
    }
}
