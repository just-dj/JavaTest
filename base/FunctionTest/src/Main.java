import java.util.function.Function;

/**
 * @Author justdj
 * @create 2020/5/9 10:22 上午
 */
public class Main {

    public static void main(String[] args) {
        Function<Integer,Integer> a= i->i+1;
        Function<Integer,Integer> b=i->i*i;
        System.out.println("F1:"+b.apply(a.apply(5)));
        System.out.println("F1:"+b.compose(a).apply(5));
        System.out.println("F2:"+a.apply(b.apply(5)));
        System.out.println("F2:"+b.andThen(a).apply(5));

        /*
         先A.apply
         再执行 B.apply 然后返回了一个Function
         */
        b.compose(a).andThen(a).apply(5);
    }
}
