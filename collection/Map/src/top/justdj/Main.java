package top.justdj;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    //clear put remove
    public static void main(String[] args) {
//                将键映射到索引上
//                存储了值的数组称为散列表
//                将键值映射到散列表的索引上的函数称为散列函数
	// write your code here
        //散列图
        //使用散列实现
        Map<String,String> a = new HashMap<String,String>();
        
        //链式散列图
        //使用LinkedList实现
        //以插入顺序 或访问顺序排序
        Map<String,String> b = new LinkedHashMap <String, String>();
        
        //树形图
        //使用红黑树实现
        //自然顺序排序 或者比较器顺序排序
        Map<String,String> c = new TreeMap <String, String>();
    }
}
