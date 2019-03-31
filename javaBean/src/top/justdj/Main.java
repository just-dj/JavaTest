package top.justdj;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
	// write your code here
        
        Test first = new Test();
        System.out.println( first.getClass().getName() );
    
    
        PropertyDescriptor pd = new PropertyDescriptor("name",first.getClass());
        Method methodName = pd.getWriteMethod();
        
        methodName.invoke(first,"小明");

    }
}
