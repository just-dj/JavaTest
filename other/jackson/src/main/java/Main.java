import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String a = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Person("张", "13588292637"));
        System.out.println(a);



        //反序列化测试
        Map map = new HashMap();
        map.put("test_name", "what");
        String origin = mapper.writeValueAsString(map);
        System.out.println(origin);
        System.out.println(mapper.readValue(origin, Person.class));


        //配置测试
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        map.put("aaaa", "a");
        mapper.readValue(mapper.writeValueAsString(map), Person.class);

    }

    public static class Person implements Serializable {

        @JsonProperty("test_name")
        String name;

        String phone;

        public Person() {
        }

        public Person(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }


    public class CustomSerializer extends StdSerializer<Person> {
        protected CustomSerializer(Class<Person> t) {
            super(t);
        }

        @Override
        public void serialize(Person person, JsonGenerator jgen,
                              SerializerProvider provider) throws IOException {

        }
    }

    public class Test1 extends JsonSerializer{
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        }
    }

}
