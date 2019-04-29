package com.demo.ExchangerTest;

import com.demo.ApplicationTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;

/**
 * Exchanger（交换者）是一个用于线程间协作的工具类。
 * Exchanger用于进行线程间的数据交换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。
 * 这两个线程通过exchange方法交换数据，如果第一个线程先执行exchange（）方法，它会一直等待第二个线程
 * 也执行exchange方法，当两个线程都达到同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方
 *
 * 应用场景：
 * 可用于校对工作，比如我们需要将纸质银行流水通过人工的方式录入成电子银行流水，为了避免错误，采用AB岗两人
 * 进行录入，录入到Excel之后，系统需要加载这两个Excel，并对两个Excel数据进行校对，看看是否录入一致。
 * @author chenxinnunu@gmail.com
 * @date 2019/4/22 19:25
 */
public class ExchangerTest extends ApplicationTest {
    @Resource(name = "threadPool")
    private ExecutorService threadPool;

    public static final Exchanger<String> exgr = new Exchanger<>();
    @Test
    public void get() {
        //execute用于提交不需要返回值的任务，submit用于提交需要返回值的任务
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";
                    String exchange = exgr.exchange(A);
                    System.out.println(exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";
                    String A = exgr.exchange(B);
                    System.out.println("A和B数据是否一致:" + A.equals(B) + ",A录入的是:" + A + ",B录入的是:" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.shutdown();
    }
    /**
     * 如果两个线程有一个没有执行exchange（）方法，则会一致等待，如果担心有特殊情况发生，避免一致等待，
     * 可以使用exchange（V x， longtimeout， TimeUnit unit）设置最大等待时长。
    */
}
