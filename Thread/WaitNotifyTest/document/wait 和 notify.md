[wait() 和 notify()方法](https://blog.csdn.net/weixin_43184769/article/details/89599632)



#### 锁池和等待池

**锁池：**线程A获得锁执行某synchronized修饰的方法M，其他线程想要再执行M需要获取锁，但是锁目前被A持有，所以其他线程会先进入锁池中等待A释放锁以后进行公平竞争，竞争到的线程则离开锁池执行代码，没竞争到的线程留在锁池等待下一次竞争。

**等待池：**线程A在持有锁时调用wait()释放锁并进入等待池中 

每个锁对象都有两个队列 一个是就绪队列 一个是阻塞队列 就绪队列里存储了将要获得锁的线程 ； 阻塞队列则存储了被阻塞的线程；

#### wait() 

**调用wait的当前线程必须拥有该对象的锁** 。**这个方法会使当前线程将自己放入对象的等待集合，然后放弃它持有的锁，线程将暂时不会被调度并处于休眠状态** ,**直到以下四种情况之一的发生：** 

- 其他线程调用同一个对象的notify方法，而此线程正好是被随机选中要唤醒的线程 

- 其他线程调用了同一个对象的notifyAll方法 

- 其他线程中断了该线程 

- wait(long waitTime)参数中的等待时间过去了，如果等待时间为0则会一直等待唤醒 

  **线程在被唤醒后，将会从该对象的等待集中删除，并重新启动线程调度， 在与其他线程竞争到锁后，wait方法才返回。对象和线程的状态将会与调用wait时完全相同** 

**其他**

​	在同步语句中才可调用，否则会抛出IllegalMonitorStateException。可以使调用该方法的线程释放共享资源的锁，然后从运行状态退出，进入等待队列，直到被再次唤醒；

#### nitify()

**如果有任何线程在等待此对象监视器（锁），则随机选择其中一个线程唤醒。  被唤醒的对象并不会立马开始执行，必须等到当前线程释放该对象上的锁，然后被  唤醒的线程将会公平的与其他线程进行竞争该锁。 notify方法必须是持有该对象的监视器的线程才能够调用，以下有三种方法可以成为监视器的拥有者 :**

- 执行该对象的同步方法 
- 执行在该对象上同步的同步代码块 
- 执行类对象的静态方法 
- 一般wait()为要放在while循环中，为了确保目标动作只有在保护条件成立的情况下才能够执行 。

**其他**

该方法可以随机唤醒等待队列中等待同一共享资源的“一个线程”，并使该线程退出等待队列，进入可运行状态，直到被再次唤醒；

#### notifyAll ()

**唤醒此对象监视器上的所有线程，对象通过wait方法来等待对象的监视器**  ；

方法 可以是所有正在等待队列中的等待同一共享资源的全部线程从等待状态退出，进入可运行状态。此时，优先级最高的线程最先执行，但也有可能是随机执行，因为这要取决于虚拟机的实现。







#### **注意**

- 线程执行wait方法后会自动释放锁 

- 线程执行notify后不释放锁 会执行完整个代码块

- 线程在awit状态下调用 interrupt方法会抛出 waitInterruptException 异常 
- wait和notify的使用会导致较多的上下文切换 

**上下文切换**

CPU通过时间片分配算法来循环执行任务，当前任务执行一个时间片后会切换到下一个任务。但是，在切换前会保存上一个任务的状态，以便下次切换回这个任务时，可以再次加载这个任务的状态，从任务保存到再加载的过程就是一次上下文切换。 



### wait() sleep() await()对比



#### wait 和 sleep 对比

```java
 public final native void wait(long timeout) throws InterruptedException;

 public static native void sleep(long millis) throws InterruptedException;
```

**同：**

- 都是线程同步时会用到的方法，使当前线程暂停运行，把运行机会交给其它线程。
- 如果任何线程在等待期间被中断都会抛出InterruptedException
- 都是native方法

**异：**

- 所在类不同，wait()是Object超类中的方法；而sleep()是线程Thread类中的方法
- 关键点是对锁的保持不同，wait会释放锁；而sleep()并不释放锁
- 唤醒方法不完全相同，wait依靠notify或者notifyAll、中断发生、或者到达指定时间来唤醒；而sleep()则是到达指定的时间后被唤醒。
- 使用的位置不同，wait只能用在同步代码块中，而sleep用在任何位置。



#### wait 和 await 对比

await是ConditionObject类里面的方法，ConditionObject实现了Condition接口；而ReentrantLock里面默认有实现newCondition()方法，新建一个条件对象。该方法就是用在ReentrantLock中根据条件来设置等待。唤醒方法也是由专门的Signal()或者Signal()来执行。另外await会导致当前线程被阻塞，会放弃锁，这点和wait是一样的。

由于所在的超类不同使用场景也不同，wait一般用于Synchronized中，而await只能用于ReentrantLock锁中，具体如下

**wait()** 由notify()或者notifyAll()方法唤醒

```java
synchronized (obj) {
              while (&lt;condition does not hold&gt;)
               obj.wait(timeout);
             ... // Perform action appropriate to condition
         }
```

**await()** 由signal() 方法唤醒