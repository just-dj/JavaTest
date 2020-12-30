import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.xml.internal.ws.util.StreamUtils;

public class StreamTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");

        System.out.println(list.stream().findFirst());
        System.out.println(list.stream().map(String::hashCode).collect(Collectors.toList()));
        System.out.println(list.stream().mapToDouble(Double::parseDouble).max().getAsDouble());

//        list.stream().flatMapToDouble();
        list.stream().distinct().collect(Collectors.toList());
        list.stream().peek(a -> a.toCharArray());
        System.out.println(list.stream().skip(1).collect(Collectors.toList()));
        list.stream().sorted().collect(Collectors.toList());
    }

}
