##异步Servlet

提供了异步Servlet的CallAble实现和线程模型。  

在传统的Servlet中，如果Servlet中有耗时的操作（例如IO操作），将阻塞Servlet，占用服务器资源。
因此，Servlet 3.0推出了异步Servlet技术来解决这个问题。  

**在阅读本文档之前，请阅读**_java.util.concurrent.Callable_**相关api.**

#依赖

jar包：[async.jar](https://github.com/xcr1234/async/raw/master/dist/async.jar)，

源码jar包：[async-source.jar](https://github.com/xcr1234/async/raw/master/dist/async-source.jar)

例子：[example.war](https://github.com/xcr1234/async/raw/master/dist/example.war)


#一、线程池

准备一个线程池，供异步Servlet使用。方式有两种：  

方式一

在web.xml中加入  

```xml
     <context-param>
            <param-name>asyncExecutorsSize</param-name>
            <param-value>8</param-value>
     </context-param>
    <listener>
        <listener-class>com.oralcewdp.async.AsyncExecutorsContextListener</listener-class>
    </listener>
```

asyncExecutorsSize参数表示线程池容量，默认值为8。

方式二

自己准备一个线程池，然后调用AsyncServlet的setAsyncExecutors方法，例如：

```java
    ExecutorService exec=Executors.newCachedThreadPool();  //自己创建的线程池
    setAsyncExecutors(exec);
```

同一个Servlet的setAsyncExecutors方法只能被调用一次。

#二、写一个AsyncServlet类

创建一个类继承AsyncServlet抽象类，注意AsyncServlet是一个泛型类，其泛型用法与java.util.concurrent.Callable一样。

**配置Servlet时需要加上asyncSupported = true**

需要实现getCallable方法，实现这个方法的模式如下：

```java
     return new ServletCallAble<E>(){
        pulibc E call() throws Exception{
             //do something.
             //return xxx;
             
        }
     
    };

```

在call方法中执行比较耗时的操作，操作完毕后返回执行的结果。
ServletCallAble其实就是Callable，只是可以使用getRequest、getResponse和getAsyncContext方法得到request、response和asyncContext.


#FAQ

Q：如何在Servlet中输出运行结果？  
A：覆盖onComplete方法，第三个参数为输出结果。
- - -
Q：异步过程中的异常如何处理？  
A：覆盖onError方法，第三个参数为异常Throwable。或者在call中try-catch；当异步过程处理超时时，可以覆盖onTimeout方法。
- - -
Q：可否过滤是否进入异步过程，例如检查用户输入？
A：覆盖doAsync方法，返回true则进入异步过程，返回false则不进入。
- - -
Q：超时时间如何设置？
A：执行setTimeout方法或者覆盖getTimeout方法，默认为10秒。
- - -
Q：异步过程是否支持跳转（dispatch）？
A：支持。AsyncContext中有dispatch方法，可以进行跳转，具体参考javax.servlet.AsyncContext。