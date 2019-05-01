package top.justdj;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

class Book{
    public String name;
    
    public float price;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "书名： "+name +"  价格：  "+ price;
    }
}

public class Main {
    public static void main(String[] args) {
	// write your code here
        Book book1 = new Book();
        
        //方法一
        //根据Class对象产生一个BeanInfo类
        try{
            BeanInfo bInfoObject = Introspector.getBeanInfo(book1.getClass(),book1.getClass().getSuperclass());
            //获取Person Bean中所有属性的信息 ，以PropertyDescriptor数组的形式返回
            PropertyDescriptor mPropertryArr[] = bInfoObject.getPropertyDescriptors();
    
            for (PropertyDescriptor a:mPropertryArr) {
                System.out.println(" 属性名 ： "+a.getName() +"  属性类型："+ a.getPropertyType());
            }
        }catch (IntrospectionException e){
            e.printStackTrace();
        }
        
        //方法二
        //
        Book b = new Book();
        try{
            PropertyDescriptor pd = new PropertyDescriptor("name",b.getClass());
            Method methodName = pd.getWriteMethod();
            methodName.invoke(b,"大尾巴狼");
            
            String price = "12.0";
            PropertyDescriptor pd2 = new PropertyDescriptor("price",b.getClass());
            Method methodName2 = pd2.getWriteMethod();
            //和上面不同的是这里判断了一下参数属性，
            Class clazz = pd2.getPropertyType();
            if (clazz.equals(float.class))
                methodName2.invoke(b,Float.parseFloat(price));
            else
                methodName2.invoke(b,price);
            
            System.out.println(b);
            
            //读取JavaBean的属性
            Method mt = pd2.getReadMethod();
            Object o = mt.invoke(b);
            System.out.println(o);
            
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    
    }
}
