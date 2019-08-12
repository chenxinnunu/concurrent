package com.demo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.SimpleFormatter;

/**
 * @author chenxin
 * @date 2019/08/12
 * 等待通知机制
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wa @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                //直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //再次加锁
                synchronized (lock) {
                    System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        /**
         * 输出：Thread[WaitThread,5,main] flag is true. wa @ 14:09:09
         *      Thread[NotifyThread,5,main] hold lock. notify @ 14:09:10
         *      Thread[NotifyThread,5,main] hold lock again. sleep @ 14:09:15
         *      Thread[WaitThread,5,main] flag is false. running @ 14:09:20
         * 第三行和第四行的顺序可能会变。
         * 注意事项：
         * 1.使用wait() notify() notifyAll() 时需要先调用对象加锁
         * 2.调用wait() 之后，线程状态由Running变成了waiting，并将当前线程放到了等待队列。
         * 3.notify()和notifyAll()方法调用后，等待线程依然不会从wait()返回，需要调用wait()和waitAll()的线程释放锁之后，等待线程才有机会从wait()返回。
         * 4.notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()则是将将等待队列中的所有线程都移到到同步队列，被移到的线程状态由waiting变成blocked
         * 5.从wait()方法返回的前提是获得了调用对象的锁
         */
    }
}
