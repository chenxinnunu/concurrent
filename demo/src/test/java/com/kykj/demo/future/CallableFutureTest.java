package com.kykj.demo.future;

import com.kykj.demo.ApplicationTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 有返回值的线程
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 15:59
 */
public class CallableFutureTest extends ApplicationTest {
    @Resource(name = "consumerQueueThreadPool")
    private ExecutorService consumerQueueThreadPool;

    @Test
    public void getResult() throws ExecutionException {
        //创建一个有返回值的任务
        CallableTest c = new CallableTest();
        //pool.execute();
        Future f = consumerQueueThreadPool.submit(c);
        try {
            Object s = f.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumerQueueThreadPool.shutdown();
        }
    }
}
