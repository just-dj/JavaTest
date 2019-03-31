package top.justdj;

import java.net.URL;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
	// write your code here
        
        System.out.println( Thread.currentThread().getContextClassLoader());
       String name = null;
        Optional<String> optional = Optional.ofNullable(name);
        System.out.println("结果是:"+optional.orElse("") + ":");
        
        Optional<User> op = Optional.ofNullable(new User("大鹏","则张"));
        System.out.println(op);
        System.out.println("map测试 : "+ op.map(a->((User)a).name.toString()).map(User->new User("大明","")));
       
    }
    
  
}

class User{
    static String name;
    static String age;
    
    public User(String name, String age) {
        User.name = name;
        User.age = age;
    }
    
    public static String getName() {
        return name;
    }
    
    public  void setName(String name) {
        this.name = name;
    }
    
    public static String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return name + " : "+ age;
    }
}