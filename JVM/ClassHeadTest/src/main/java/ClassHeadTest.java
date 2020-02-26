import org.openjdk.jol.info.ClassLayout;

/**
 * @Author justdj
 * @create 2020/2/22 10:30 上午
 */
public class ClassHeadTest {


    public static void main(String[] args) {
        int a = 0;
        a = a++;
        Human human = new Human();
        System.out.println(ClassLayout.parseInstance(human).toPrintable());
        System.out.println(a);
    }


}
