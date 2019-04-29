package com.demo.semaphore;


import com.demo.ApplicationTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源
 * 应用场景：Semaphore可以用于做流量控制，特别是公共资源有限的应用场景，比如数据库连接。
 * 假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程并发的读取，
 * 但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只用10个，这是我们必须控制只有10个线程
 * 同时获取数据库连接保存数据，否则会报错无法获取数据库连接，这个时候就可以使用Semaphore来做流量控制
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/22 14:11
 */
public class SemaphoreTest extends ApplicationTest {
    @Resource(name = "threadPool")
    private ExecutorService threadPool;

    public static final int THREAD_COUNT = 30;
    private static Semaphore s = new Semaphore(10);

    @Test
    public void test() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("save data");
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        threadPool.shutdown();
    }
    /**
     * 在代码中，虽然有30个线程在执行，但是只允许10个并发执行。Semaphore的构造方法Semaphore（int permits）
     * 接受一个整型的数字，表示可用的许可证数量。Semaphore（10）表示允许10个线程获取许可证，也就是最大并发数
     * 是10。Semaphore的用法很简单，首先线程使用Semaphore的acquire（）方法获取一个许可证，使用完之后用release（）方法
     * 归还许可证。还可以使用tryAcquire（）方法尝试获取许可证。
     */
}
