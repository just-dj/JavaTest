import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Date: 19.5.3
 * Time: 19:01
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 通过synchronized 实现的俺懒加载线程安全单例模式 只对关键代码同步 保证性能 这里这是一个示例
 *  实际上通过静态内部类也可以实现
 */
public class SingletonModeTest {
	
	private volatile  static SingletonModeTest singletonModeTest;
	private SingletonModeTest(){};
	
	public  static  SingletonModeTest getInstance(){
		try {
			if (null == singletonModeTest){
				//模拟处理时间
				TimeUnit.SECONDS.sleep(3);
				synchronized (SingletonModeTest.class){
					if (null == singletonModeTest){
						singletonModeTest = new SingletonModeTest();
					}
				}
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		return singletonModeTest;
	}
	
	public static SingletonModeTest getInstance2(){
		return SingleModelTestFactory.singletonModeTest;
	}
	
	public static void main(String[] args){
		//通过同步方式实现
		new MyThread().start();
		new MyThread().start();
		new MyThread().start();
		//静态内部类实现
		new MyThread2().start();
		new MyThread2().start();
	}
	
	
	private static class SingleModelTestFactory{
		private static SingletonModeTest singletonModeTest = new SingletonModeTest();
	}
}

class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println(SingletonModeTest.getInstance().hashCode());
	}
}

class MyThread2 extends Thread{
	
	@Override
	public void run() {
		System.out.println(SingletonModeTest.getInstance2().hashCode());
	}
}

