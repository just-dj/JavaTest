import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class ReferenceTest {



    public static void main(String[] args) {
        Reference<String> reference = new WeakReference<String>("哎哎");
        while (!reference.isEnqueued()){
            System.gc();
        }
    }


}
