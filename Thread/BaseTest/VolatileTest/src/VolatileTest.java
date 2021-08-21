public class VolatileTest {

    public static volatile Object A;

    public static Object B;

    static {
        A = new Object();
        B = new Object();
    }

    public static void setA(Object a) {
        A = a;
    }

    public static void setB(Object b) {
        B = b;
    }
}
