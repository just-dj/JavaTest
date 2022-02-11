/**
 * Created with IntelliJ IDEA.
 * Date: 2019/9/9
 * Time: 10:48
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 在utf-8 编码中，ASCII字符使用1个字节   中文字符使用3到4个字节，
 * 应该是在BMP范围中的字符使用一个char储存，超过BMP的部分使用两个char来表示
 * char真实的含义是描述了UTF-16编码中的一个**代码单元**，而不是一个**字符**。
 */
public class Main {
    
    
    public static void main(String[]  args) throws Exception{
    
        String str = "hello中国";
        int byteLen = str.getBytes("utf-8").length;
        int strLen = str.length();
        System.out.println(System.getProperty("file.encoding"));
        System.out.println("byteLen : " + byteLen);
        System.out.println("strLen  : " + strLen);
    
    
        System.out.println("中".getBytes("UTF-8").length); //----> 3
        System.out.println("中中".getBytes("UTF-8").length);// ----> 6
        System.out.println("中".getBytes("GBK").length);// ----> 2
        System.out.println("中中".getBytes("GBK").length); //----> 4

        char temp = '中';
        
        
        
    }
    
    
}
