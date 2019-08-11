package com.demo.thread;

/**
 * @author chenxin
 * @date 2019/08/11
 * daemon线程是一种支持性线程，因此它主要被用作程序中后台调度以及支持性工作，
 * 当java虚拟机中不存在非daemon线程时，java虚拟机将会退出，
 * 可以通过Thread.setDaemon(true)将线程设置为Daemon线程。
 */
public class Daemon {
    /** daemon属性需要在线程启动前设置，不能在线程启动之后设置，
     * daemon线程被用作完成支持性工作，但是在Java虚拟机退出时，Daemon线程中的finally块并不一定会执行
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "daemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("daemon thread end run.");
            }
        }
    }
    /**
     * 执行之后没有在控制台有任何输出，main线程(非daemon线程）在启动了DaemonRunner线程之后，随着main方法执行完毕而终止，
     * 而此时Java虚拟中已经没有了非daemon线程，虚拟机需要退出。
     * Java虚拟机中的所有Daemon线程都需要立即终止，因此DaemonRunner立即终止，但是其中的finally块没有执行。
     * 所以，在创建daemon线程时，不能依靠finally块中的内容来确保执行关闭和清理资源的逻辑
     */

}
