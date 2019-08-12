package com.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxin
 * @date 2019/08/12
 * 安全的暂停、恢复、中断线程
 * 中断线程应该用interrupt()，除了中断以外，还可以利用一个boolean变量来控制是否需要停止任务并终止改线程。
 * <p>
 * 不应该用过期的stop，stop()方法在终结一个线程时，不会保证线程资源的正常释放，通常是没有给与线程完成资源释放的机会，因此会导致程序可能工作在不确定的状态下。
 * 而suspend()和resume()暂停和恢复线程可以用等待/通知机制来替代；
 * suspend()在调用后，线程不会释放已经持有的资源(比如锁)，而是占有着资源进入睡眠状态，容易引发死锁问题，
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        //睡眠一秒，main线程对countThread进行中断，使countThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.isInterrupted();

        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        //睡眠一秒，main线程对Runner two进行取消，使CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancle();
    }

    static class Runner implements Runnable {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("count i = " + i);
        }

        public void cancle() {
            on = false;
        }
    }

}
