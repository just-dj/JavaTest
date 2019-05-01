package top.justdj;

import java.util.*;

public class Main {
    //add remove
    public static void main(String[] args) {
        
        //这个类实现了Equalse 和hashCode方法 由于没有实现父类的size 和 Iterator方法 所以是一个抽象类
        AbstractSet<Integer>  abstractSet = new AbstractSet <Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return null;
            }
    
            @Override
            public int size() {
                return 0;
            }
        };
        
        //散列集
        //实现了Set接口的具体类
        //初始容量16 负载系数0.75
        HashSet<Integer> hashSet = new HashSet <>();
    
        //链式散列集
        //继承了HashSet 如果想不需要维护插入顺序 应该使用Hashset ，比LinkedHashSet更高效
        //支持对集合内的元素排序  保持元素插入时的顺序
        LinkedHashSet linkedHashSet  = new LinkedHashSet();
        
        
        //树形集
        //实现了sortedSet 接口
        //支持自然顺序 （Comparable）和比较器顺序(comparator)
        TreeSet<Integer> treeSet = new TreeSet <>();
        
        
        
        
    }
}
