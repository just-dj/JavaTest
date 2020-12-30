

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author justdj
 * @create 2020/3/9 9:19 下午
 */
public class ProxyTest {

    public static void main(String[] args) {
        Star cxk = new CXK();
        /*
        使用JDK动态代理，创建代理对象
        参数1：类加载器
        参数2：所有实现的接口
        参数3：方法调用的接口，Star接口中每个方法都会调用一次
        */
        Star s2 = (Star) Proxy.newProxyInstance(
                cxk.getClass().getClassLoader(),
                new Class[]{Star.class},
                new InvocationHandler() {
                    /*
                    对每个方法进行功能增强
                    参数1：代理对象，不能直接调用，不然出现递归
                    参数2：每个方法对象，如：sing() act()，每个方法都会调用一次
                    参数3：方法传递的参数，如：歌名
                    返回值：调用的方法的返回值
                    */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //如果是演出的方法，判断是否小于1000，如果小于则不演出，否则调用原来的方法
                        String methodName = method.getName();
                        if ("act".equals(methodName)) {
                            //得到演示参数值
                            int money = (int) args[0];
                            if (money < 1000) {
                                System.out.println("费用太低，不演出了");
                                return null;
                            }
                        }
                        //其它的情况就调用真实对象原来的方法
                        return method.invoke(cxk, args); //真实对象，方法的参数
                    }
                });

        //fans请自己喜欢的明星出场唱歌，拍戏
        s2.sing("只因你太美");
        //费用过低不满足
        s2.act(500);

    }
}
