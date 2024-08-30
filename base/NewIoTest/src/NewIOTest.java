import java.nio.ByteBuffer;

/*
*
* 从JDK1.4开始,Java提供了一些改进输入/输出处理的新功能,这些新功能被统称为新IO(New IO 简称NIO),新增了许多用于处理
* 输入/输出的类,这些类都被放在java.nio包以及子包下,并对原java.io中的很多类都以NIO为基础进行改写,新增了满足NIO的功能
*
* Java NIO 由以下几个核心部分组成:
* Channels：通道  Buffer：缓冲区  Selectors：选择器
* Channels和Buffer是新IO中的两个核心对象,Channel是对传统的输入/输出系统的模拟,在新IO系统中所有的数据都需要通过通道传输。
* Channels与传统的InputStream,OutputStrem最大的区别在于它提供了一个map()方法,通过该map()方法可以直接将"一块数据"映射到
* 内存中,如果说传统的输入/输出系统是面向流处理,则新IO则是面向块的处理
* Buffer可以被理解为一个容器(缓冲区,数组),发送到Channel中的所有对象都必须首先放到Buffer中,而从Channel中读取的数据也必须
* 放到Buffer中,也就是说数据可以从Channel读取到Buffer中,也可以从Buffer写到Channel中
*
* 使用Buffer
* Buffer就像一个数组,它可以保存多个类型相同的数据,Buffer是一个抽象类其最常用的类是ByteBuffer,它可以在底层字节数组上进行
* get/set操作,除了ByteBuffer之外,对应于其他基本数据类型(boolean除外)都有相应的Buffer类
*
*
* NIO与IO的区别:
* 1.IO面向流,NIO面向缓冲区
*   Java IO面向流意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。
*   此外，它不能前后移动流中的数据。如果需要前后移动从流中读取的数据，需要先将它缓存到一个缓冲区。
*   Java NIO的缓冲导向方法略有不同。数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动。
*   这就增加了处理过程中的灵活性
* 2.IO是阻塞式的,NIO有非阻塞式的
*   Java IO的各种流是阻塞的。这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，
*   或数据完全写入。该线程在此期间不能再干任何事情了。Java NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，
*   但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取，而不是保持线程阻塞，
*   所以直至数据变的可以读取之前，该线程可以继续做其他的事情。 非阻塞写也是如此。
* 3.IO没有选择器,NIO有选择器
*   Java NIO的选择器允许一个单独的线程来监视多个输入通道，你可以注册多个通道使用一个选择器，
*   然后使用一个单独的线程来“选择”通道：这些通道里已经有可以处理的输入，或者选择已准备写入的通道。
*   这种选择机制，使得一个单独的线程很容易来管理多个通道
*/
/*
* 使用Buffer读写数据一般遵循以下四个步骤：
1. 写入数据到Buffer
2. 调flip()方法
3. 从Buffer中读取数据
4. 调用clear()方法或者compact()方法
 当向buffer写入数据时，buffer会记录下写了多少数据。一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到
 读模式。在读模式下，可以读取之前写入到buffer的所有数据。
 一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。
 clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，
 新写入的数据将放到缓冲区未读数据的后面。
*/


/*
* Buffer的capacity,position和limit
* 缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。这块内存被包装成NIO Buffer对象，并提供了一组方法，
* 用来方便的访问该块内存。
* 为了理解Buffer的工作原理，需要熟悉它的三个属性：
* capacity(容量) position(容量开始的位置) limit(容量结束的位置)
* position和limit的含义取决于Buffer处在读模式还是写模式。不管Buffer处在什么模式，capacity的含义总是一样的。
*/

/*
* 在创建buffer对象的时候传递的参数就是capacity
* 容量为1024的缓冲区
* 此时buffer的limit和capacity都为1024
* 此时的position是0
*/
public class NewIOTest {

    public static void main(String[] args) {
        //MyByteBuffer1();//简单的读写
        MyByteBuffer2();//读写的基本方法
        Thread.currentThread().getContextClassLoader();
    }

    private static void MyByteBuffer2() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);//开辟容量1024字节
        System.out.println("position:" + buffer.position());//0
        System.out.println("limit:" + buffer.limit());//1024
        /*
         * position是5,说明写入了5个字节,position指向的是当前内容的结尾,方便接着往下写
         */
        buffer.put("hello".getBytes());
        System.out.println(buffer.position());//5
        System.out.println(buffer.limit());//1024
        //可以继续写
        buffer.put("world".getBytes());
        System.out.println(buffer.position());//10
        //切换为读模式
        /*这一步很重要 flip可以理解为模式切换 之前的代码实现的是写入操作
         *当调用这个方法后就变成读取操作,那么position和limit的值就要发生变换
         *此时capacity为1024不变
         *此时limit就移动到原来position所在的位置,相当于把buffer中没有数据的空间
         *"封印起来"从而避免读取Buffer数据的时候读到null值
         *相当于 limit = position  limit = 10
         *此时position的值相当于 position = 0
         *
         */

        buffer.flip();
        System.out.println("此时的Position:" + buffer.position());//0
        System.out.println("此时的limit:" + buffer.limit());//10
        /*
         * clear():
         * API中的意思是清空缓冲区
         * 而是将缓冲区中limit和position恢复到初始状态
         * 即limit和capacity都为1024 position是0
         * 此时可以完成写入模式
         */
        buffer.clear();
        System.out.println("clear后:" + buffer.position());
        System.out.println("clear后:" + buffer.limit());

        //可以继续写
        buffer.put("temp".getBytes());
        //继续读
        buffer.flip();
        byte[] arr = new byte[buffer.limit()];
        buffer.get(arr);
        System.out.println("temp:" + new String(arr));
    }

    private static void MyByteBuffer1() {
        //获取缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //写入数据
        buffer.put("Hello".getBytes());
        //将写模式转换成读模式
        buffer.flip();
        //读取一个字节
        //		byte b=buffer.get();
        //		System.out.println((char)b);
        //读取多个字节
        //这里必须把上面的读取一个字节的代码注释掉,因为执行一次get后相当于指针已经指向了下标1,
        //所以再继续读取buffer.limit个字符后越界.报错BufferUnderflowException
        byte[] by = new byte[buffer.limit()];
        buffer.get(by);
        System.out.println(new String(by));

    }

}
