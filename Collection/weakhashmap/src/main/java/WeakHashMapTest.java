import java.util.WeakHashMap;

public class WeakHashMapTest {

    public static void main(String[] args) {
        WeakHashMap<String, String> map = new WeakHashMap<String, String>();
        map.put("key", "value");
        map.put("key", "value2");

        map = null;
    }

}
