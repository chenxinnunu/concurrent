package com.demo.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 同步屏障CyclicBarrier CyclicBarrier的应用场景见测试类
 * CyclicBarrier的字面意思是可循环使用的屏障。
 * 他要做的事情是，然一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，
 * 屏障才会开门，所有被屏障拦截的线程才会继续运行
 * @author chenxinnunu@gmail.com
 * @date 2019/4/21 17:11
 */
public class CyclicBarrierTest1 {
    /**
     * 默认构造方式的参数表示拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，
     * 然后当前线程被阻塞。
    */
    private static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
    /**
     * 由于主线程和子线程的调度是有CPU决定的，两个线程都有可能先执行，所以会出现两种执行情况
     * 1 2 或 2 1
     *
     * 如果把new CyclicBarrier()中的 2 改成 3 ，那么主线程和子线程或永远等待，因为没有第三个线程执行await方法，
     * 就是没有第三个线程到达屏障，所以之前到达屏障的两个线程都不会执行
    */
}
