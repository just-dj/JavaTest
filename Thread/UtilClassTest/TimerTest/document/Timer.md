

Timer类的主要作用就是设置计划任务，

但封装任务的类却是TimerTask



```java
private static Timer timer = new Timer();

static public class MyTask extends TimerTask {
   @Override
   public void run() {
      System.out.println("运行了！时间为：" + new Date());
   }
}

public static void main(String[] args) {
   try {
      MyTask task = new MyTask();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateString = "2014-10-12 11:55:00";

      Date dateRef = sdf.parse(dateString);
      System.out.println("字符串时间：" + dateRef.toLocaleString() + " 当前时间："
            + new Date().toLocaleString());
      timer.schedule(task, dateRef);
   } catch (ParseException e) {
      e.printStackTrace();
   }
}
```



执行任务的时间晚于当前时间：在未来执行的代码

- ​	创建一个Timer就是启动一个新的线程，这个新启动的线程并不是守护线程，它一直在运行。
- ​	TimerTask是以队列的方式一个一个被顺序执行的，所以执行的时间可能和预期的时间不一致，因为前面的任务可能消耗的时间太长，则后面任务运行的时间也会被延迟。



计划时间早于当前时间： 提前运行的效果

- ​	如果执行任务的时间早于当前时间，则立即执行task任务。



**追赶执行性**： 计划时间早于当前时间，把早于当前时间的计划都执行掉

```java
public void schedule(TimerTask task, Date firstTime, long period) //不具有追赶执行性
```





```java
public void scheduleAtFixedRate(TimerTask task, long delay, long period) // 具有追赶执行性
```