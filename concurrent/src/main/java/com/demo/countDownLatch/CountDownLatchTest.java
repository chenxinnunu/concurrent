package com.demo.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 用并发包中提供的CountDownLatch实现join的功能，让一个或多个线程等待其他线程完成，并且比join的功能更多
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/21 16:46
 */
public class CountDownLatchTest {
    /** 
     * CountDownLatch的构造函数接收一个int类型的参数作为计数器，N表示要等待N个点完成
     * 计数器必须大于等于0，只是等于0的时候，await方法不会阻塞当前线程。
    */
    private static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                /**
                 * 当调用countDown方法时，N就会减1，await方法会阻塞当前线程，直到N变成0
                 * countDown方法可以用在任何的地方，所以这里的N个点，可以说是N个线程，
                 * 也可以是1个线程里的N个执行步骤。用在多线程时，只需把这个CountDownLatch的引用传递到线程里即可。
                */
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await();
        /**
         * 当然有的时候也不可能让主线程一直等到，所以可以使用另外一个带指定时间的await方法-await(long time, TimeUnit unit),
         * 这个方法等待特定时间后，就不会再阻塞当前线程，join也有类似的方法。
        */
        System.out.println(3);
    }
}
