/*
  Created by IntelliJ IDEA.
  User: shan
  Date: 19.4.13
  Time: 11:02
  Info:
*/

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 *
 * @author justdj
 * @email top90982@gmail.com
 * Date: 19.4.13
 * Time: 11:02
 */
public class CallableTest {
	
	static class Sum implements Callable<Long> {
		private final long from;
		private final long to;
		
		Sum(long from, long to) {
			this.from = from;
			this.to = to;
		}
		
		@Override
		public Long call() {
			long acc = 0;
			for (long i = from; i <= to; i++) {
				acc = acc + i;
			}
			System.out.println(Thread.currentThread().getName() + " : " + acc);
			return acc;
		}
	}
	
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		List<Future<Long>> results = executor.invokeAll(Arrays.asList(
				new Sum(0, 10), new Sum(0, 1_000), new Sum(0, 1_000_000)
		));
		executor.shutdown();
		
		for (Future<Long> result : results) {
			System.out.println(result.get());
		}
	}
	
	
}
