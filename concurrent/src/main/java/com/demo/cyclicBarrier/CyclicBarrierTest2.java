package com.demo.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 线程到达屏障时，可以优先执行的CyclicBarrier
 * CyclicBarrier的应用场景见测试类
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/21 17:26
 */
public class CyclicBarrierTest2 {
    private static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
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

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }

    /**
     * 因为设置了拦截线程的数量是2，所以必须等待代码中的第一个线程和线程A都执行完之后，
     * 才会继续执行主线程，然后输出2
     * 所以输出结果是3 1 2
    */
}
