package top.justdj;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
	// write your code here
        
        System.out.println( Thread.currentThread().getContextClassLoader());
       String name = null;
        Optional<String> optional = Optional.ofNullable(name);
        System.out.println("结果是:"+optional.orElse("") + ":");

        System.out.println("奇怪的逻辑1-" +  Optional.ofNullable(LocalDateTime.now()).map(LocalDateTime::getDayOfYear).orElse(null));
        LocalDateTime test = null;
        System.out.println("奇怪的逻辑2-" +  Optional.ofNullable(test).map(LocalDateTime::getDayOfYear).orElse(null));
        System.out.println("奇怪的逻辑3-" +  Optional.ofNullable(test).map(LocalDateTime::getDayOfYear));

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