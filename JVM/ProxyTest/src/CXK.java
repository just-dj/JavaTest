/**
 * @Author justdj
 * @create 2020/3/9 9:28 下午
 */
public class CXK implements Star {

    @Override
    public void sing(String song) {
        System.out.println("sing : " + song);
    }

    @Override
    public void act(int money) {
        System.out.println("act : " + money);
    }
}
