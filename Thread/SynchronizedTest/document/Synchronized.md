

对象监视器

#### 对象锁  

Java 多线程编程核心技术 2.1.4  

##### 可重入锁 ：

###### 自己可以再次获取自己的内部锁，存在父子类继承关系时，子类可以通过可重入锁调用父类的同步方法

- 当一个线程执行的代码出现异常时，其所持有的锁会自动释放。
- synchronized 和 synchronized（this） 都是锁住当前对象的 会诸塞整个对象的同步方法
- synchronized(非this对象 x) 的好处在于 和其他同步方法是异步的 不与其他锁this同步方法抢this锁 可以大大提高运行效率
- 使用synchronized(非this对象 x)要注意 不能用String String存在常量池 可能会造成无限等待 
- 在将任何类型作为同步锁时，注意是否有多个线程同时持有锁对象，如果同时持有相同的锁对象，则这些线程之间就是同步的；如果分别获取锁对象，则这些线程之间就是异步的；在先获得锁的线程里修改锁的属性对锁是没有影响的，只要对象没有改变； 






#### 类锁 对类的所有对象实例起作用

- synchronized 修饰静态方法 
- synchronized(class)

#### 对象锁 

- synchronized 
- synchronized(this)



#### synchronized使用方法 

##### Synchronized 同步方法

对象内的多个方法可以使用同一个对象锁 他们之间是同步的 

同步是不能继承的

##### Synchronized 同步语句块

  一半同步 一半异步 尽可能减少同步的代码数量 

  当一个线程访问onject的一个Synchronized(this)同步代码块时，其他线程对于一个object中 其他Synchronized(this)同步代码块的访问将被阻塞，这说明synchronized使用的对象监视器是同一个

##### 静态同步synchronized方法



##### synchronized(class)代码块

