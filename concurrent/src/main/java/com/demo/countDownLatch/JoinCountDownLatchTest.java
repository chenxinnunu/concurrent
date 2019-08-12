package com.demo.countDownLatch;

/**
 * 利用join方法让主线程等待所有线程完成
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/21 16:39
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thred2 finish");
            }
        });
        thread1.start();
        thread2.start();
        //join用于让当前线程等待join线程执行结束。原理就是不停的检查线程是否存活。也可以加入超时时间
        thread1.join();
        thread2.join();
        System.out.println("all thread finish");
    }
}
