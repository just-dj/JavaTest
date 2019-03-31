package top.justdj;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

interface Animal{
    public abstract  void said(); //函数冗余
}

class Human implements Animal{
    
    public String name = "";
    
    public int age = 0;
    
    public Human(){
    
    }
    
    public <T extends Human>  Human(T  anotherHuman){
        this.name = anotherHuman.name;
        this.age = anotherHuman.age;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public void said() {
        System.out.println("Human  name :"+this.name+"  age : "+this.age);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}

class Student extends Human{
    
    public Student(){
    
    }
    
    public Student(String name,int age){
        this.age = age;
        this.name = name;
    }

    public void sayHello(String name){
        System.out.println("Hello " + name + "  " + this.name);
        return;
    }
    @Override
    public void said() {
        System.out.println("Student name :"+this.name+"  age : "+this.age);
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        Student xiaoPeng = new Student("大鹏",40);
        Human daPeng = new Human(xiaoPeng);
        
        xiaoPeng.said();
        daPeng.said();
        
//        toString内部实现是==，进行hash码的对比 ，对于类的实例 ，hash就是在内存内的地址；
        System.out.println( xiaoPeng.getName()  +"   "+ xiaoPeng.toString() );
        System.out.println( xiaoPeng.getClass().getName() + "   " + daPeng.getClass());
        
        try{
            Class clazz =  Class.forName(xiaoPeng.getClass().getName());
            Constructor cons[] = clazz.getConstructors();
            
            Student xiaoMing = (Student)clazz.newInstance();
            //通过获取构造方法创建实例 与Class 类里面的函数同名 但是参数不一样
            Student xiaoDong = (Student)cons[0].newInstance();
            Student xiaoBa = (Student)cons[1].newInstance("小八",12);
            
            //输出构造函数信息
            System.out.print("构造函数信息  "+ cons[1].getModifiers() +" " + cons[1].getName() + '\n');
            
            xiaoMing.setName("小明");
            xiaoMing.said();
            
            xiaoBa.said();
            //这里已经得到一个Student对象了，//这里使用Field访问属性
            Object p = clazz.newInstance();
            //获取类中指定的属性名
            //注意这里获取到了父类 就目前来看 子类无法通过nameFiled访问父类属性，就是只能访问真正属于自己的 非集成属性
            Class superClass = xiaoMing.getClass().getSuperclass();
            Field nameFiled = superClass.getDeclaredField("name");
                //通过反射访问该属性时取消权限检查
            nameFiled.setAccessible(true);
            nameFiled.set(p,"春天");
            ((Student)p).said();
            
            //获取类中指定的方法
            //新建一个Student对象
            Object pp = clazz.newInstance();
            ((Student)pp).setName("反射");
            //获取Student类中的sayHello方法 里面有两个形参
            Method md = clazz.getMethod("sayHello",String.class);
            //调用该函数
            md.invoke(pp,"铁头娃");
            
        }catch (ClassNotFoundException e){
            System.out.println(e.getCause());
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
        
    }
}
