import java.util.HashMap;

/**
 * @Author justdj
 * @create 2020/2/10 10:09 下午
 * 基础操作符测试
 */
public class OperatorTest {

//    & ^ ~  >>  << >>>

    public static void main(String[] args) {
        int h;
        Integer key = 5;
        System.out.println(key.hashCode());
        System.out.println((h = key.hashCode()) ^ (h >>> 16));
        System.out.println((16 - 1) & ((h = key.hashCode()) ^ (h >>> 16)));

        key = -5;
        System.out.println(key.hashCode());
        System.out.println((h = key.hashCode()) ^ (h >>> 16));
        System.out.println((16 - 1) & ((h = key.hashCode()) ^ (h >>> 16)));

        HashMap hashMap = new HashMap();
        hashMap.put(-5, "111");

        System.out.println(-1 ^ 1);
    }

}
