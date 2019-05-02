



等待线程对象销毁

join的作用是所属的线程对象x正常执行run中的任务，而使当前线程z进行无限期的阻塞，等待线程销毁后再继续执行z后面的代码。

join具有使线程排队运行的作用，有些类似同步的运行效果。join与synchronized的区别在于：join在内部使用wait方法进行等待，而synchronized使用的“对象监视器”原理作为同步。



在x线程 join过程中 如果z被中断 会抛出InterruptException异常 



join也还可以指定时间

#### join(long)和 sleep(long)的区别

​	join(long) 内部是使用wait (long) 实现的   z是释放了锁的，sleep没有释放锁

Join方法实现是通过wait（小提示：Object 提供的方法）。 当main线程调用t.join时候，main线程会获得线程对象t的锁（wait 意味着拿到该对象的锁),调用该对象的wait(等待时间)，直到该对象唤醒main线程 ，比如退出后。这就意味着main 线程调用t.join时，必须能够拿到线程t对象的锁。 



join后面的代码可能会提前运行的哦 如果join先抢到了线程 