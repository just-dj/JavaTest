import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception{
        TestA a = new TestA();
        System.out.println();
        Method[] methods = a.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Annotation[] annotations = methods[0].getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation instanceof InheritA);
            System.out.println(annotation instanceof B);
        }
        Annotation[] aAnnotations = a.getClass().getAnnotations();
        for (Annotation aAnnotation : aAnnotations) {
            aAnnotation.annotationType().getDeclaredAnnotations();
            System.out.println(aAnnotation instanceof InheritA);
            System.out.println(aAnnotation instanceof B);
        }

    }




    @InheritA
    public static class TestA{

        @InheritA
        public void say(){
        }

    }







}
