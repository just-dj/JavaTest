package top.justdj;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
	// write your code here]
        LinkedList list = new LinkedList();
    
        String str1 = "aaa";
        String str2 = "aaa";
        String str = new String();
        System.out.println(str1 == str2 + "   "+str1.equals(str2));//返回TRUE
    
        
// 在写方法的时候可能结果集不存在，需要返回null,在调用这个方法的地方就要做一个null判断,很繁琐，
// 容易出问题，这个时候就可以使用emptyList或EMPTY_LIST。但是也会有同学说我new ArrayList不就可以了，
// 这样是可以，但是每次我们new 一个集合对象的时候都会有一个初始化空间，占用内存资源，
// 积少成多会浪费很多的资源，Collections中的空集合对象是一个静态常量，在内存中只存在一份，所以能够节省内存资源。

        List temp = Collections.EMPTY_LIST;
        temp.add(12);
        System.out.println(temp);
        
        
    }
}

