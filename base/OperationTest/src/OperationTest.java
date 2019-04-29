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
	
	
	public static void main(String args[]){
		System.out.println("Integer.SIZE - 3  COUNT_BITS " + COUNT_BITS);
		System.out.println("-1 << COUNT_BITS  RUNNING " + RUNNING);
		System.out.println("0 << COUNT_BITS  SHUTDOWN " + SHUTDOWN);
		System.out.println("1 << COUNT_BITS  STOP " + STOP);
		System.out.println("2 << COUNT_BITS  TIDYING " + TIDYING);
		System.out.println("3 << COUNT_BITS  TERMINATED " + TERMINATED);
		
		//以 536870912为模
		System.out.println("workerCountOf  " + workerCountOf( 536870919));
	}
	
	
	// Packing and unpacking ctl
	private static int runStateOf(int c)     { return c & ~CAPACITY; }
	private static int workerCountOf(int c)  { return c & CAPACITY; }
	private static int ctlOf(int rs, int wc) { return rs | wc; }
	
	
}
