/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.4.29
  Time: 18:19
  Info:
*/

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.4.29
 * Time: 18:19
 *
 * https://blog.csdn.net/lipeng_bigdata/article/details/51232266
 */
public class OperationTest {
	
	private static final int COUNT_BITS = Integer.SIZE - 3;
	private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
	
	// runState is stored in the high-order bits
	private static final int RUNNING    = -1 << COUNT_BITS;
	private static final int SHUTDOWN   =  0 << COUNT_BITS;
	private static final int STOP       =  1 << COUNT_BITS;
	private static final int TIDYING    =  2 << COUNT_BITS;
	private static final int TERMINATED =  3 << COUNT_BITS;
	
//	CAPACITY  :         00011111111111111111111111111111
//	RUNNING   :        11100000000000000000000000000000
//	SHUTDOWN  :     00000000000000000000000000000000
//	STOP      :            00100000000000000000000000000000
//	TIDYING   :          01000000000000000000000000000000
//	TERMINATED:      01100000000000000000000000000000

	
	public static void main(String args[]){
		System.out.println("Integer.SIZE - 3  COUNT_BITS " + COUNT_BITS);
		System.out.println("-1 << COUNT_BITS  RUNNING " + RUNNING);
		System.out.println("0 << COUNT_BITS  SHUTDOWN " + SHUTDOWN);
		System.out.println("1 << COUNT_BITS  STOP " + STOP);
		System.out.println("2 << COUNT_BITS  TIDYING " + TIDYING);
		System.out.println("3 << COUNT_BITS  TERMINATED " + TERMINATED);
		
		//以 536870912为模 1左移27位
		System.out.println("workerCountOf  " + workerCountOf( 536870919));
	}
	
	
	// Packing and unpacking ctl
	//运行状态
	private static int runStateOf(int c)     { return c & ~CAPACITY; }
	//工作线程数量
	private static int workerCountOf(int c)  { return c & CAPACITY; }
	//初始化ctl
	private static int ctlOf(int rs, int wc) { return rs | wc; }
	
	
}
